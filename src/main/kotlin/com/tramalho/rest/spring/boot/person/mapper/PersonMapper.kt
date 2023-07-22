package com.tramalho.rest.spring.boot.person.mapper

import com.tramalho.rest.spring.boot.person.model.PersonModel
import com.tramalho.rest.spring.boot.person.vo.v1.PersonVO
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Component

@Component
class PersonMapperImp : PersonMapper {

    override fun toVO(personModel: PersonModel) = INSTANCE.toVO(personModel)
    fun toListVO(personModelList: List<PersonModel>): List<PersonVO> {
        return personModelList.map {
            toVO(it)
        }.toList()
    }

    override fun toModel(personVO: PersonVO) = INSTANCE.toModel(personVO)

    companion object {
        private val INSTANCE: PersonMapper = Mappers.getMapper(PersonMapper::class.java)
    }
}

@Mapper
interface PersonMapper {
    fun toVO(personModel: PersonModel): PersonVO
    fun toModel(personVO: PersonVO): PersonModel
}


