package study.bhyunnie.rental.framework.kafkaAdapter

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import study.bhyunnie.rental.application.usecase.CompensationUsecase
import study.bhyunnie.rental.domain.model.event.EventResult
import study.bhyunnie.rental.domain.model.event.EventType

class RentalEventConsumers(
    private val compensationUsecase:CompensationUsecase
) {
    private val objectMapper: ObjectMapper = ObjectMapper()
    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["#{'\${consumer.topic1.name}'.split(',')}"], groupId = "\${consumer.groupId.name}")
    fun consumerRental(record:ConsumerRecord<String,String>) {
        try {
            println("Consumer Record >>> ${record.value()}")

            val eventResult = objectMapper.readValue(record.value(), EventResult::class.java)
            val idName = eventResult.idName
            val item = eventResult.item
            val point = eventResult.point

            if (!eventResult.success) {
                val eventType = eventResult.eventType
                println("eventType = $eventType")

                when (eventType) {
                    EventType.RENT -> {
                        compensationUsecase.cancelRentItem(idName, item)
                        println("대여 취소 보상 트랜잭션 실행")
                    }
                    EventType.RETURN -> {
                        compensationUsecase.cancelReturnItem(idName, item, point)
                        println("반납 취소 보상 트랜잭션 실행")
                    }
                    EventType.OVERDUE -> {
                        compensationUsecase.cancelMakeAvailableRental(idName, point)
                        println("연체 해처 처리취소 보상트랜잭션 실행")
                    }
                    else -> {

                    }
                }
            } else {
                println("eventResult.success = $eventResult.success")
            }
        } catch (e:Exception) {

        }
    }
}