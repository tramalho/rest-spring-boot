package com.tramalho.rest.spring.boot.auth.filter

import com.tramalho.rest.spring.boot.auth.service.JwtTokenService
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtConfigurer(private val jwtTokenService: JwtTokenService): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtTokenFilter(jwtTokenService), UsernamePasswordAuthenticationFilter::class.java)
    }
}