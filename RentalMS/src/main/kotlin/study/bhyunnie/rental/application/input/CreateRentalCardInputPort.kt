package study.bhyunnie.rental.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.application.usecase.CreateRentalCardUsecase
import study.bhyunnie.rental.model.RentalCard
import study.bhyunnie.rental.model.vo.IDName
import study.bhyunnie.rental.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.rental.framework.web.dto.UserInputDto

@Service
@Transactional
class CreateRentalCardInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort
) : CreateRentalCardUsecase {
	override fun createRentalCard(userInputDto: UserInputDto): RentalCardOutputDto {
		val rentalCard: RentalCard = RentalCard.createRentalCard(IDName(userInputDto.userId, userInputDto.userName))
		return RentalCardOutputDto.mapToDto(
			rentalCardOutputPort.save(rentalCard)
		)
	}
}