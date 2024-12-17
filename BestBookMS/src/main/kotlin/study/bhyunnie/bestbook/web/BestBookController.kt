package study.bhyunnie.bestbook.web

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import study.bhyunnie.bestbook.domain.BestBookService
import study.bhyunnie.bestbook.domain.model.BestBook
import java.util.Optional

@RestController
class BestBookController(
	private val bestBookService: BestBookService
) {
	@GetMapping("/api/books")
	fun getAllBooks(): ResponseEntity<List<BestBook>> {
		val books: List<BestBook> = bestBookService.getAllBooks()
		return ResponseEntity(books, HttpStatus.OK)
	}

	@GetMapping("/api/books/{id}")
	fun getBookById(
		@PathVariable id: String
	):ResponseEntity<BestBook?> {
		val bookOptional: Optional<BestBook> = bestBookService.getBookById(id)
		return ResponseEntity(bookOptional.orElseGet { null }, HttpStatus.OK)
	}

	@PostMapping("/api/books")
	fun createBook(
		@RequestBody book: BestBook
	): ResponseEntity<BestBook> {
		val createdBook = bestBookService.saveBook(book)
		return ResponseEntity(createdBook, HttpStatus.CREATED)
	}

	@PutMapping("/api/books/{id}")
	fun updateBook(
		@PathVariable id: String,
		@RequestBody bestBook: BestBook
	):ResponseEntity<BestBook> {
		val updatedBook = bestBookService.updateBook(id, bestBook)
		return ResponseEntity(updatedBook, HttpStatus.OK)
	}
}