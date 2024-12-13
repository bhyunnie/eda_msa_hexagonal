package study.bhyunnie.eda_msa_hexagonal.framework.web.dto

import study.bhyunnie.eda_msa_hexagonal.domain.model.RentalCard

class RentalCardOutputDto(
	val rentalCardId: String,
	val memberId: String,
	val memberName: String,
	val rentStatus: String,
	val totalLateFee: Long,
	val totalRentCount: Long,
	val totalReturnCount: Long,
	val totalOverdueCount: Long
) {
	companion object {
		fun mapToDto(rentalCard: RentalCard):RentalCardOutputDto {
			return RentalCardOutputDto(
				rentalCardId = rentalCard.rentalCardNo.no,
				memberId = rentalCard.member.id,
				memberName = rentalCard.member.name,
				rentStatus = rentalCard.rentStatus.name,
				totalLateFee = rentalCard.lateFee.point,
				totalRentCount = rentalCard.rentalItemList.size.toLong(),
				totalReturnCount = rentalCard.returnItemList.size.toLong(),
				totalOverdueCount = rentalCard.rentalItemList.filter { it.overdue }.size.toLong()
			)
		}
	}
}