package com.tramalho.rest.spring.boot.person.controller

import com.tramalho.rest.spring.boot.person.model.PersonModel
import  com.tramalho.rest.spring.boot.person.service.PersonService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.logging.Logger


@RestController
@RequestMapping("/person")
class PersonController(private val personService: PersonService) {

    @GetMapping("/{$ID}")
    fun findById(@PathVariable(ID) id: String): PersonModel {
        return personService.findById(id.toLong())
    }

    @GetMapping
    fun findBAll(): List<PersonModel> {
        return personService.findAll()
    }

    @PostMapping
    fun create(@RequestBody personModel: PersonModel): PersonModel {
        return personService.create(personModel)
    }

    @PutMapping
    fun update(@RequestBody personModel: PersonModel): PersonModel {
        return personService.create(personModel)
    }

    @DeleteMapping("/{$ID}")
    fun delete(@PathVariable(ID) id: String) {
        personService.delete(id.toLong())
    }

    private companion object {
        private const val ID = "id"
    }
}