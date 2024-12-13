package study.bhyunnie.eda_msa_hexagonal.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.eda_msa_hexagonal.application.output.RentalCardOutputPort
import study.bhyunnie.eda_msa_hexagonal.application.usecase.OverdueItemUsecase
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import study.bhyunnie.eda_msa_hexagonal.domain.model.vo.Item
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.UserItemInputDto

@Service
@Transactional
class OverdueItemInputPort(private val rentalCardOutputPort: RentalCardOutputPort) :OverdueItemUsecase {
	override fun overdueItem(rental: UserItemInputDto): RentalCardOutputDto {
		val rentalCard: RentalCard = rentalCardOutputPort.loadRentalCard(rental.userId).orElseThrow {
			IllegalArgumentException("해당 카드가 존재하지 않습니다.")
		}
		rentalCard.overdueItem(Item(rental.itemId.toInt(), rental.itemTitle))
		return RentalCardOutputDto.mapToDto(rentalCard)
	}
}