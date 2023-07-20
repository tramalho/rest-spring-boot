package com.tramalho.rest.spring.boot.controller

import com.tramalho.rest.spring.boot.model.Greeting
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting {
        return Greeting(COUNTER.incrementAndGet(), String.format(TEMPLATE, name))
    }

    private companion object {
        private const val TEMPLATE = "Hello, %s"
        private val COUNTER = AtomicLong()
    }
}