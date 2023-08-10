package com.tramalho.rest.spring.boot.file.service

import com.tramalho.rest.spring.boot.config.exception.FileStorageException
import com.tramalho.rest.spring.boot.file.FileStorageConfig
import org.springframework.stereotype.Service
import com.tramalho.rest.spring.boot.file.model.FileModel
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileService(private val fileStorageConfig: FileStorageConfig) {

    fun storeFile(multipartFile: MultipartFile): String {

        var fileName = ""

        try {
            fileName = StringUtils.cleanPath(multipartFile.originalFilename ?: fileName)

            val targetLocation = resolveDir().resolve(fileName)

            Files.copy(multipartFile.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)

        } catch (fe: FileStorageException) {
            throw fe
        } catch (ex: Exception) {
            throw FileStorageException("Could not storage file: $fileName", ex)
        }

        return fileName
    }

    private fun resolveDir(): Path {
        val path = Paths.get(fileStorageConfig.uploadDir)
        path.toAbsolutePath().normalize()

        try {
            if (!Files.exists(path)) {
                Files.createDirectory(path)
            }
        } catch (ex: Exception) {
            throw FileStorageException("Could not create Directory ${path.toUri().path}", ex)
        }

        return path
    }
}