package com.tramalho.rest.spring.boot.book.repository

import com.tramalho.rest.spring.boot.book.model.BookModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookModel, Long>