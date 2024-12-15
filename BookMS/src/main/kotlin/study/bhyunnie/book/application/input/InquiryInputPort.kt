package study.bhyunnie.book.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.book.application.output.BookOutputPort
import study.bhyunnie.book.application.usecase.InquiryUsecase
import study.bhyunnie.book.framework.web.dto.BookOutputDto

@Service
@Transactional
class InquiryInputPort(
	private val bookOutputPort: BookOutputPort
):InquiryUsecase {
	override fun getBookInfo(bookNo: Long): BookOutputDto {
		val loadBook = bookOutputPort.loadBook(bookNo)
		return BookOutputDto.mapToDto(loadBook)
	}
}