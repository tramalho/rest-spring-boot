package com.tramalho.rest.spring.boot.auth.controller

import com.tramalho.rest.spring.boot.auth.service.AuthService
import com.tramalho.rest.spring.boot.auth.vo.AccountCredentialVO
import com.tramalho.rest.spring.boot.auth.vo.TokenVO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {


    @PostMapping("/signin")
    fun signin(@RequestBody accountCredentialVO: AccountCredentialVO): ResponseEntity<TokenVO> {

        val tokenVO = authService.signIn(accountCredentialVO)
        return ResponseEntity.ok(tokenVO)
    }

    @PutMapping("/refresh/{username}")
    fun refreshToken(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable("username") username: String
    ): ResponseEntity<TokenVO> {
        val tokenVO = authService.refreshToken(authorization, username)
        return ResponseEntity.ok(tokenVO)
    }
}