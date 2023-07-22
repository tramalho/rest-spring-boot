package com.tramalho.rest.spring.boot.person.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "person")
data class PersonModel(
    @Id
    @GeneratedValue
    val id: Long,
    @Column(name = "first_name", nullable = false)
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    val address: String,
    val gender: String
)
