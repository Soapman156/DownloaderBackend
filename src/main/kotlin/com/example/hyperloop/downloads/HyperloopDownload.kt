package com.example.hyperloop.downloads

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
data class HyperloopDownload(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment strategy for the ID
    val id: Int = 0,

    @Column(nullable = false)
    val type: String,

    @Column(nullable = false)
    val dependency: String,

//    @Column(nullable = false)
//    val version: String,

    @Column(nullable = false)
    val status: DownloadStatus = DownloadStatus.STARTED,

    @Column(nullable = false)
    val date: LocalDateTime = LocalDateTime.now()
)
