package com.example.hyperloop.downloads

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class DownloadController(private val downloadService: DownloadService) {

    @GetMapping("downloads")
    @CrossOrigin(origins = ["*"])
    fun getAllDownloads(): List<HyperloopDownload> = this.downloadService.getAllDownloads()

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("downloads")
    @CrossOrigin(origins = ["*"])
    fun handleIncomingDownloadRequest(@RequestBody downloadRequest: FrontendDownloadRequest) =
        this.downloadService.saveDownloadRequest(downloadRequest)
}