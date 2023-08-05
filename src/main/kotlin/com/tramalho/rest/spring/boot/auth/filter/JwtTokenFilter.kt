package com.tramalho.rest.spring.boot.auth.filter

import com.tramalho.rest.spring.boot.auth.service.JwtTokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtTokenFilter(private val jwtTokenService: JwtTokenService) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val resolveToken = jwtTokenService.resolveToken(request as HttpServletRequest)

        if (jwtTokenService.validateToken(resolveToken)) {
            jwtTokenService.getAuth(resolveToken)?.apply {
                SecurityContextHolder.getContext().authentication = this
            }
        }

        chain.doFilter(request, response)
    }
}