package com.tramalho.rest.spring.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestSpringBootApplication

fun main(args: Array<String>) {
	runApplication<RestSpringBootApplication>(*args)
}
