package study.bhyunnie.eda_msa_hexagonal.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.eda_msa_hexagonal.application.output.RentalCardOutputPort
import study.bhyunnie.eda_msa_hexagonal.application.usecase.ReturnItemUsecase
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import study.bhyunnie.eda_msa_hexagonal.domain.model.vo.Item
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.UserItemInputDto
import java.time.LocalDate

@Service
@Transactional
class ReturnItemInputPort (
	private val rentalCardOutputPort: RentalCardOutputPort
):ReturnItemUsecase {
	override fun returnItem(returnDto: UserItemInputDto): RentalCardOutputDto {
		val rentalCard:RentalCard = rentalCardOutputPort.loadRentalCard(returnDto.userId).orElseThrow{
			IllegalArgumentException("해당 카드가 존재하지 않습니다")
		}
		val returnItem = Item(returnDto.itemId.toInt(), returnDto.itemTitle)
		rentalCard.returnItem(returnItem, LocalDate.now())
		return RentalCardOutputDto.mapToDto(rentalCard)
	}
}