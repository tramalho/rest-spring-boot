package com.tramalho.rest.spring.boot.auth.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority

@Entity
@Table
data class Permission(
    @Id
    @GeneratedValue
    val id: Long? = null,
    private val description: String = ""
) : GrantedAuthority {
    override fun getAuthority() = description
}
