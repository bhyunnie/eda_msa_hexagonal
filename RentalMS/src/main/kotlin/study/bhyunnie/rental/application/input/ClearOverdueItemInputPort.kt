package study.bhyunnie.rental.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.rental.application.output.EventOutputPort
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.application.usecase.ClearOverdueItemUsecase
import study.bhyunnie.rental.domain.model.RentalCard
import study.bhyunnie.rental.framework.web.dto.ClearOverdueInfoDto
import study.bhyunnie.rental.framework.web.dto.RentalResultOutputDto

@Service
@Transactional
class ClearOverdueItemInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort,
	private val eventOutputPort: EventOutputPort
): ClearOverdueItemUsecase {
	override fun clearOverdue(clearOverdueInfoDto: ClearOverdueInfoDto): RentalResultOutputDto {
		val rentalCard: RentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDto.userId).orElseThrow {
			IllegalArgumentException("해당 카드가 존재하지 않습니다")
		}
		rentalCard.makeAvailableRental(clearOverdueInfoDto.point.toLong())

		val clearedOverdueItemEvent = RentalCard.createOverdueClearedEvent(rentalCard.member, clearOverdueInfoDto.point.toLong())
		eventOutputPort.occurOverdueClearedEvent(clearedOverdueItemEvent)
		return RentalResultOutputDto.mapToDto(rentalCard)
	}
}