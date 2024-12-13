package study.bhyunnie.eda_msa_hexagonal.framework.web.dto

import study.bhyunnie.eda_msa_hexagonal.domain.model.ReturnItem
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