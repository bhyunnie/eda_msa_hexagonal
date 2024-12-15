package study.bhyunnie.book.application.usecase

import study.bhyunnie.book.framework.web.dto.BookInfoDto
import study.bhyunnie.book.framework.web.dto.BookOutputDto

interface AddBookUsecase {
	fun addBook(bookInfoDto: BookInfoDto): BookOutputDto
}