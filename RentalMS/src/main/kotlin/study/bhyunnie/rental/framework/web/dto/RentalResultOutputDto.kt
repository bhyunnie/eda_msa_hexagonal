package study.bhyunnie.rental.framework.web.dto

import study.bhyunnie.rental.domain.model.RentalCard

class RentalResultOutputDto(
	val userId: String,
	val userName: String,
	val rentCount: Int,
	val totalLateFee: Long
) {
	companion object {
		fun mapToDto(rentalCard: RentalCard): RentalResultOutputDto {
			return RentalResultOutputDto(
				userId = rentalCard.member.id,
				userName = rentalCard.member.name,
				rentCount = rentalCard.rentalItemList.size,
				totalLateFee = rentalCard.lateFee.point
			)
		}
	}

}