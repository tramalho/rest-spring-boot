package com.tramalho.rest.spring.boot.person.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "person")
data class PersonModel(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @Column(name = "first_name", nullable = false)
    var firstName: String = "",
    @Column(name = "last_name")
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
    @Column(name = "birth_day", nullable = true)
    var birthDay: LocalDate? = null,
    var enabled: Boolean = true,
)
