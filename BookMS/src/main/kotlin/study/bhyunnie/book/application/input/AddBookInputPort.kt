package study.bhyunnie.book.application.input

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.bhyunnie.book.application.output.BookOutputPort
import study.bhyunnie.book.application.usecase.AddBookUsecase
import study.bhyunnie.book.domain.model.Book
import study.bhyunnie.book.domain.model.vo.Classification
import study.bhyunnie.book.domain.model.vo.Location
import study.bhyunnie.book.domain.model.vo.Source
import study.bhyunnie.book.framework.web.dto.BookInfoDto
import study.bhyunnie.book.framework.web.dto.BookOutputDto

@Service
@Transactional
class AddBookInputPort(
	private val bookOutputPort: BookOutputPort
):AddBookUsecase {
	override fun addBook(bookInfoDto: BookInfoDto): BookOutputDto {
		val book = Book.enterBook(
			title = bookInfoDto.title,
			description = bookInfoDto.description,
			author = bookInfoDto.author,
			classification = Classification.valueOf(bookInfoDto.classification),
			isbn = bookInfoDto.isbn,
			location = Location.valueOf(bookInfoDto.location),
			publicationDate = bookInfoDto.publicationDate,
			source = Source.valueOf(bookInfoDto.source)
		)
		val save = bookOutputPort.save(book)
		return BookOutputDto.mapToDto(save)
	}
}