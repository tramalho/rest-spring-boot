package com.tramalho.rest.spring.boot.auth.service

import com.tramalho.rest.spring.boot.auth.vo.AccountCredentialVO
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenService: JwtTokenService,
    private val userService: UserService
) {

    fun signin(accountCredentialVO: AccountCredentialVO): ResponseEntity<Any> {

        try {
            val (username, password) = accountCredentialVO

            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))

            val userDetails = userService.loadUserByUsername(username)

            val tokenVO = jwtTokenService.createAccessToken(userDetails.username, userDetails.getRoles())

            return ResponseEntity.ok(tokenVO)

        } catch (ex: Exception) {
            throw BadCredentialsException("Invalid credentials")
        }
    }
}