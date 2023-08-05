package com.tramalho.rest.spring.boot.auth.config

import com.tramalho.rest.spring.boot.auth.filter.JwtConfigurer
import com.tramalho.rest.spring.boot.auth.service.JwtTokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig(private val jwtTokenService: JwtTokenService, private val passwordEncoder: CustomPasswordEncoder) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return passwordEncoder.passwordEncoder
    }

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity.httpBasic {
            it.disable().csrf { c -> c.disable() }
        }.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.authorizeHttpRequests {
            it.requestMatchers("/auth/signin", "/auth/refresh/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
            it.requestMatchers("/api/**").authenticated()
            it.requestMatchers("/users").denyAll()
        }.cors(withDefaults())
            .apply(JwtConfigurer(jwtTokenService))

        return httpSecurity.build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
}