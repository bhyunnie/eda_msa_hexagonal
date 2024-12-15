package study.bhyunnie.book.framework.web.dto

import java.time.LocalDate

class BookInfoDto(
	val title:String,
	val description: String,
	val author: String,
	val isbn: String,
	val publicationDate: LocalDate,
	val source: String,
	val classification: String,
	val location: String,
)