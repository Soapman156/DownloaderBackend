package com.example.hyperloop.queues

import com.example.hyperloop.downloads.HyperloopDownload
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RabbitMQSender(private val rabbitTemplate: RabbitTemplate) {

    @Value("\${spring.rabbitmq.request-queue.routing-key}")
    private lateinit var queueName: String

    fun sendDownloadRequest(downloadRequest: HyperloopDownload) {
        println("Sending download request to RabbitMQ: $downloadRequest")
        rabbitTemplate.convertAndSend(queueName, downloadRequest)
    }
}