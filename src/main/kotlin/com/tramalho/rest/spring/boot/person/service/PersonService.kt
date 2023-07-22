package com.tramalho.rest.spring.boot.person.service

import com.tramalho.rest.spring.boot.person.controller.PersonController
import com.tramalho.rest.spring.boot.person.model.PersonModel
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    private val logger = Logger.getLogger(PersonController::class.simpleName)

    fun findById(id: Long): PersonModel {
        val personModel = PersonModel(id, "first", "last", "address", "m")
        logger.info("$personModel")
        return personModel
    }

    fun findAll(): List<PersonModel> {
        val results = arrayListOf<PersonModel>()

        for (i in 1..8) {
            results.add(PersonModel(i.toLong(), "first $i", "last $i", "address $i", if (i % 2 == 0) "m" else "f"))
        }

        logger.info("$results")
        return results
    }

    fun create(personModel: PersonModel): PersonModel {
        logger.info("$personModel")
        return personModel
    }
    fun update(personModel: PersonModel): PersonModel {
        logger.info("$personModel")
        return personModel
    }

    fun delete(id: Long) {
        logger.info("$id")
    }
}
