package com.tramalho.rest.spring.boot.person.vo.v2

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
open class PersonVOV2(
    @JsonProperty("id")
    var key: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer::class)
    var birthDay: LocalDate? = null,
    var enabled: Boolean = true,
    ) : RepresentationModel<PersonVOV2>()

