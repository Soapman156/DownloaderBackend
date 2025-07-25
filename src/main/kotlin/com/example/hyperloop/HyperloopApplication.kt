package com.example.hyperloop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class HyperloopApplication

fun main(args: Array<String>) {
	runApplication<HyperloopApplication>(*args)
}
