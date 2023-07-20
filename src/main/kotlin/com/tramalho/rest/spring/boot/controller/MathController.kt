package com.tramalho.rest.spring.boot.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MathController {

    @GetMapping("/sum/{numOne}/{numTwo}")
    fun sum(
        @PathVariable(value = "numOne") numOne: String,
        @PathVariable(value = "numTwo") numTwo: String
        ): Double {

        return Double.MAX_VALUE
    }
}