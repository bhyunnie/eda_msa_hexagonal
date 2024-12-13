package study.bhyunnie.eda_msa_hexagonal.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.eda_msa_hexagonal.application.output.RentalCardOutputPort
import study.bhyunnie.eda_msa_hexagonal.application.usecase.RentItemUsecase
import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import study.bhyunnie.eda_msa_hexagonal.domain.model.vo.IDName
import study.bhyunnie.eda_msa_hexagonal.domain.model.vo.Item
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.RentalCardOutputDto
import study.bhyunnie.eda_msa_hexagonal.framework.web.dto.UserItemInputDto

@Service
@Transactional
class RentItemInputPort(
	private val rentalCardOutputPort: RentalCardOutputPort
):RentItemUsecase {
	override fun rentItem(rental: UserItemInputDto): RentalCardOutputDto {
		val rentalCard = rentalCardOutputPort.loadRentalCard(rental.userId).orElseGet{
			RentalCard.createRentalCard(IDName(rental.userId, rental.userName))
		}
		val newItem = Item(rental.itemId.toInt(), rental.itemTitle)
		rentalCard.rentItem(newItem)
		val save:RentalCard = rentalCardOutputPort.save(rentalCard)
		return RentalCardOutputDto.mapToDto(save)
	}
}

