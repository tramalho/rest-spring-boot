package com.tramalho.rest.spring.boot.book.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "book")
data class BookModel(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var author: String = "",
    var price: Double = 0.0,
    var title: String = "",
    @Column(name = "launch_date", nullable = true)
    var launchDate: LocalDate? = null,
)
