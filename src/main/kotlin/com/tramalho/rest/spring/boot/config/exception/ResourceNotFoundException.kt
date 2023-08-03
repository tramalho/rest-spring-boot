package com.tramalho.rest.spring.boot.config.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ResourceNotFoundException(customMessage: String?) :
    RuntimeException(customMessage ?: "Data Not Found")