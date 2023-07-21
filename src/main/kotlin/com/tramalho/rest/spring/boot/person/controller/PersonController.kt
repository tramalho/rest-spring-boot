package com.tramalho.rest.spring.boot.person.controller

import com.tramalho.rest.spring.boot.person.model.PersonModel
import  com.tramalho.rest.spring.boot.person.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger


@RestController
class PersonController(private val personService: PersonService) {

    @GetMapping("/person/{$ID}")
    fun findById(@PathVariable(ID) id: String): PersonModel {
        return personService.findById(id.toLong())
    }

    private companion object {
        private const val ID = "id"
    }
}