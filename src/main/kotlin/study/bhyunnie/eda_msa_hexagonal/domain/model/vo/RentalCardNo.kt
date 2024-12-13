package study.bhyunnie.eda_msa_hexagonal.domain.model.vo

import jakarta.persistence.Embeddable
import java.io.Serializable
import java.time.LocalDate
import java.util.*

@Embeddable
data class RentalCardNo(
	val no: String
): Serializable {
	companion object {
		fun createRentalCardNo(): RentalCardNo {
			val uuid = UUID.randomUUID()
			val year = LocalDate.now().year.toString()
			val id = "$year-$uuid"
			return RentalCardNo(
				no = id
			)
		}

		fun sample(): RentalCardNo {
			return RentalCardNo.createRentalCardNo()
		}

		@JvmStatic fun main(args: Array<String>) {
			println(sample())
		}
	}
}