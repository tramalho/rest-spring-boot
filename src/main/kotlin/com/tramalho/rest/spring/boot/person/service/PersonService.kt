package com.tramalho.rest.spring.boot.person.service

import com.tramalho.rest.spring.boot.exception.ResourceNotFoundException
import com.tramalho.rest.spring.boot.person.controller.PersonController
import com.tramalho.rest.spring.boot.person.mapper.PersonMapperImp
import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.repository.PersonRepository
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.linkTo

import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val personMapperImp: PersonMapperImp) {

    fun findById(id: Long): PersonVOV2 {

        return findAndHighOrderFunction(id).apply {
            val findById = { methodOn(PersonController::class.java).findById(id.toString()) }
            val withSelfRel = linkTo<PersonController> { findById() }.withSelfRel()
            this.add(withSelfRel)
        }
    }

    fun findAll(): List<PersonVOV2> {
        return personMapperImp.toListVO(personRepository.findAll())
    }

    fun create(personVO: PersonVOV1): PersonVOV1 {
        val personModel = personMapperImp.toModel(personVO)
        val save = personRepository.save(personModel)
        return personMapperImp.toVOV1(save)
    }

    fun createV2(personVO: PersonVOV2): PersonVOV2 {
        val personModel = personMapperImp.toModel(personVO)
        val save = personRepository.save(personModel)
        return personMapperImp.toVOV2(save)
    }

    fun update(personVO: PersonVOV2): PersonVOV2 {
        val personModel = personMapperImp.toModel(personVO)
        personModel.id?.let {
           val foundPersonVO = findAndHighOrderFunction(it) { foundData ->
                foundData.address = personModel.address
                foundData.firstName = personModel.firstName
                foundData.lastName = personModel.lastName
                foundData.gender = personModel.gender
                personRepository.save(foundData)
            }

            return foundPersonVO
        }

        throw ResourceNotFoundException("invalid id")
    }

    fun delete(id: Long) {
        findAndHighOrderFunction(id) {
            it.id?.let { it1 -> personRepository.deleteById(it1) }
        }
    }

    private fun findAndHighOrderFunction(id: Long, function: (PersonModel) -> Unit = {}): PersonVOV2 {
        val personModel = personRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("No record with id $id")
        }
        function.invoke(personModel)

        return personMapperImp.toVOV2(personModel)
    }
}
