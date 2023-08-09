package com.tramalho.rest.spring.boot.file.service

import org.springframework.stereotype.Service
import com.tramalho.rest.spring.boot.file.model.FileModel

@Service
class FileService() {

    fun findById(id: Long): FileModel {
        return FileModel(id, "content")
    }
}