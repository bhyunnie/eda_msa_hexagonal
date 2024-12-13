package study.bhyunnie.rental.framework.web.dto

import study.bhyunnie.rental.model.RentalItem
import java.time.LocalDate

class RentItemOutputDto(
	val itemNumber: Int,
	val itemTitle: String,
	val rentDate: LocalDate,
	val overdueDate: LocalDate
) {
	companion object {
		fun mapToDto(rentalItem: RentalItem): RentItemOutputDto {
			return RentItemOutputDto(
				itemNumber = rentalItem.item.no,
				itemTitle = rentalItem.item.title,
				rentDate = rentalItem.rentDate,
				overdueDate = rentalItem.overdueDate
			)
		}
	}
}