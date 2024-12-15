package study.bhyunnie.book.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.book.application.output.BookOutputPort
import study.bhyunnie.book.application.usecase.MakeUnAvailableUsecase
import study.bhyunnie.book.framework.web.dto.BookOutputDto

@Service
@Transactional
class MakeUnAvailableInputPort(
	private val bookOutputPort: BookOutputPort
):MakeUnAvailableUsecase {
	override fun unAvailable(bookNo: Long): BookOutputDto {
		val loadBook = bookOutputPort.loadBook(bookNo)
		loadBook.makeUnavailable()
		return BookOutputDto.mapToDto(loadBook)
	}
}