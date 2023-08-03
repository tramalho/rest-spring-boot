package com.tramalho.rest.spring.boot.config.exception.handler

import com.tramalho.rest.spring.boot.config.exception.ExceptionResponse
import com.tramalho.rest.spring.boot.config.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.util.Date

@RestController
@ControllerAdvice
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    final fun handleAllExceptions(
        exception: Exception,
        webRequest: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        return configResponse(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    final fun handleResourceNotFoundException(
        exception: Exception,
        webRequest: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        return configResponse(exception, webRequest, HttpStatus.NOT_FOUND)
    }

    private fun configResponse(
        exception: Exception,
        webRequest: WebRequest,
        httpStatusCode: HttpStatusCode
    ): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            Date(),
            exception.message ?: "invalid operation",
            webRequest.getDescription(false)
        )

        return ResponseEntity(exceptionResponse, httpStatusCode)
    }
}