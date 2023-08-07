package com.tramalho.rest.spring.boot.person.repository

import com.tramalho.rest.spring.boot.person.model.PersonModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<PersonModel, Long> {

    @Modifying
    @Query("UPDATE PersonModel p SET p.enabled =:enabled WHERE p.id=:id")
    fun patchStatus(@Param("id") id: Long, @Param("enabled") enabled: Boolean)
}