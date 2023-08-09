package com.tramalho.rest.spring.boot

import com.tramalho.rest.spring.boot.auth.config.CustomPasswordEncoder
import com.tramalho.rest.spring.boot.auth.config.SecurityConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class RestSpringBootApplication

/*
	private val customPasswordEncoder: CustomPasswordEncoder = CustomPasswordEncoder()
*/

fun main(args: Array<String>) {
	runApplication<RestSpringBootApplication>(*args)

/*	val passwordEncoder = customPasswordEncoder.passwordEncoder

	val result1 = passwordEncoder.encode("admin123")
	val result2 = passwordEncoder.encode("admin345")

	println("result1:$result1")
	println("result2:$result2")*/
}
