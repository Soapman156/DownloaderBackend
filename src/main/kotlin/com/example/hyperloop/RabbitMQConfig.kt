package com.example.hyperloop

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Value("\${spring.rabbitmq.request-queue.routing-key}")
    private lateinit var requestQueueName: String

    @Value("\${spring.rabbitmq.status-queue.routing-key}")
    private lateinit var downloadStatusQueueName: String

    @Bean
    fun jsonMessageConverter(objectMapper: ObjectMapper): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter(objectMapper)
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerModule(KotlinModule.Builder().build())
            registerModule(JavaTimeModule()) // Add support for Java 8 date/time types
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // Ensure ISO format
        }
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory, jsonMessageConverter: Jackson2JsonMessageConverter): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jsonMessageConverter
        return rabbitTemplate
    }

    @Bean
    fun downLoadRequestQueue(): Queue {
        return Queue(requestQueueName, true)
    }

    @Bean
    fun downloadStatusQueue(): Queue {
        return Queue(downloadStatusQueueName, true)  // durable queue
    }
}