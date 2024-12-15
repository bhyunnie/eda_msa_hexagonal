package study.bhyunnie.book.application.output

import org.springframework.stereotype.Repository
import study.bhyunnie.book.domain.model.Book

@Repository
interface BookOutputPort {
	fun loadBook(bookNo: Long): Book
	fun save(book:Book): Book
}