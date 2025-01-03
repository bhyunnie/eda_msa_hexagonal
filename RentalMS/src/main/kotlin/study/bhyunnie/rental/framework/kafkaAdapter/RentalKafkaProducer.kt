package study.bhyunnie.rental.framework.kafkaAdapter


import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import study.bhyunnie.rental.application.output.EventOutputPort
import study.bhyunnie.rental.domain.model.event.ItemRented
import study.bhyunnie.rental.domain.model.event.ItemReturned
import study.bhyunnie.rental.domain.model.event.OverdueCleared
import study.bhyunnie.rental.domain.model.event.PointUseCommand
import java.util.concurrent.CompletableFuture
import kotlin.math.log

@Service
class RentalKafkaProducer(
	private val itemRentedKafkaTemplate: KafkaTemplate<String, ItemRented>,
	private val itemReturnedKafkaTemplate: KafkaTemplate<String, ItemReturned>,
	private val overdueClearedKafkaTemplate: KafkaTemplate<String, OverdueCleared>,
	private val pointKafkaTemplate: KafkaTemplate<String, PointUseCommand>
): EventOutputPort {
	@Value("\${producers.topic1.name}")
	private var TOPIC_RENT:String = ""

	@Value("\${producers.topic2.name}")
	private var TOPIC_RETURN:String = ""

	@Value("\${producers.topic3.name}")
	private var TOPIC_CLEAR:String = ""

	@Value("\${producers.topic4.name}")
	private var TOPIC_POINT:String = ""

	private val logger = LoggerFactory.getLogger(javaClass)

	override fun occurRentalEvent(itemRented: ItemRented) {
		val future: CompletableFuture<SendResult<String,ItemRented>> = itemRentedKafkaTemplate.send(TOPIC_RENT, itemRented)

		future.whenComplete { success, exception ->
			if (exception == null) {
				logger.info("{}",success.producerRecord.value())
			} else {
				logger.error("{}", success.producerRecord.value())
				logger.error("{}", exception.message)
			}
		}
	}

	override fun occurReturnEvent(itemReturned: ItemReturned) {
		val future: CompletableFuture<SendResult<String,ItemReturned>> = itemReturnedKafkaTemplate.send(TOPIC_RETURN, itemReturned)

		future.whenComplete { success, exception ->
			if (exception == null) {
				logger.info("{}",success.producerRecord.value())
			} else {
				logger.error("{}", success.producerRecord.value())
				logger.error("{}", exception.message)
			}
		}
	}

	override fun occurOverdueClearedEvent(overdueCleared: OverdueCleared) {
		val future: CompletableFuture<SendResult<String,OverdueCleared>> = overdueClearedKafkaTemplate.send(TOPIC_CLEAR, overdueCleared)

		future.whenComplete { success, exception ->
			if (exception == null) {
				logger.info("{}",success.producerRecord.value())
			} else {
				logger.error("{}", success.producerRecord.value())
				logger.error("{}", exception.message)
			}
		}
	}

	override fun occurPointUseCommand(pointUseCommand: PointUseCommand) {
		val future: CompletableFuture<SendResult<String,PointUseCommand>> = pointKafkaTemplate.send(TOPIC_CLEAR, pointUseCommand)

		future.whenComplete { success, exception ->
			if (exception == null) {
				logger.info("{}",success.producerRecord.value())
			} else {
				logger.error("{}", success.producerRecord.value())
				logger.error("{}", exception.message)
			}
		}
	}
}