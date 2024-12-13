package study.bhyunnie.rental.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.application.usecase.ClearOverdueItemUsecase
import study.bhyunnie.rental.model.RentalCard
import study.bhyunnie.rental.framework.web.dto.ClearOverdueInfoDto
import study.bhyunnie.rental.framework.web.dto.RentalResultOutputDto

@Service
@Transactional
class ClearOverdueItemInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort,
): ClearOverdueItemUsecase {
	override fun clearOverdue(clearOverdueInfoDto: ClearOverdueInfoDto): RentalResultOutputDto {
		val rentalCard: RentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDto.userId).orElseThrow {
			IllegalArgumentException("해당 카드가 존재하지 않습니다")
		}
		rentalCard.makeAvailableRental(clearOverdueInfoDto.point.toLong())
		return RentalResultOutputDto.mapToDto(rentalCard)
	}
}