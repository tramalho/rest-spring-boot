package com.tramalho.rest.spring.boot.controller

import com.tramalho.rest.spring.boot.exception.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import kotlin.math.sqrt

@RestController
class MathController {

    @GetMapping("/sum/{$NUMBER_ONE}/{$NUMBER_TWO}")
    fun sum(
        @PathVariable(value = NUMBER_ONE) numOne: String,
        @PathVariable(value = NUMBER_TWO) numTwo: String
    ): Double {
        return execute(numOne, numTwo) { a, b -> a + b }
    }

    @GetMapping("/div/{$NUMBER_ONE}/{$NUMBER_TWO}")
    fun div(
        @PathVariable(value = NUMBER_ONE) numOne: String,
        @PathVariable(value = NUMBER_TWO) numTwo: String
    ): Double {
        return execute(numOne, numTwo) { a, b -> a / b }
    }

    @GetMapping("/minus/{$NUMBER_ONE}/{$NUMBER_TWO}")
    fun minus(
        @PathVariable(value = NUMBER_ONE) numOne: String,
        @PathVariable(value = NUMBER_TWO) numTwo: String
    ): Double {

        val op: (a: Double, b: Double) -> Double = { a, b -> a - b }
        return execute(numOne, numTwo, op)
    }

    @GetMapping("/multiply/{$NUMBER_ONE}/{$NUMBER_TWO}")
    fun multiply(
        @PathVariable(value = NUMBER_ONE) numOne: String,
        @PathVariable(value = NUMBER_TWO) numTwo: String
    ): Double {

        val op = { a: Double, b: Double -> a * b }

        return execute(numOne, numTwo, op)
    }

    @GetMapping("/sqrt/{$NUMBER_ONE}")
    fun sqrt(
        @PathVariable(value = NUMBER_ONE) numOne: String
    ): Double {

        val op = { a: Double, _: Double -> sqrt(a) }

        return execute(numOne, operation = op)
    }

    private fun execute(
        numOne: String,
        numTwo: String? = null,
        operation: (a: Double, b: Double) -> Double
    ): Double {
        try {
            val numberOne = numOne.toDouble()
            val numberTwo = numTwo?.toDouble() ?: 0.0

            return operation(numberOne, numberTwo)
        } catch (e: NumberFormatException) {
            throw UnsupportedMathOperationException(e.message)
        }
    }

    private companion object {
        private const val NUMBER_ONE = "numberOne"
        private const val NUMBER_TWO = "numberTwo"
    }
}