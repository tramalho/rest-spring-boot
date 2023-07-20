package com.tramalho.rest.spring.boot.exception

import java.io.Serializable
import java.util.Date

class ExceptionResponse(val timeStamp: Date, val message: String, val details: String) : Serializable