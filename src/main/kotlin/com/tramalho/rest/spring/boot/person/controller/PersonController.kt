package com.tramalho.rest.spring.boot.person.controller

import  com.tramalho.rest.spring.boot.person.service.PersonService
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(private val personService: PersonService) {

    @GetMapping("/{$ID}")
    fun findById(@PathVariable(ID) id: String): PersonVO {
        return personService.findById(id.toLong())
    }

    @GetMapping
    fun findBAll(): List<PersonVO> {
        return personService.findAll()
    }

    @PostMapping
    fun create(@RequestBody personVO: PersonVO): PersonVO {
        return personService.create(personVO)
    }

    @PutMapping
    fun update(@RequestBody personVO: PersonVO): PersonVO {
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