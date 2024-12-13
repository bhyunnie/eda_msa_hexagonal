package study.bhyunnie.eda_msa_hexagonal.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.eda_msa_hexagonal.application.output.RentalCardOutputPort
import study.bhyunnie.eda_msa_hexagonal.application.usecase.ClearOverdueItemUsecase
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.ClearOverdueInfoDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalResultOutputDto

@Service
@Transactional
class ClearOverdueItemInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort,
):ClearOverdueItemUsecase {
	override fun clearOverdue(clearOverdueInfoDto: ClearOverdueInfoDto): RentalResultOutputDto {
		val rentalCard: RentalCard = rentalCardOutputPort.loadRentalCard(clearOverdueInfoDto.userId).orElseThrow {
			IllegalArgumentException("해당 카드가 존재하지 않습니다")
		}
		rentalCard.makeAvailableRental(clearOverdueInfoDto.point.toLong())
		return RentalResultOutputDto.mapToDto(rentalCard)
	}
}