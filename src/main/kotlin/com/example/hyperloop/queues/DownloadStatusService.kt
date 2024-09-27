package com.example.hyperloop.queues

import com.example.hyperloop.downloads.DownloadService
import com.example.hyperloop.downloads.HyperloopDownload
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class DownloadStatusService(private val downloadService: DownloadService) {

    @RabbitListener(queues = ["private.hyperloop.download_status"])
    fun receiveMessage(statusUpdate: HyperloopDownload) {
        println("Received download status: $statusUpdate")
        this.downloadService.updateStatusOfDownload(statusUpdate)
    }
}
