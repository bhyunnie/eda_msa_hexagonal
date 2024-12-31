package study.bhyunnie.member.framework.kafkaadapter

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import study.bhyunnie.member.application.usecase.SavePointUsecase
import study.bhyunnie.member.application.usecase.UsePointUsecase
import study.bhyunnie.member.domain.model.event.ItemRented
import study.bhyunnie.member.domain.model.event.ItemReturned
import study.bhyunnie.member.domain.model.event.OverdueCleared

@Service
class MemberEventConsumers(
	private val savePointUsecase: SavePointUsecase,
	private val usePointUsecase: UsePointUsecase
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
		println("rental clear >>> " + record.value())
		val overdueCleared = objectMapper.readValue(record.value(), OverdueCleared::class.java)
		usePointUsecase.usePoint(overdueCleared.idName, overdueCleared.point)
	}
}