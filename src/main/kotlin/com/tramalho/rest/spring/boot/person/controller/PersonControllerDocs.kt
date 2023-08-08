package com.tramalho.rest.spring.boot.person.controller

import com.tramalho.rest.spring.boot.config.exception.ExceptionResponse
import com.tramalho.rest.spring.boot.person.controller.PersonControllerDocs.Companion.TAG
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = TAG, description = "Endpoint for Managing Persons")
interface PersonControllerDocs {
    fun findById(@PathVariable(ID) id: String): PersonVOV2

    @Operation(
        summary = "Finds All People", tags = [TAG], responses = [
            ApiResponse(
                description = "Success", responseCode = "200", content = arrayOf(
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = ArraySchema(schema = Schema(implementation = PersonVOV2::class))
                    )
                )
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = arrayOf(
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = ArraySchema(schema = Schema(implementation = PersonVOV2::class))
                    )
                )
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400", content = arrayOf(
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = ArraySchema(schema = Schema(implementation = ExceptionResponse::class))
                    )
                )
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401", content = arrayOf(
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = ArraySchema(schema = Schema(implementation = ExceptionResponse::class))
                    )
                )
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404", content = arrayOf(
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = ArraySchema(schema = Schema(implementation = ExceptionResponse::class))
                    )
                )
            ),
            ApiResponse(
                description = "Internal Error", responseCode = "405", content = arrayOf(
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = ArraySchema(schema = Schema(implementation = ExceptionResponse::class))
                    )
                )
            )
        ]
    )
    fun findBAll(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "12") size: Int,
        @RequestParam(value = "direction", defaultValue = "asc") direction: String
    ): ResponseEntity<PagedModel<EntityModel<PersonVOV2>>>

    fun findByName(
        @PathVariable(value = "name") name: String,
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "12") size: Int,
        @RequestParam(value = "direction", defaultValue = "asc") direction: String
    ): ResponseEntity<PagedModel<EntityModel<PersonVOV2>>>

    fun create(personVO: PersonVOV1): PersonVOV1

    fun createV2(personVOV2: PersonVOV2): PersonVOV2

    fun update(personVO: PersonVOV2): PersonVOV2

    fun delete(id: String): ResponseEntity<Any>

    fun patchStatus(id: Long, enabled: Boolean): PersonVOV2

    companion object {
        const val ID = "id"
        const val TAG = "People"

    }
}
