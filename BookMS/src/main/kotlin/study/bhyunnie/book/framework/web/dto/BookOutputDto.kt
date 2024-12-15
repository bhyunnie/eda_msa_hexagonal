package study.bhyunnie.book.framework.web.dto

import study.bhyunnie.book.domain.model.Book

class BookOutputDto(
	val bookNo: Long,
	val bookTitle: String,
	val bookStatus: String,
) {
	companion object {
		fun mapToDto(book: Book): BookOutputDto {
		return BookOutputDto(
		bookNo = book.no,
		bookTitle = book.title,
		bookStatus = book.bookStatus.name
		)
	}
}
}