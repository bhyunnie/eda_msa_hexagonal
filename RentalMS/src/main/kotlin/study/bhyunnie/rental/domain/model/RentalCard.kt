package study.bhyunnie.rental.domain.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Embedded
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import study.bhyunnie.rental.domain.model.event.ItemRented
import study.bhyunnie.rental.domain.model.event.ItemReturned
import study.bhyunnie.rental.domain.model.event.OverdueCleared
import study.bhyunnie.rental.domain.model.vo.LateFee
import study.bhyunnie.rental.domain.model.vo.IDName
import study.bhyunnie.rental.domain.model.vo.Item
import study.bhyunnie.rental.domain.model.vo.RentStatus
import study.bhyunnie.rental.domain.model.vo.RentalCardNo
import java.awt.Point
import java.time.LocalDate
import java.time.Period

@Entity
data class RentalCard(
	@EmbeddedId
	val rentalCardNo: RentalCardNo,
	@Embedded
	val member: IDName,
	var rentStatus: RentStatus,
	@Embedded
	var lateFee: LateFee,
	@ElementCollection
	val rentalItemList: ArrayList<RentalItem> = ArrayList(),
	@ElementCollection
	val returnItemList:ArrayList<ReturnItem> = ArrayList()
) {
	fun addRentalItem(rentalItem: RentalItem) {
		this.rentalItemList.add(rentalItem)
	}

	fun removeRentalItem(rentalItem: RentalItem) {
		this.rentalItemList.remove(rentalItem)
	}

	fun addReturnItem(returnItem: ReturnItem) {
		this.returnItemList.add(returnItem)
	}

	fun removeReturnItem(returnItem: ReturnItem) {
		this.returnItemList.remove(returnItem)
	}

	// 대여 처리
	fun rentItem(item: Item) {
		checkRentalAvailable()
		addRentalItem(RentalItem.createRentalItem(item = item))
	}

	// 반납 처리
	fun returnItem(item: Item, returnDate: LocalDate): RentalCard {
		val rentalItem = this.rentalItemList.stream().filter{ it.item == item }.findFirst().get()
		calculateLateFee(rentalItem, returnDate)
		this.addReturnItem(ReturnItem.createReturnItem(rentalItem))
		this.removeRentalItem(rentalItem)
		return this
	}

	private fun checkRentalAvailable() {
		if (this.rentStatus == RentStatus.RENT_UNAVAILABLE) throw RuntimeException("대여 불가 상태 입니다")
		if (this.rentalItemList.size > 5) throw RuntimeException("대여 가능 권수를 초과했습니다")
	}

	private fun calculateLateFee(rentalItem: RentalItem, returnDate: LocalDate) {
		if(returnDate > rentalItem.overdueDate) {
			val point:Long = Period.between(rentalItem.overdueDate, returnDate).days * 10L
			this.lateFee.addPoint(point)
		}
	}

	// 테스트 용 코드
	fun overdueItem(item: Item): RentalCard {
		val rentalItem = this.rentalItemList.first { it.item == item }
		rentalItem.overdue = true
		this.rentStatus = RentStatus.RENT_UNAVAILABLE
		// 강제로 연체 만들기
		rentalItem.overdueDate = LocalDate.now().minusDays(1)
		return this
	}

	fun makeAvailableRental(point: Long): Long {
		// 단순하게 같은 경우만
		if (this.rentalItemList.size != 0) throw RuntimeException("모든 도서가 반납되어야 정지를 해제할 수 있습니다")
		if (this.lateFee.point != point) throw RuntimeException("해당 포인트로 연체를 해제할 수 없습니다")

		this.lateFee = lateFee.removePoint(point)
		if (this.lateFee.point == 0L) {
			this.rentStatus = RentStatus.RENT_AVAILABLE
		}
		return this.lateFee.point
	}

	companion object {
		fun createRentalCard(creator: IDName): RentalCard {
			return RentalCard(
				rentalCardNo = RentalCardNo.createRentalCardNo(),
				member = creator,
				rentStatus = RentStatus.RENT_AVAILABLE,
				lateFee = LateFee.createLateFee()
			)
		}

		fun createItemRentedEvent(idName:IDName, item:Item, point: Long):ItemRented {
			return ItemRented(idName,item,point)
		}

		fun createItemReturnEvent(idName:IDName, item:Item, point: Long):ItemReturned {
			return ItemReturned(idName,item,point)
		}

		fun createOverdueClearedEvent(idName:IDName, point: Long):OverdueCleared {
			return OverdueCleared(idName, point)
		}

		fun sample(): RentalCard {
			return RentalCard(
				rentalCardNo = RentalCardNo.sample(),
				member = IDName.sample(),
				rentStatus = RentStatus.RENT_AVAILABLE,
				lateFee = LateFee.sample(),
			)
		}

		@JvmStatic
		fun main(args: Array<String>) {
			println(sample())
		}
	}
}