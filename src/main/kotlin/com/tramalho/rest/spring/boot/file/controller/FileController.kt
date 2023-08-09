package com.tramalho.rest.spring.boot.file.controller

import com.tramalho.rest.spring.boot.file.model.FileModel
import com.tramalho.rest.spring.boot.file.service.FileService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FileController(private val fileService: FileService) {

    @GetMapping("/file/{ID}")
    fun findById(@PathVariable(ID) id: String): FileModel {
        return fileService.findById(id.toLong())
    }

    private companion object {
        private const val ID = "id"
    }
}