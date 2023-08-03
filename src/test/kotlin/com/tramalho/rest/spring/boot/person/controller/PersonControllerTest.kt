package com.tramalho.rest.spring.boot.person.controller

import com.tramalho.rest.spring.boot.person.service.PersonService
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [PersonController::class])
@ExtendWith(MockitoExtension::class)
class PersonControllerTest {

    @MockBean
    private lateinit var personService: PersonService

    @Autowired
    private lateinit var mockMvc: MockMvc


    @BeforeEach
    fun setUp() {

    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "http://localhost:3000, 200",
            "http://localhost:1000, 403",
        ]
    )
    fun shouldValidateOriginVerification(origin: String, statusCode: Int) {
        whenever(personService.findById(anyLong())).thenReturn(PersonVOV2())

        val requestBuilder = get("/person/v1/1").header("Origin", origin)

        mockMvc
            .perform(requestBuilder)
            .andDo(print())
            .andExpect(status().`is`(HttpStatus.valueOf(statusCode).value()))
    }
}