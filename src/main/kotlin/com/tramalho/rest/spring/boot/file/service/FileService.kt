package com.tramalho.rest.spring.boot.file.service

import com.tramalho.rest.spring.boot.config.exception.FileStorageException
import com.tramalho.rest.spring.boot.config.exception.MyFileNotFoundException
import com.tramalho.rest.spring.boot.file.FileStorageConfig
import org.apache.commons.lang3.CharSet
import org.apache.tomcat.util.buf.Utf8Encoder
import org.springframework.stereotype.Service
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.net.URI
import java.net.URL
import java.net.URLDecoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileService(private val fileStorageConfig: FileStorageConfig) {

    fun storeFile(multipartFile: MultipartFile): String {

        var fileName = ""

        try {
            fileName = URLDecoder.decode(StringUtils.cleanPath(multipartFile.originalFilename ?: fileName), StandardCharsets.UTF_8)
            fileName = fileName.replace(Regex("[^\\w.]"), "_")

            val targetLocation = resolvePath(fileName)

            Files.copy(multipartFile.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)

        } catch (fe: FileStorageException) {
            throw fe
        } catch (ex: Exception) {
            throw FileStorageException("Could not storage file: $fileName", ex)
        }

        return fileName
    }

    fun loadFileFromResource(filename: String): Resource {

        try {

            val targetLocation = resolvePath(filename)

            UrlResource(targetLocation.toUri()).run {
                if (exists()) {
                    return this
                }
            }

        } catch (e: Exception) {
            throw MyFileNotFoundException("File not found: $filename", e)
        }

        throw MyFileNotFoundException("File not found: $filename")
    }

    private fun resolvePath(fileName: String): Path {
        val path = Paths.get(fileStorageConfig.uploadDir)
        path.toAbsolutePath().normalize()

        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path)
            }
        } catch (ex: Exception) {
            throw FileStorageException("Could not create Directory ${path.toUri().path}", ex)
        }

        return path.resolve(fileName).normalize()
    }
}