package study.bhyunnie.book.application.usecase

import study.bhyunnie.book.framework.web.dto.BookOutputDto

interface MakeUnAvailableUsecase {
	fun unAvailable(bookNo: Long): BookOutputDto
}