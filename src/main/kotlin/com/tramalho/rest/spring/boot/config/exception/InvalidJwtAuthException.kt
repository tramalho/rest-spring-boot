package com.tramalho.rest.spring.boot.config.exception

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.FORBIDDEN)
data class InvalidJwtAuthException(override val message: String?, override val cause: Throwable) :
    AuthenticationException(message, cause)