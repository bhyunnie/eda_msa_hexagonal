package study.bhyunnie.eda_msa_hexagonal.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.eda_msa_hexagonal.application.output.RentalCardOutputPort
import study.bhyunnie.eda_msa_hexagonal.application.usecase.InquiryUsecase
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentItemOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.ReturnItemOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.UserInputDto
import java.util.Optional
import javax.swing.text.html.Option

@Service
@Transactional
class InquiryInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort
):InquiryUsecase {
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