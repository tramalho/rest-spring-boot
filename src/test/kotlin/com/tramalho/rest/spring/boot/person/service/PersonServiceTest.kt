package com.tramalho.rest.spring.boot.person.service

import com.tramalho.rest.spring.boot.person.mapper.PersonMapperImp
import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.repository.PersonRepository
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.util.*

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class PersonServiceTest {

    @Mock
    private lateinit var repository: PersonRepository

    @Mock
    private lateinit var personMapper: PersonMapperImp

    private lateinit var personService: PersonService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        whenever(personMapper.toVOV2(any())).thenReturn(PersonVOV2())

        personService = PersonService(repository, personMapper)
    }

    @Test
    fun testFindById() {
        val personModel = PersonModel(1L)
        whenever(repository.findById(anyLong())).thenReturn(Optional.of(personModel))

        val personVOV2 = personService.findById(anyLong())

        assertNotNull(personVOV2)
        assertNotNull(personVOV2.links)
        assertTrue(personVOV2.toString().contains("self"))
    }

}