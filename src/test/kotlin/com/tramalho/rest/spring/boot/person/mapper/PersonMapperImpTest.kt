package com.tramalho.rest.spring.boot.person.mapper

import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PersonMapperImpTest {

    private lateinit var personMapperImp: PersonMapperImp

    @BeforeEach
    fun setUp() {
        personMapperImp = PersonMapperImp()
    }

    @Test
    fun shouldParseVoToModel() {
        val vo = createVO()
        val model = personMapperImp.toModel(vo)
        assert(vo, model)
    }

    @Test
    fun shouldParseModelToVO() {
        val model = createModel()
        val voList = personMapperImp.toListVO(listOf(model))
        assert(voList.first(), model)
    }

    private fun assert(vo: PersonVOV2, model: PersonModel) {
        assertEquals(vo.id, model.id)
        assertEquals(vo.firstName, model.firstName)
        assertEquals(vo.lastName, model.lastName)
        assertEquals(vo.address, model.address)
        assertEquals(vo.gender, model.gender)
        assertEquals(vo.birthDay, model.birthDay)
    }

    private fun createVO() = PersonVOV2(1, "firstName", "lastName", "address", "F")
    private fun createModel() = PersonModel(1, "firstName", "lastName", "address", "F")
}