package com.tramalho.rest.spring.boot.file.controller

import com.tramalho.rest.spring.boot.file.controller.FileController.Companion.BASE_PATH
import com.tramalho.rest.spring.boot.file.service.FileService
import com.tramalho.rest.spring.boot.file.vo.FileResponseVO
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("$DOWNLOAD_PATH/{filename:.+}")
    fun downloadFile(@PathVariable("filename") fileName: String, httpServletRequest: HttpServletRequest): ResponseEntity<Any> {

        val resource = fileService.loadFileFromResource(fileName)

        val contentType = httpServletRequest.servletContext.getMimeType(resource.file.absolutePath)

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename='${resource.filename}'")
                .body(resource)
    }


    companion object {
        const val BASE_PATH = "/api/file"
        const val UPLOAD_PATH = "/v1/upload"
        const val UPLOAD_MULTIPLES_PATH = "/v1/multi_upload"
        const val DOWNLOAD_PATH = "/v1/download"
    }
}
