package com.tramalho.rest.spring.boot.math.service

import com.tramalho.rest.spring.boot.config.exception.ResourceNotFoundException
import com.tramalho.rest.spring.boot.math.model.Operation
import org.springframework.stereotype.Component
import kotlin.math.sqrt

@Component
class MathService {

    fun operation(a: String, b: String? = null, operation: Operation): Double {

        val op: (a: Double, b: Double) -> Double = when (operation) {
            Operation.SUM -> { v1, v2 -> v1 + v2 }
            Operation.MINUS -> { v1, v2 -> v1 - v2 }
            Operation.DIV -> { v1, v2 -> v1 / v2 }
            Operation.MULTIPLY -> { v1, v2 -> v1 * v2 }
            Operation.SQRT -> { v1, _ -> sqrt(v1) }
        }

        return execute(a, b, op)
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
            throw ResourceNotFoundException(e.message)
        }
    }
}