package study.bhyunnie.book.config

import com.fasterxml.jackson.databind.JsonSerializer

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
@EnableKafka
class KafkaConfig {
	@Value("\${kafka.bootstrap-servers}")
	private var bootstrapServers: String = ""

	@Bean
	fun <T> producerFactory(): ProducerFactory<String, T> {
		val config:MutableMap<String, Any> = HashMap()
		config[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
		config[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
		config[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
		return DefaultKafkaProducerFactory(config)
	}

	@Bean
	fun <T> kafkaTemplate(): KafkaTemplate<String, T> {
		return KafkaTemplate(producerFactory())
	}
}