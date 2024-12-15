package study.bhyunnie.book.framework.jpaadapter

import org.springframework.stereotype.Repository
import study.bhyunnie.book.application.output.BookOutputPort
import study.bhyunnie.book.domain.model.Book

@Repository
class BookAdapter(
	private val bookRepository: BookRepository
): BookOutputPort {
	override fun loadBook(bookNo: Long): Book {
		return bookRepository.findById(bookNo).get()
	}

	override fun save(book: Book): Book {
		return bookRepository.save(book)
	}
}