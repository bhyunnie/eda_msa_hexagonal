package study.bhyunnie.bestbook.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import study.bhyunnie.bestbook.domain.BestBookService
import study.bhyunnie.bestbook.domain.event.ItemRented

@Service
class BestBookEventConsumers(
	private val bestBookService: BestBookService
) {
	private val objectMapper = ObjectMapper()

	@KafkaListener(topics = ["rental_rent"], groupId = "bestBook")
	fun consume(record:ConsumerRecord<String,String>) {
		val itemRented = objectMapper.readValue(record.value(), ItemRented::class.java)
		bestBookService.dealBestBook(itemRented.item)
	}
}