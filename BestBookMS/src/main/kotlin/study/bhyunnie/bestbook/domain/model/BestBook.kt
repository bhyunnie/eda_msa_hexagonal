package study.bhyunnie.bestbook.domain.model

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
class BestBook(
	@Id
	val id:String,
	var item: Item,
	var rentCount: Long
) {
	fun increaseBestBookCount():Long {
		this.rentCount += 1
		return this.rentCount
	}

	companion object {
		fun registerBestBook(item:Item):BestBook {
			val uuid = UUID.randomUUID()
			return BestBook(
				id = uuid.toString(),
				item = item,
				rentCount = 0
			)
		}
	}
}