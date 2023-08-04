package com.tramalho.rest.spring.boot.auth.config

import com.tramalho.rest.spring.boot.auth.filter.JwtConfigurer
import com.tramalho.rest.spring.boot.auth.service.JwtTokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm
import java.beans.Customizer

@EnableWebSecurity
@Configuration
class SecurityConfig(private val jwtTokenService: JwtTokenService) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        val encoders = hashMapOf<String, PasswordEncoder>()

        val pbkdf2PasswordEncoder = Pbkdf2PasswordEncoder("", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)

        encoders["pbkdf2"] = pbkdf2PasswordEncoder

        return DelegatingPasswordEncoder("pbkdf2", encoders).apply {
            setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder)
        }
    }


    fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.httpBasic {
                it.disable().csrf { c -> c.disable() }
            }.sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.authorizeHttpRequests {
                it.requestMatchers("/auth/signin", "/auth/refresh", "/api-docs/**", "/swagger-ui.html**").permitAll()
                it.requestMatchers("/api/**").authenticated()
                it.requestMatchers("/users").denyAll()
            }.cors(withDefaults()).apply(JwtConfigurer(jwtTokenService))

    }
}