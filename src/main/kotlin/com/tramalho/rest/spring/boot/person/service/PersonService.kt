package com.tramalho.rest.spring.boot.person.service

import com.tramalho.rest.spring.boot.person.controller.PersonController
import com.tramalho.rest.spring.boot.person.model.PersonModel
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    private val logger = Logger.getLogger(PersonController::class.simpleName)

    fun findById(id: Long): PersonModel {
        val personModel = PersonModel(id, "content")
        logger.info("$personModel")
        return personModel
    }
}
