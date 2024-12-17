package study.bhyunnie.bestbook.domain

import org.springframework.stereotype.Service
import study.bhyunnie.bestbook.domain.model.BestBook
import study.bhyunnie.bestbook.domain.model.Item
import study.bhyunnie.bestbook.persistence.BestBookRepository
import java.util.Optional

@Service
class BestBookService(
	private val bestBookRepository: BestBookRepository
) {
	fun getAllBooks():List<BestBook> {
		return bestBookRepository.findAll()
	}

	fun getBookById(id:String): Optional<BestBook> {
		return bestBookRepository.findById(id)
	}

	fun dealBestBook(item: Item) {
		val bestBook:BestBook = bestBookRepository.findBestBookByItem(item).orElseGet {
			BestBook.registerBestBook(item)
		}
		bestBook.increaseBestBookCount()
		saveBook(bestBook)
	}

	fun updateBook(id: String, book:BestBook): BestBook? {
		val existingBookOptional:Optional<BestBook> = bestBookRepository.findById(id)
		if (existingBookOptional.isPresent) {
			val existingBook = existingBookOptional.get()
			existingBook.item = book.item
			existingBook.rentCount = book.rentCount
			return bestBookRepository.save(existingBook)
		}
		return null
	}

	fun deleteBook(id:String): Boolean {
		val bookOptional = bestBookRepository.findById(id)
		if (bookOptional.isPresent) {
			bestBookRepository.delete(bookOptional.get())
			return true
		}
		return false
	}

	fun saveBook(bestBook: BestBook):BestBook {
		return bestBookRepository.save(bestBook)
	}
}