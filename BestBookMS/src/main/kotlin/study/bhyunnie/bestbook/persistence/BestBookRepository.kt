package study.bhyunnie.bestbook.persistence

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import study.bhyunnie.bestbook.domain.model.BestBook
import study.bhyunnie.bestbook.domain.model.Item
import java.util.Optional

@Repository
interface BestBookRepository: MongoRepository<BestBook, String> {
	fun findBestBookByItem(item:Item): Optional<BestBook>
}