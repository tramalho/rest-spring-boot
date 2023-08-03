package com.tramalho.rest.spring.boot.greeting

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

interface ControllerConfig<T> {

    fun createAppParams(): Pair<String, String> {
        return Pair("a", "b").apply {
            println(this)
        }
    }

    fun createAppParamsWithAudience(): Pair<String, String> {
        return Pair("a", "c").apply {
            println(this)
        }
    }

    fun createResponseEntity(objects: T, nextBoolean: Boolean): ResponseEntity<T> {
        println(objects.toString())

        val httpStatus = when (nextBoolean) {
            true -> HttpStatus.TEMPORARY_REDIRECT
            else -> HttpStatus.OK
        }

        return ResponseEntity(objects, httpStatus)
    }
}
