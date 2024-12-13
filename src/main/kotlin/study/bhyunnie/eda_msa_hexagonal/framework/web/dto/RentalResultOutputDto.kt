package study.bhyunnie.eda_msa_hexagonal.framework.web.dto

import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard
import study.bhyunnie.eda_msa_hexagonal.domain.model.vo.LateFee

class RentalResultOutputDto(
	val userId: String,
	val userName: String,
	val rentCount: Int,
	val totalLateFee: Long
) {
	companion object {
		fun mapToDto(rentalCard: RentalCard):RentalResultOutputDto {
			return RentalResultOutputDto(
				userId = rentalCard.member.id,
				userName = rentalCard.member.name,
				rentCount = rentalCard.rentalItemList.size,
				totalLateFee = rentalCard.lateFee.point
			)
		}
	}

}