package com.tramalho.rest.spring.boot.auth.service

import com.tramalho.rest.spring.boot.auth.vo.AccountCredentialVO
import com.tramalho.rest.spring.boot.auth.vo.TokenVO
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenService: JwtTokenService,
    private val userService: UserService
) {

    fun signin(accountCredentialVO: AccountCredentialVO): TokenVO {

        if (isValid(accountCredentialVO)) {

            try {

                val (username, password) = accountCredentialVO

                authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))

                username?.let {
                    val userDetails = userService.loadUserByUsername(it)
                    return jwtTokenService.createAccessToken(userDetails.username, userDetails.getRoles())
                }
            } catch (ex: Exception) {
                throw BadCredentialsException("Invalid credentials", ex)
            }
        }
        throw UsernameNotFoundException("Invalid Client Request")
    }

    private fun isValid(ac: AccountCredentialVO?): Boolean {
        return ac != null && ac.password?.isNotEmpty() == true && ac.username?.isNotEmpty() == true
    }
}