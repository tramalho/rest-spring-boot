package com.tramalho.rest.spring.boot.person.service

import com.tramalho.rest.spring.boot.exception.ResourceNotFoundException
import com.tramalho.rest.spring.boot.person.controller.PersonController
import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService(private val personRepository: PersonRepository) {

    private val logger = Logger.getLogger(PersonController::class.simpleName)

    fun findById(id: Long): PersonModel {
        return findAndHighOrderFunction(id)
    }

    fun findAll(): List<PersonModel> {
        return personRepository.findAll()
    }

    fun create(personModel: PersonModel): PersonModel {
        return personRepository.save(personModel)
    }

    fun update(personModel: PersonModel): PersonModel {

        personModel.id?.let {
            return findAndHighOrderFunction(it) { foundData ->
                foundData.address = personModel.address
                foundData.firstName = personModel.firstName
                foundData.lastName = personModel.lastName
                foundData.gender = personModel.gender
                personRepository.save(foundData)
            }
        }

        throw ResourceNotFoundException("invalid id")
    }

    fun delete(id: Long) {
        findAndHighOrderFunction(id) {
            personRepository.deleteById(it.id!!)
        }
    }

    private fun findAndHighOrderFunction(id: Long, function: (PersonModel) -> Unit = {}): PersonModel {
        val personModel = personRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("No record with id $id")
        }
        function.invoke(personModel)

        return personModel
    }
}
