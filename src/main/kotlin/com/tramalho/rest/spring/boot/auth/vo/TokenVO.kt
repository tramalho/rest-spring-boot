package com.tramalho.rest.spring.boot.auth.vo

import java.util.Date

data class TokenVO(
    val username: String,
    val authenticated: Boolean,
    val created: Date,
    val expiration: Date,
    val accessToken: String,
    val refreshToken: String
)
