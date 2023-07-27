package com.tramalho.rest.spring.boot.book.controller

import com.tramalho.rest.spring.boot.book.service.BookService
import com.tramalho.rest.spring.boot.book.vo.BookVO
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.APPLICATION_XML_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(private val bookService: BookService) {

    @GetMapping("/v1/{${ID}}", produces = [APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE])
    fun findById(@PathVariable(ID) id: String): BookVO {
        return bookService.findById(id.toLong())
    }

    @GetMapping("/v1")
    fun findBAll(): List<BookVO> {
        return bookService.findAll()
    }

    @PostMapping("/v1")
    fun create(@RequestBody bookVO: BookVO): BookVO {
        return bookService.create(bookVO)
    }

    @PutMapping("/v1")
    fun update(@RequestBody bookVO: BookVO): BookVO {
        return bookService.update(bookVO)
    }

    @DeleteMapping("/v1/{${ID}}")
    fun delete(@PathVariable(ID) id: String): ResponseEntity<Any> {
        bookService.delete(id.toLong())
        return ResponseEntity.noContent().build()
    }

    private companion object {
        private const val ID = "id"
    }
}