package study.bhyunnie.member.framework.kafkaadapter

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import study.bhyunnie.member.application.usecase.SavePointUsecase
import study.bhyunnie.member.application.usecase.UsePointUsecase
import study.bhyunnie.member.domain.model.event.*

@Service
class MemberEventConsumers(
	private val savePointUsecase: SavePointUsecase,
	private val usePointUsecase: UsePointUsecase,
	private val eventProducer: MemberEventProducer
) {
	private val objectMapper = ObjectMapper()

	@KafkaListener(topics = ["#{'\${consumer.topic1.name}'.split(',')}"], groupId = "#{'\${consumer.groupId.name}'}")
	fun consumeRent(record: ConsumerRecord<String, String>) {
		println("rental rent >>> " + record.value())
		val itemRented = objectMapper.readValue(record.value(), ItemRented::class.java)
		usePointUsecase.usePoint(itemRented.idName, itemRented.point)
	}

	@KafkaListener(topics = ["#{'\${consumer.topic1.name}'.split(',')}"], groupId = "#{'\${consumer.groupId.name}'}")
	fun consumeReturn(record: ConsumerRecord<String, String>) {
		println("rental return >>> " + record.value())
		val itemReturned = objectMapper.readValue(record.value(), ItemReturned::class.java)
		savePointUsecase.savePoint(itemReturned.idName, itemReturned.point)
	}

	@KafkaListener(topics = ["#{'\${consumer.topic3.name}'.split(',')}"], groupId = "#{'\${consumer.groupId.name}'}")
	fun consumeClear(record: ConsumerRecord<String, String>) {
		val overdueCleared = objectMapper.readValue(record.value(), OverdueCleared::class.java)
		val eventResult = EventResult(
			eventType = EventType.OVERDUE,
			idName = overdueCleared.idName,
			point = overdueCleared.point,
			item = Item.sample(),
			success = false
		)

		println(record.value())

		try {
			usePointUsecase.usePoint(overdueCleared.idName,overdueCleared.point)
			eventResult.success = true
		} catch (e:Exception) {
			eventResult.success = false
		}
		eventProducer.occurEvent(eventResult)
	}

	@KafkaListener(topics = ["#{'\${consumer.topic4.name}'.split(',')}"])
	fun consumeUsePoint(record: ConsumerRecord<String,String>) {
		println(record.value())
		val pointUseCommand = objectMapper.readValue(record.value(), PointUseCommand::class.java)
		usePointUsecase.usePoint(pointUseCommand.idName, pointUseCommand.point)
	}
}