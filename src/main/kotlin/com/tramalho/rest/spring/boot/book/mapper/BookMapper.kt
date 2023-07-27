package com.tramalho.rest.spring.boot.book.mapper

import com.tramalho.rest.spring.boot.book.model.BookModel
import com.tramalho.rest.spring.boot.book.vo.BookVO
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service

@Service
class BookMapperImp : BookMapper {

    fun toListVO(bookModelList: List<BookModel>): List<BookVO> {

        val list = arrayListOf<BookVO>()

        for (bookModel in bookModelList) {
            list.add(toVO(bookModel))
        }

        return list
    }

    override fun toVO(bookModel: BookModel): BookVO = INSTANCE.toVO(bookModel)

    override fun toModel(bookVO: BookVO) = INSTANCE.toModel(bookVO)

    companion object {
        private val INSTANCE: BookMapper = Mappers.getMapper(BookMapper::class.java)
    }
}

@Mapper
interface BookMapper {
    @Mapping(source = "id", target = "key")
    fun toVO(bookModel: BookModel): BookVO

    @Mapping(source = "key", target = "id")
    fun toModel(bookVO: BookVO): BookModel
}


