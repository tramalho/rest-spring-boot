package com.tramalho.rest.spring.boot.person.service

import com.tramalho.rest.spring.boot.exception.ResourceNotFoundException
import com.tramalho.rest.spring.boot.person.mapper.PersonMapperImp
import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.repository.PersonRepository
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVO
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val personMapperImp: PersonMapperImp) {

    fun findById(id: Long): PersonVO {
        return findAndHighOrderFunction(id)
    }

    fun findAll(): List<PersonVO> {
        return personMapperImp.toListVO(personRepository.findAll())
    }

    fun create(personVO: PersonVO): PersonVO {
        val personModel = personMapperImp.toModel(personVO)
        val save = personRepository.save(personModel)
        return personMapperImp.toVO(save)
    }

    fun update(personVO: PersonVO): PersonVO {
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
            personRepository.deleteById(it.id!!)
        }
    }

    private fun findAndHighOrderFunction(id: Long, function: (PersonModel) -> Unit = {}): PersonVO {
        val personModel = personRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("No record with id $id")
        }
        function.invoke(personModel)

        return personMapperImp.toVO(personModel)
    }
}
