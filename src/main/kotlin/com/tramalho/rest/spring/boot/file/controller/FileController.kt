package com.tramalho.rest.spring.boot.file.controller

import com.tramalho.rest.spring.boot.file.controller.FileController.Companion.BASE_PATH
import com.tramalho.rest.spring.boot.file.service.FileService
import com.tramalho.rest.spring.boot.file.vo.FileResponseVO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(BASE_PATH)
class FileController(private val fileService: FileService) {

    @PostMapping(UPLOAD_PATH)
    fun uploadFile(@RequestParam("file") multipartFile: MultipartFile): FileResponseVO {

        val storedFileName = fileService.storeFile(multipartFile)

        val filedDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("$BASE_PATH$DOWNLOAD_PATH")
                .path(storedFileName)
                .toUriString()

        return FileResponseVO(storedFileName, filedDownloadURI, multipartFile.contentType ?: "", multipartFile.size)
    }

    @PostMapping(UPLOAD_MULTIPLES_PATH)
    fun uploadMultiples(@RequestParam("files") multipartFile: List<MultipartFile>): List<FileResponseVO> {

        return multipartFile.map {
            uploadFile(it)
        }.toList()
    }


    companion object {
        const val BASE_PATH = "/api/file"
        const val UPLOAD_PATH = "/v1/upload"
        const val UPLOAD_MULTIPLES_PATH = "/v1/multi_upload"
        const val DOWNLOAD_PATH = "/v1/download"
    }
}
