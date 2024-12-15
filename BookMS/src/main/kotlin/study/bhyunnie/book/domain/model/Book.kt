package study.bhyunnie.book.domain.model

import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import study.bhyunnie.book.domain.model.vo.BookDesc
import study.bhyunnie.book.domain.model.vo.BookStatus
import study.bhyunnie.book.domain.model.vo.Classification
import study.bhyunnie.book.domain.model.vo.Location
import study.bhyunnie.book.domain.model.vo.Source
import java.time.LocalDate

@Entity
class Book(
	@Id
	val no: Long = 0,
	val title: String,
	@Embedded
	val desc: BookDesc,
	val classification: Classification,
	var bookStatus: BookStatus,
	val location: Location,
) {

	fun makeAvailable():Book {
		this.bookStatus = BookStatus.AVAILABLE
		return this
	}

	fun makeUnavailable(): Book {
		this.bookStatus = BookStatus.UNAVAILABLE
		return this
	}

	companion object {
		fun enterBook(
			title:String,
			author: String,
			isbn: String,
			description: String,
			publicationDate: LocalDate,
			source: Source,
			classification: Classification,
			location:Location
		): Book {
			val bookDesc = BookDesc.createBookDesc(
				author=author, isbn = isbn, description = description, publicationDate = publicationDate, source = source
			)
			val book = Book(
				title = title,
				desc = bookDesc,
				classification = classification,
				location = location,
				bookStatus = BookStatus.ENTERED
			)
			return book
		}

		fun sample():Book {
			return enterBook(
				title = "이펙티브 코틀린",
				author = "마르친 모스칼라",
				isbn = "123121512",
				description = "안전성, 가독성, 코드 설계, 효율성을 향상시키기 위한 52가지 전략과 기법",
				publicationDate = LocalDate.now(),
				source = Source.SUPPLY,
				classification = Classification.COMPUTER,
				location = Location.PANGYO
			)
		}
	}
}