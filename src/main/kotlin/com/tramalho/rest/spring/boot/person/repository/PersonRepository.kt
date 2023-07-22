package com.tramalho.rest.spring.boot.person.repository

import com.tramalho.rest.spring.boot.person.model.PersonModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<PersonModel, Long>