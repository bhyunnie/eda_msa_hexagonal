package study.bhyunnie.book.application.usecase

import study.bhyunnie.book.framework.web.dto.BookOutputDto

interface MakeAvailableUsecase {
	fun available(bookNo: Long): BookOutputDto
}