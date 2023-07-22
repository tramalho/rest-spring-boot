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
    val id: Long? = null,
    @Column(name = "first_name", nullable = false)
    var firstName: String = "",
    @Column(name = "last_name")
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)
