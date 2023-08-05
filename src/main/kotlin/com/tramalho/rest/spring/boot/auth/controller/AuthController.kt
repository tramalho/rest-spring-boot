package com.tramalho.rest.spring.boot.auth.controller

import com.tramalho.rest.spring.boot.auth.service.AuthService
import com.tramalho.rest.spring.boot.auth.vo.AccountCredentialVO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {


    @PostMapping("/signin")
    fun signin(@RequestBody accountCredentialVO: AccountCredentialVO): ResponseEntity<Any> {

        val tokenVO = authService.signin(accountCredentialVO)
        return ResponseEntity.ok(tokenVO)
    }
}