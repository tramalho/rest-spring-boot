package com.tramalho.rest.spring.boot.person.service

import com.tramalho.rest.spring.boot.config.exception.ResourceNotFoundException
import com.tramalho.rest.spring.boot.person.controller.PersonController
import com.tramalho.rest.spring.boot.person.mapper.PersonMapperImp
import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.repository.PersonRepository
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort.Direction
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.linkTo

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val personMapperImp: PersonMapperImp,
    private val pagedResourcesAssembler: PagedResourcesAssembler<PersonVOV2>) {

    fun findById(id: Long): PersonVOV2 {
        return findAndHighOrderFunction(id)
    }

    fun findAll(pageRequest: PageRequest): PagedModel<EntityModel<PersonVOV2>> {

        val page = personRepository.findAll(pageRequest)

        val personVOPageList = page.map {
            val toVOV2 = personMapperImp.toVOV2(it)
            toVOV2.hateoas()
            toVOV2
        }

        val pageLink = hateoasFindAll(pageRequest)

        return pagedResourcesAssembler.toModel(personVOPageList, pageLink)
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

    private fun hateoasFindAll(p: PageRequest): Link {
        //val directions = p.sort.getOrderFor()
        val findAll = { methodOn(PersonController::class.java).findBAll(p.pageNumber, p.pageSize, "asc") }
        return linkTo<PersonController> { findAll() }.withSelfRel()
    }

    fun Direction.toStr(): String {
        return this.name.lowercase()
    }
}
