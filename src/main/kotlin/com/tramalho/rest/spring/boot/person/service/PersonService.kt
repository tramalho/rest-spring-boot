package com.tramalho.rest.spring.boot.person.service

import com.tramalho.rest.spring.boot.config.exception.ResourceNotFoundException
import com.tramalho.rest.spring.boot.person.controller.PersonController
import com.tramalho.rest.spring.boot.person.mapper.PersonMapperImp
import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.repository.PersonRepository
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.linkTo

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val personMapperImp: PersonMapperImp) {

    fun findById(id: Long): PersonVOV2 {
        return findAndHighOrderFunction(id)
    }

    fun findAll(): List<PersonVOV2> {
        val toListVO = personMapperImp.toListVO(personRepository.findAll())
        toListVO.forEach { vo -> vo.hateoas() }
        return toListVO
    }

    fun create(personVO: PersonVOV1): PersonVOV1 {
        val personModel = personMapperImp.toModel(personVO)
        val save = personRepository.save(personModel)
        return personMapperImp.toVOV1(save)
    }

    fun createV2(personVO: PersonVOV2): PersonVOV2 {
        val personModel = personMapperImp.toModel(personVO)
        val save = personRepository.save(personModel)
        return personMapperImp.toVOV2(save).apply {
            hateoas()
        }
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

    @Transactional
    fun patchStatus(id: Long, enabled: Boolean): PersonVOV2 {
        personRepository.patchStatus(id, enabled)
        return findAndHighOrderFunction(id)
    }

    private fun findAndHighOrderFunction(id: Long, function: (PersonModel) -> Unit = {}): PersonVOV2 {
        val personModel = personRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("No record with id $id")
        }
        function.invoke(personModel)

        return personMapperImp.toVOV2(personModel).apply {
            hateoas()
        }
    }

    private fun PersonVOV2.hateoas() {
        val findById = { methodOn(PersonController::class.java).findById(this.key.toString()) }
        val withSelfRel = linkTo<PersonController> { findById() }.withSelfRel()
        this.add(withSelfRel)
    }
}
