package com.tramalho.rest.spring.boot.person.model

data class PersonModel(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val address: String,
    val gender: String
)
