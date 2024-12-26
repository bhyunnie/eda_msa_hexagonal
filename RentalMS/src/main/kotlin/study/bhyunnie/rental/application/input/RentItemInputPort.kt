package study.bhyunnie.rental.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.rental.application.output.EventOutputPort
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.application.usecase.RentItemUsecase
import study.bhyunnie.rental.domain.model.RentalCard
import study.bhyunnie.rental.domain.model.vo.IDName
import study.bhyunnie.rental.domain.model.vo.Item
import study.bhyunnie.rental.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.rental.framework.web.dto.UserItemInputDto

@Service
@Transactional
class RentItemInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort,
	private val eventOutputPort: EventOutputPort
): RentItemUsecase {
	override fun rentItem(rental: UserItemInputDto): RentalCardOutputDto {
		val rentalCard = rentalCardOutputPort.loadRentalCard(rental.userId).orElseGet{
			RentalCard.createRentalCard(IDName(rental.userId, rental.userName))
		}
		val newItem = Item(rental.itemId.toInt(), rental.itemTitle)
		rentalCard.rentItem(newItem)
		val itemRentEvent = RentalCard.createItemRentedEvent(rentalCard.member, newItem, 10)
		eventOutputPort.occurRentalEvent(itemRentEvent)
		val save: RentalCard = rentalCardOutputPort.save(rentalCard)
		return RentalCardOutputDto.mapToDto(save)
	}
}

