package com.tramalho.rest.spring.boot.controller

import com.tramalho.rest.spring.boot.exception.UnsupportedMathOperationException
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

        try {
            val numberOne = numOne.toDouble()
            val numberTwo = numTwo.toDouble()

            return numberOne + numberTwo
        } catch (e: NumberFormatException) {
            throw UnsupportedMathOperationException(e.message)
        }
    }
}