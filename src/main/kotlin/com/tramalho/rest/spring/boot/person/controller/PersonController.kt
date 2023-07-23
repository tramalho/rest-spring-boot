package com.tramalho.rest.spring.boot.person.controller

import  com.tramalho.rest.spring.boot.person.service.PersonService
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(private val personService: PersonService) {

    @GetMapping("/{$ID}")
    fun findById(@PathVariable(ID) id: String): PersonVOV2 {
        return personService.findById(id.toLong())
    }

    @GetMapping
    fun findBAll(): List<PersonVOV2> {
        return personService.findAll()
    }

    @PostMapping("/v1")
    fun create(@RequestBody personVO: PersonVOV1): PersonVOV1 {
        return personService.create(personVO)
    }

    @PostMapping("/v2")
    fun createV2(@RequestBody personVOV2: PersonVOV2): PersonVOV2 {
        return personService.createV2(personVOV2)
    }

    @PutMapping
    fun update(@RequestBody personVO: PersonVOV2): PersonVOV2 {
        return personService.update(personVO)
    }

    @DeleteMapping("/{$ID}")
    fun delete(@PathVariable(ID) id: String):ResponseEntity<Any> {
        personService.delete(id.toLong())
        return ResponseEntity.noContent().build()
    }

    private companion object {
        private const val ID = "id"
    }
}