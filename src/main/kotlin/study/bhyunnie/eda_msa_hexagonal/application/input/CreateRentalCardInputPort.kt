package study.bhyunnie.eda_msa_hexagonal.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.eda_msa_hexagonal.application.output.RentalCardOutputPort
import study.bhyunnie.eda_msa_hexagonal.application.usecase.CreateRentalCardUsecase
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import study.bhyunnie.eda_msa_hexagonal.domain.model.vo.IDName
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.UserInputDto

@Service
@Transactional
class CreateRentalCardInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort
) : CreateRentalCardUsecase {
	override fun createRentalCard(userInputDto: UserInputDto): RentalCardOutputDto {
		val rentalCard:RentalCard = RentalCard.createRentalCard(IDName(userInputDto.userId, userInputDto.userName))
		return RentalCardOutputDto.mapToDto(
			rentalCardOutputPort.save(rentalCard)
		)
	}
}