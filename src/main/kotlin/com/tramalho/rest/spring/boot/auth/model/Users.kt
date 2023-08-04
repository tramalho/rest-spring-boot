package com.tramalho.rest.spring.boot.auth.model

import jakarta.persistence.*

@Entity
@Table
data class Users(
    @Id @GeneratedValue val id: Long? = null,
    @Column(name = "user_name", unique = true)
    val userName: String,
    @Column(name = "full_name") val fullName: String,
    val password: String,
    @Column(name = "account_non_expired") val accountNonExpired: Boolean?,
    @Column(name = "account_non_locked") val accountNonLocked: Boolean?,
    @Column(name = "credentials_non_expired") val credentialsNonExpired: Boolean?,
    val enabled: Boolean?,
    @ManyToMany(fetch = FetchType.EAGER) @JoinTable(
        name = "user_permission",
        joinColumns = [JoinColumn(name = "id_user")],
        inverseJoinColumns = [JoinColumn(name = "id_permission")]
    ) val permissions: List<Permission>
)
