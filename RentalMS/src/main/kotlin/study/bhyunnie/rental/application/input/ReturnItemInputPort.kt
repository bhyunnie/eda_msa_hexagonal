package study.bhyunnie.rental.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.rental.application.output.EventOutputPort
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.application.usecase.ReturnItemUsecase
import study.bhyunnie.rental.domain.model.RentalCard
import study.bhyunnie.rental.domain.model.vo.Item
import study.bhyunnie.rental.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.rental.framework.web.dto.UserItemInputDto
import java.time.LocalDate

@Service
@Transactional
class ReturnItemInputPort (
	private val rentalCardOutputPort: RentalCardOutputPort,
	private val eventOutputPort: EventOutputPort
): ReturnItemUsecase {
	override fun returnItem(returnDto: UserItemInputDto): RentalCardOutputDto {
		val rentalCard: RentalCard = rentalCardOutputPort.loadRentalCard(returnDto.userId).orElseThrow{
			IllegalArgumentException("해당 카드가 존재하지 않습니다")
		}
		val returnItem = Item(returnDto.itemId.toInt(), returnDto.itemTitle)
		rentalCard.returnItem(returnItem, LocalDate.now())

		val returnItemEvent = RentalCard.createItemReturnEvent(rentalCard.member, returnItem, 10)
		eventOutputPort.occurReturnEvent(returnItemEvent)

		return RentalCardOutputDto.mapToDto(rentalCard)
	}
}