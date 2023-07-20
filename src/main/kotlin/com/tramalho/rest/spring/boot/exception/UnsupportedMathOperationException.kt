package com.tramalho.rest.spring.boot.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UnsupportedMathOperationException(customMessage: String?) : RuntimeException("Please set a numeric  value [$customMessage]")