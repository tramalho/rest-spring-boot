package com.tramalho.rest.spring.boot.auth.service

import com.auth0.jwt.algorithms.Algorithm
import com.tramalho.rest.spring.boot.auth.model.Permission
import com.tramalho.rest.spring.boot.auth.vo.TokenVO
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*
import javax.print.attribute.standard.JobOriginatingUserName

@Service
class JwtTokenService(
    private val userDetailsService: UserDetailsService
) {

    @Value("\${security.jwt.token.secret-key:default}")
    private var secretKey = ""

    @Value("\${security.jwt.token.expire-length:default}")
    private var timeToExpireInMillis: Long = 0

    private var algorithm: Algorithm? = null


    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAccessToken(userName: String, roles: List<Permission>): TokenVO {
        val now = Date()

        val expired = Date(now.time + timeToExpireInMillis)

        val accessToken = getAccessToken(userName, roles, now, expired)
        val refreshToken = getAccessToken(userName, roles, now)

        return TokenVO(userName, true, now, expired, accessToken, accessToken)
    }

    private fun getAccessToken(userName: String, roles: List<Permission>, now: Date, expired: Date? = Date()): String {
        return ""
    }
}