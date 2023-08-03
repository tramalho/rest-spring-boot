package com.tramalho.rest.spring.boot.book.service

import com.tramalho.rest.spring.boot.book.controller.BookController
import com.tramalho.rest.spring.boot.book.mapper.BookMapperImp
import com.tramalho.rest.spring.boot.book.model.BookModel
import com.tramalho.rest.spring.boot.book.repository.BookRepository
import com.tramalho.rest.spring.boot.book.vo.BookVO
import org.springframework.stereotype.Service
import com.tramalho.rest.spring.boot.config.exception.ResourceNotFoundException
import com.tramalho.rest.spring.boot.person.controller.PersonController
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.linkTo

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val bookMapper: BookMapperImp
) {

    fun findById(id: Long): BookVO {
        return findAndHighOrderFunction(id)
    }

    fun findAll(): List<BookVO> {
        val toListVO = bookMapper.toListVO(bookRepository.findAll())
        toListVO.forEach { vo -> vo.hateoas() }
        return toListVO
    }

    fun create(bookVO: BookVO): BookVO {
        val personModel = bookMapper.toModel(bookVO)
        val save = bookRepository.save(personModel)
        return bookMapper.toVO(save)
    }

    fun update(bookVO: BookVO): BookVO {
        val bookModel = bookMapper.toModel(bookVO)
        bookModel.id?.let {
            val foundBookVO = findAndHighOrderFunction(it) { foundData ->
                foundData.title = bookModel.title
                foundData.author = bookModel.author
                foundData.price = bookModel.price
                foundData.launchDate = bookModel.launchDate
                bookRepository.save(foundData)
            }

            return foundBookVO
        }

        throw ResourceNotFoundException("invalid id")
    }

    fun delete(id: Long) {
        findAndHighOrderFunction(id) {
            it.id?.let { it1 -> bookRepository.deleteById(it1) }
        }
    }

    private fun findAndHighOrderFunction(id: Long, function: (BookModel) -> Unit = {}): BookVO {
        val bookModel = bookRepository.findById(id).orElseThrow {
            throw ResourceNotFoundException("No record with id $id")
        }
        function.invoke(bookModel)

        return bookMapper.toVO(bookModel).apply {
            hateoas()
        }
    }

    private fun BookVO.hateoas() {
        val findById = { WebMvcLinkBuilder.methodOn(BookController::class.java).findById(this.key.toString()) }
        val withSelfRel = linkTo<BookController> { findById() }.withSelfRel()
        this.add(withSelfRel)
    }
}
