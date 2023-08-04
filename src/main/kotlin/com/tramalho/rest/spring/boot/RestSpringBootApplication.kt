package com.tramalho.rest.spring.boot

import com.tramalho.rest.spring.boot.auth.config.SecurityConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestSpringBootApplication

/*
	@Autowired
	private var securityConfig: SecurityConfig = SecurityConfig(null)
*/

fun main(args: Array<String>) {
	runApplication<RestSpringBootApplication>(*args)

/*	val passwordEncoder = securityConfig.passwordEncoder()

	val result1 = passwordEncoder.encode("admin123")
	val result2 = passwordEncoder.encode("admin345")

	println(result1)
	println(result2)*/
}
