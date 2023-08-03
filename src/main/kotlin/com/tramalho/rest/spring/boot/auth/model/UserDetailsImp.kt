package com.tramalho.rest.spring.boot.auth.model

import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsImp(val users: Users) : UserDetails {

    fun getRoles(): List<String> {
        return users.permissions.map { permission -> permission.authority }
    }

    override fun getAuthorities(): List<Permission> {
        return users.permissions
    }

    override fun getPassword(): String {
        return users.password
    }

    override fun getUsername(): String {
        return users.username
    }

    override fun isAccountNonExpired(): Boolean {
        return users.accountNonExpired ?: false
    }

    override fun isAccountNonLocked(): Boolean {
        return users.accountNonLocked ?: false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return users.credentialsNonExpired ?: false
    }

    override fun isEnabled(): Boolean {
        return users.enabled ?: false
    }
}
