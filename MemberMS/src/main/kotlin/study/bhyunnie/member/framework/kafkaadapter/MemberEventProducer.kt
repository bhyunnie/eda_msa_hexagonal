package study.bhyunnie.member.framework.kafkaadapter

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import study.bhyunnie.member.domain.model.event.EventResult
import java.util.concurrent.CompletableFuture

@Service
class MemberEventProducer(
    private val kafkaTemplate: KafkaTemplate<String,EventResult>
) {
    @Value("\${producers.topic1.name}")
    private var TOPIC = ""
    private val logger = LoggerFactory.getLogger(javaClass)

    fun occurEvent(result:EventResult) {
        val future: CompletableFuture<SendResult<String, EventResult>> = kafkaTemplate.send(TOPIC, result)

        future.whenComplete { success, exception ->
            if (exception == null) {
                logger.info("{}", success.producerRecord.value())
            } else {
                logger.error("{}", success.producerRecord.value())
                logger.error("{}", exception.message)
            }
        }
    }
}