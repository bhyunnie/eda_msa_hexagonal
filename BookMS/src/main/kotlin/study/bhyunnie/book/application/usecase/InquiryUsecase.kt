package study.bhyunnie.book.application.usecase

import study.bhyunnie.book.framework.web.dto.BookOutputDto

interface InquiryUsecase {
	fun getBookInfo(bookNo: Long): BookOutputDto
}