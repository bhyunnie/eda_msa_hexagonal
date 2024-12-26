package study.bhyunnie.rental.domain.model

import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import java.time.LocalDate

@Embeddable
data class ReturnItem(
	@Embedded
	val rentalItem: RentalItem,
	val returnDate: LocalDate,
) {
	companion object {
		fun createReturnItem(rentalItem: RentalItem): ReturnItem {
			return ReturnItem(
				rentalItem = rentalItem,
				returnDate = LocalDate.now(),
			)
		}

		fun sample(): ReturnItem {
			return ReturnItem(
				rentalItem = RentalItem.sample(),
				returnDate = LocalDate.now()
			)
		}

		@JvmStatic
		fun main(args: Array<String>) {
			println(sample())
		}
	}
}