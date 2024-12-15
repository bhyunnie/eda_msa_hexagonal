package study.bhyunnie.book.framework.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import study.bhyunnie.book.application.input.AddBookInputPort
import study.bhyunnie.book.application.usecase.AddBookUsecase
import study.bhyunnie.book.application.usecase.InquiryUsecase
import study.bhyunnie.book.application.usecase.MakeAvailableUsecase
import study.bhyunnie.book.application.usecase.MakeUnAvailableUsecase
import study.bhyunnie.book.framework.web.dto.BookInfoDto
import study.bhyunnie.book.framework.web.dto.BookOutputDto

@RestController
class BookController(
	private val addBookUsecase: AddBookUsecase,
	private val inquiryUsecase: InquiryUsecase,
	private val makeAvailableUsecase: MakeAvailableUsecase,
	private val makeUnAvailableUsecase: MakeUnAvailableUsecase,
) {

	@PostMapping("/api/book")
	fun createBook(
		@RequestBody bookInfoDto: BookInfoDto
	): ResponseEntity<BookOutputDto> {
		val bookOutputDto = addBookUsecase.addBook(bookInfoDto)
		return ResponseEntity<BookOutputDto>(
			bookOutputDto,
			HttpStatus.CREATED
		)
	}

	@GetMapping("/api/book/{no}")
	fun getBookNo(
		@PathVariable no: String
	):ResponseEntity<BookOutputDto> {
		val bookInfo = inquiryUsecase.getBookInfo(no.toLong())
		return ResponseEntity<BookOutputDto>(
			bookInfo,
			HttpStatus.OK
		)
	}
}