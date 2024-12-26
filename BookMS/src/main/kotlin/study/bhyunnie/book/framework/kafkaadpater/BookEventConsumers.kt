package study.bhyunnie.book.framework.kafkaadpater

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import study.bhyunnie.book.application.usecase.MakeAvailableUsecase
import study.bhyunnie.book.application.usecase.MakeUnAvailableUsecase
import study.bhyunnie.book.domain.model.event.ItemRented
import study.bhyunnie.book.domain.model.event.ItemReturned

@Service
class BookEventConsumers(
	private val makeAvailableUsecase: MakeAvailableUsecase,
	private val makeUnAvailableUsecase: MakeUnAvailableUsecase
) {
	private val objectMapper = ObjectMapper()

	@KafkaListener(topics = ["#{'\${consumer.topic1.name}'.split(',')}"], groupId = "#{'\${consumer.groupId.name}'")
	fun consumeRent(record: ConsumerRecord<String,String>) {
		println("rental rent >>> " + record.value())
		val itemRented = objectMapper.readValue(record.value(), ItemRented::class.java)
		makeUnAvailableUsecase.unAvailable(itemRented.item.no.toLong())
	}

	@KafkaListener(topics = ["#{'\${consumer.topic1.name}'.split(',')}"], groupId = "#{'\${consumer.groupId.name}'")
	fun consumeReturn(record:ConsumerRecord<String,String>) {
		println("rental return >>> " + record.value())
		val itemReturned = objectMapper.readValue(record.value(), ItemReturned::class.java)
		makeAvailableUsecase.available(itemReturned.item.no.toLong())
	}
}