package study.bhyunnie.rental.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.application.usecase.OverdueItemUsecase
import study.bhyunnie.rental.domain.model.RentalCard
import study.bhyunnie.rental.domain.model.vo.Item
import study.bhyunnie.rental.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.rental.framework.web.dto.UserItemInputDto

@Service
@Transactional
class OverdueItemInputPort(private val rentalCardOutputPort: RentalCardOutputPort) : OverdueItemUsecase {
	override fun overdueItem(rental: UserItemInputDto): RentalCardOutputDto {
		val rentalCard: RentalCard = rentalCardOutputPort.loadRentalCard(rental.userId).orElseThrow {
			IllegalArgumentException("해당 카드가 존재하지 않습니다.")
		}
		rentalCard.overdueItem(Item(rental.itemId.toInt(), rental.itemTitle))
		return RentalCardOutputDto.mapToDto(rentalCard)
	}
}