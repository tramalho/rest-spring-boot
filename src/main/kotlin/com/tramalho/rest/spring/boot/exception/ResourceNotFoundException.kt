package com.tramalho.rest.spring.boot.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(customMessage: String?) :
    RuntimeException("Validate values [$customMessage]")