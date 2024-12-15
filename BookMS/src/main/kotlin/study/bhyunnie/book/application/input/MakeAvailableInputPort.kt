package study.bhyunnie.book.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.book.application.output.BookOutputPort
import study.bhyunnie.book.application.usecase.MakeAvailableUsecase
import study.bhyunnie.book.framework.web.dto.BookOutputDto

@Service
@Transactional
class MakeAvailableInputPort(
	private val bookOutputPort: BookOutputPort
):MakeAvailableUsecase {
	override fun available(bookNo: Long): BookOutputDto {
		val loadBook = bookOutputPort.loadBook(bookNo)
		loadBook.makeAvailable()
		return BookOutputDto.mapToDto(loadBook)
	}
}