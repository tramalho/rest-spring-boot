package com.tramalho.rest.spring.boot.config.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
data class MyFileNotFoundException(
        override val message: String,
        override val cause: Throwable
) : RuntimeException(message, cause) 