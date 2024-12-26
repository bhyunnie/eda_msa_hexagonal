package study.bhyunnie.rental.domain.model

import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import study.bhyunnie.rental.domain.model.vo.Item
import java.time.LocalDate

@Embeddable
data class RentalItem(
	@Embedded
	val item: Item,
	val rentDate: LocalDate,
	var overdue: Boolean,
	var overdueDate: LocalDate, // 반납 예정일
) {
	companion object {
		fun createRentalItem(item: Item): RentalItem {
			return RentalItem(
				item = item,
				rentDate = LocalDate.now(),
				overdue = false,
				LocalDate.now().plusDays(14)
			)
		}

		fun sample(): RentalItem {
			return createRentalItem(Item.sample())
		}

		@JvmStatic
		fun main(args: Array<String>) {
			println(sample())
		}
	}
}