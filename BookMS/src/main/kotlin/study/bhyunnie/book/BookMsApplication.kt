package study.bhyunnie.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookMsApplication

fun main(args: Array<String>) {
	runApplication<BookMsApplication>(*args)
}
