package com.tramalho.rest.spring.boot.auth.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.tramalho.rest.spring.boot.auth.model.Permission
import com.tramalho.rest.spring.boot.auth.vo.TokenVO
import com.tramalho.rest.spring.boot.config.exception.InvalidJwtAuthException
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.lang.Exception
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

    fun createAccessToken(userName: String, roles: List<String>): TokenVO {
        val now = Date()

        val expired = refreshDate()

        val url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()

        val accessToken = getToken(url, userName, roles, now, refreshDate())
        val refreshToken = getToken(null, userName, roles, now, refreshDate(3))

        return TokenVO(userName, true, now, expired, accessToken, refreshToken)
    }

    fun validateToken(token: String): Boolean {
        val isOk: Boolean
        try {

            val decodeToken = decodeToken(token)
            isOk = decodeToken.expiresAt.after(Date())

        } catch (ex: Exception) {
            throw InvalidJwtAuthException("Invalid token")
        }

        return isOk
    }

    fun resolveToken(httpServletRequest: HttpServletRequest): String {
        val token = httpServletRequest.getHeader("Authorization")
        return token?.replace("Bearer", "")?.trim() ?: ""
    }

    fun getAuth(token: String): Authentication? {
        return try {
            val decodeToken = decodeToken(token)
            val userDetails = userDetailsService.loadUserByUsername(decodeToken.subject)
            UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        } catch (ex: Exception) {
            null
        }
    }

    private fun decodeToken(token: String): DecodedJWT {
        val jwtVerifier = JWT.require(algorithm).build()
        return jwtVerifier.verify(token)
    }

    private fun refreshDate(delay: Int = 1): Date {
        return Date(Date().time + (timeToExpireInMillis * delay))
    }

    private fun getToken(url: String?, userName: String, roles: List<String>, now: Date, expired: Date): String {

        val builder = JWT.create()

        url?.let {
            builder.withIssuer(it)
        }

        return builder
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(expired)
            .withSubject(userName)
            .sign(algorithm)
            .trim()
    }
}