package com.tramalho.rest.spring.boot.controller

import com.tramalho.rest.spring.boot.model.Greeting
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random

@RestController
class GreetingController: ControllerConfig<Greeting> {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): ResponseEntity<Greeting> {

        val nextBoolean = Random.nextBoolean()

        if (nextBoolean) {
            createAppParams()
        } else {
            createAppParamsWithAudience()
        }

        val greeting = Greeting(COUNTER.incrementAndGet(), String.format(TEMPLATE, name))

        return createResponseEntity(greeting, nextBoolean)
    }

    private companion object {
        private const val TEMPLATE = "Hello, %s"
        private val COUNTER = AtomicLong()
    }
}