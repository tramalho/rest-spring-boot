package com.tramalho.rest.spring.boot.config.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
data class FileStorageException(
        override val message: String,
        override val cause: Throwable
) : RuntimeException(message, cause)