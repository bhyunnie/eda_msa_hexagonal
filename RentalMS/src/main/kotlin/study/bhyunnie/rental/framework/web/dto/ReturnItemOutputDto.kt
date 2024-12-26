package study.bhyunnie.rental.framework.web.dto

import study.bhyunnie.rental.domain.model.ReturnItem
import java.time.LocalDate

class ReturnItemOutputDto(
	val itemNumber: Int,
	val itemTitle: String,
	val returnDate: LocalDate
) {
	companion object {
		fun mapToDto(returnItem: ReturnItem): ReturnItemOutputDto {
			return ReturnItemOutputDto(
				itemNumber = returnItem.rentalItem.item.no,
				itemTitle = returnItem.rentalItem.item.title,
				returnDate = returnItem.returnDate
			)
		}
	}
}