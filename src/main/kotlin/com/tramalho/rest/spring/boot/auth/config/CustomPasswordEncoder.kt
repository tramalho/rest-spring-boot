package com.tramalho.rest.spring.boot.auth.config

import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomPasswordEncoder {

    val passwordEncoder: DelegatingPasswordEncoder by lazy {
        val encoders = hashMapOf<String, PasswordEncoder>()

        val pbkdf2PasswordEncoder =
            Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)

        encoders["pbkdf2"] = pbkdf2PasswordEncoder

        DelegatingPasswordEncoder("pbkdf2", encoders).apply {
            setDefaultPasswordEncoderForMatches(pbkdf2PasswordEncoder)
        }
    }
}