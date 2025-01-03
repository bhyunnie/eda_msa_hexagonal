package study.bhyunnie.book.framework.kafkaadpater

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import study.bhyunnie.book.application.usecase.MakeAvailableUsecase
import study.bhyunnie.book.application.usecase.MakeUnAvailableUsecase
import study.bhyunnie.book.domain.model.event.EventResult
import study.bhyunnie.book.domain.model.event.EventType
import study.bhyunnie.book.domain.model.event.ItemRented
import study.bhyunnie.book.domain.model.event.ItemReturned

@Service
class BookEventConsumers(
	private val makeAvailableUsecase: MakeAvailableUsecase,
	private val makeUnAvailableUsecase: MakeUnAvailableUsecase,
	private val eventProducer: BookEventProducer
) {
	private val objectMapper = ObjectMapper()

	@KafkaListener(topics = ["#{'\${consumer.topic1.name}'.split(',')}"], groupId = "#{'\${consumer.groupId.name}'}")
	fun consumeRent(record: ConsumerRecord<String,String>) {
		println("rental rent >>> " + record.value())
		val itemRented = objectMapper.readValue(record.value(), ItemRented::class.java)

		val eventResult = EventResult(
			eventType = EventType.RETURN,
			idName = itemRented.idName,
			item = itemRented.item,
			point = itemRented.point,
			success = true
		)

		try {
			println("전송 받은 값 >>> ${record.value()}")
			makeUnAvailableUsecase.unAvailable(itemRented.item.no.toLong())
		} catch (e: Exception) {
			println("도서 상태 불일치")
			eventResult.success = false
		}

		eventProducer.occurEvent(eventResult)
	}

	@KafkaListener(topics = ["#{'\${consumer.topic1.name}'.split(',')}"], groupId = "#{'\${consumer.groupId.name}'}")
	fun consumeReturn(record:ConsumerRecord<String,String>) {
		println("rental return >>> " + record.value())
		val itemReturned = objectMapper.readValue(record.value(), ItemReturned::class.java)
		makeAvailableUsecase.available(itemReturned.item.no.toLong())
	}
}