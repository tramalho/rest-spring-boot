package com.tramalho.rest.spring.boot.math

import com.tramalho.rest.spring.boot.math.model.Operation
import com.tramalho.rest.spring.boot.math.service.MathService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class MathController(private val mathService: MathService) {

    @GetMapping("/sum/{$NUMBER_ONE}/{$NUMBER_TWO}")
    fun sum(
        @PathVariable(value = NUMBER_ONE) numOne: String,
        @PathVariable(value = NUMBER_TWO) numTwo: String
    ): Double {
        return mathService.operation(numOne, numTwo, Operation.SUM)
    }

    @GetMapping("/div/{$NUMBER_ONE}/{$NUMBER_TWO}")
    fun div(
        @PathVariable(value = NUMBER_ONE) numOne: String,
        @PathVariable(value = NUMBER_TWO) numTwo: String
    ): Double {
        return mathService.operation(numOne, numTwo, Operation.DIV)
    }

    @GetMapping("/minus/{$NUMBER_ONE}/{$NUMBER_TWO}")
    fun minus(
        @PathVariable(value = NUMBER_ONE) numOne: String,
        @PathVariable(value = NUMBER_TWO) numTwo: String
    ): Double {
        return mathService.operation(numOne, numTwo, Operation.MINUS)
    }

    @GetMapping("/multiply/{$NUMBER_ONE}/{$NUMBER_TWO}")
    fun multiply(
        @PathVariable(value = NUMBER_ONE) numOne: String,
        @PathVariable(value = NUMBER_TWO) numTwo: String
    ): Double {
        return mathService.operation(numOne, numTwo, Operation.MULTIPLY)
    }

    @GetMapping("/sqrt/{$NUMBER_ONE}")
    fun sqrt(
        @PathVariable(value = NUMBER_ONE) numOne: String
    ): Double {
        return mathService.operation(numOne, operation =  Operation.SQRT)
    }

    private companion object {
        private const val NUMBER_ONE = "numberOne"
        private const val NUMBER_TWO = "numberTwo"
    }
}