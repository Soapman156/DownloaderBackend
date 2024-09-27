package com.example.hyperloop.exceptions

class DownloadNotFoundException(id: Int) : RuntimeException("Download with id $id not found")