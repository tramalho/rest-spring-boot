package com.tramalho.rest.spring.boot.person.vo.v2

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PersonVOV2(
        var id: Long? = null,
        var firstName: String = "",
        var lastName: String = "",
        var address: String = "",
        var gender: String = "",
        var birthDay: LocalDate? = null
)

