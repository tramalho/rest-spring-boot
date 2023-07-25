package com.tramalho.rest.spring.boot.person.mapper

import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVOV1
import com.tramalho.rest.spring.boot.person.vo.v2.PersonVOV2
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class PersonMapperImp : PersonMapper {

    override fun toVOV1(personModel: PersonModel) = INSTANCE.toVOV1(personModel)

    override fun toVOV2(personModel: PersonModel) = INSTANCE.toVOV2(personModel)

    fun toListVO(personModelList: List<PersonModel>): List<PersonVOV2> {

        val list = arrayListOf<PersonVOV2>()

        for (personModel in personModelList) {
            list.add(toVOV2(personModel))
        }

        return list
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

    @Mapping(source = "id", target = "key")
    fun toVOV2(personModel: PersonModel): PersonVOV2
    fun toModel(personVO: PersonVOV1): PersonModel
    @Mapping(source = "key", target = "id")
    fun toModel(personVO: PersonVOV2): PersonModel
}


