package study.bhyunnie.rental.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.rental.application.output.RentalCardOutputPort
import study.bhyunnie.rental.application.usecase.InquiryUsecase
import study.bhyunnie.rental.framework.web.dto.RentItemOutputDto
import study.bhyunnie.rental.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.rental.framework.web.dto.ReturnItemOutputDto
import study.bhyunnie.rental.framework.web.dto.UserInputDto
import java.util.Optional

@Service
@Transactional
class InquiryInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort
): InquiryUsecase {
	override fun getRentalCard(userInputDto: UserInputDto): Optional<RentalCardOutputDto> {
		return rentalCardOutputPort.loadRentalCard(userInputDto.userId).map {
			RentalCardOutputDto.mapToDto(it)
		}
	}

	override fun getAllRentItem(userInputDto: UserInputDto): Optional<List<RentItemOutputDto>> {
		return rentalCardOutputPort.loadRentalCard(userInputDto.userId).map { loadCard ->
			loadCard.rentalItemList.map { RentItemOutputDto.mapToDto(it) }
		}
	}

	override fun getAllReturnItem(userInputDto: UserInputDto): Optional<List<ReturnItemOutputDto>> {
		return rentalCardOutputPort.loadRentalCard(userInputDto.userId).map { loadCard ->
			loadCard.returnItemList.map { ReturnItemOutputDto.mapToDto(it) }
		}
	}
}