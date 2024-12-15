package study.bhyunnie.book.domain.model.vo

import jakarta.persistence.Embeddable
import java.time.LocalDate

@Embeddable
data class BookDesc(
	val description: String,
	val author: String,
	val isbn: String, // 도서 출간 코드
	val publicationDate: LocalDate,
	val source: Source
) {
	companion object {
		fun createBookDesc(
			description: String,
			author:String,
			isbn: String,
			publicationDate: LocalDate,
			source: Source
		):BookDesc {
			return BookDesc(
				description = description,
				author = author,
				isbn = isbn,
				publicationDate = publicationDate,
				source = source
			)
		}

		fun sample():BookDesc {
			return BookDesc(
				description = "엔터프라이즈 아키텍처 패턴을 잘 설명해주는 도서",
				author = "마틴파울러",
				isbn = "12321312123",
				publicationDate = LocalDate.now(),
				source = Source.SUPPLY
			)
		}
	}
}