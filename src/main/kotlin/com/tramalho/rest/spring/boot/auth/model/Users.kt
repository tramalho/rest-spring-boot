package com.tramalho.rest.spring.boot.auth.model

import jakarta.persistence.*

@Entity
@Table
data class Users(
    @Id @GeneratedValue val id: Long? = null,
    @Column(name = "user_name", unique = true)
    val userName: String = "",
    @Column(name = "full_name")
    val fullName: String = "",
    val password: String = "",
    @Column(name = "account_non_expired") val accountNonExpired: Boolean? = false,
    @Column(name = "account_non_locked") val accountNonLocked: Boolean? = false,
    @Column(name = "credentials_non_expired") val credentialsNonExpired: Boolean? = false,
    val enabled: Boolean? = false,
    @ManyToMany(fetch = FetchType.EAGER) @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")]
    ) val permissions: List<Permission> = arrayListOf()
)
