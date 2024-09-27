package com.example.hyperloop.downloads

enum class DownloadStatus(val status: String) {
    STARTED("STARTED"),
    DOWNLOADING("DOWNLOADING"),
    SENDING("SENDING"),
    DONE("DONE"),
    FAILED("FAILED")
}