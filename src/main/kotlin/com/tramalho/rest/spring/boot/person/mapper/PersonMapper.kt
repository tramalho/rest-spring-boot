package com.tramalho.rest.spring.boot.person.mapper

import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class PersonMapperImp : PersonMapper {

    override fun toVOV1(personModel: PersonModel) = INSTANCE.toVOV1(personModel)

    override fun toVOV2(personModel: PersonModel) = INSTANCE.toVOV2(personModel)

    fun toListVO(personModelList: List<PersonModel>): List<PersonVOV2> {
        return personModelList.map {
            toVOV2(it)
        }.toList()
    }

    override fun toModel(personVO: PersonVOV1) = INSTANCE.toModel(personVO)

    override fun toModel(personVO: PersonVOV2) = INSTANCE.toModel(personVO)

    companion object {
        private val INSTANCE: PersonMapper = Mappers.getMapper(PersonMapper::class.java)
    }
}

@Mapper
interface PersonMapper {
    fun toVOV1(personModel: PersonModel): PersonVOV1
    fun toVOV2(personModel: PersonModel): PersonVOV2
    fun toModel(personVO: PersonVOV1): PersonModel
    fun toModel(personVO: PersonVOV2): PersonModel
}


