package study.bhyunnie.book.framework.jpaadapter

import org.springframework.data.jpa.repository.JpaRepository
import study.bhyunnie.book.domain.model.Book

interface BookRepository:JpaRepository<Book,Long> {}