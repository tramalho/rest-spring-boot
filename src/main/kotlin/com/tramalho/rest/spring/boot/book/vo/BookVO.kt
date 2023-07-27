package com.tramalho.rest.spring.boot.book.vo

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
open class BookVO(
    @JsonProperty("id")
    var key: Long? = null,
    var author: String = "",
    var price: Double = 0.0,
    var title: String = "",
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer::class)
    var launchDate: LocalDate? = null
) : RepresentationModel<BookVO>()
