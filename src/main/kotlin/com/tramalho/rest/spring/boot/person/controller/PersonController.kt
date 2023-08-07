package com.tramalho.rest.spring.boot.person.controller

import com.tramalho.rest.spring.boot.person.controller.PersonControllerDocs.Companion.ID
import  com.tramalho.rest.spring.boot.person.service.PersonService
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import jdk.jfr.Enabled
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/person")
class PersonController(private val personService: PersonService) : PersonControllerDocs {

    //@CrossOrigin(origins = [ "localhost:8080","http://127.0.0.1" ])
    @GetMapping("/v1/{$ID}")
    override fun findById(@PathVariable(ID) id: String): PersonVOV2 {
        return personService.findById(id.toLong())
    }

    @GetMapping("/v1")
    override fun findBAll(): List<PersonVOV2> {
        return personService.findAll()
    }

    @PostMapping("/v1")
    override fun create(@RequestBody personVO: PersonVOV1): PersonVOV1 {
        return personService.create(personVO)
    }

    @PostMapping("/v2")
    override fun createV2(@RequestBody personVOV2: PersonVOV2): PersonVOV2 {
        return personService.createV2(personVOV2)
    }

    @PutMapping("/v1")
    override fun update(@RequestBody personVO: PersonVOV2): PersonVOV2 {
        return personService.update(personVO)
    }

    @DeleteMapping("/v1/{$ID}")
    override fun delete(@PathVariable(ID) id: String): ResponseEntity<Any> {
        personService.delete(id.toLong())
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/v1/{$ID}/{enabled}")
    override fun patchStatus(@PathVariable(ID) id: Long, @PathVariable("enabled") enabled: Boolean): PersonVOV2 {
        return personService.patchStatus(id, enabled)
    }
}