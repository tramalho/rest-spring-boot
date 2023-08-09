package com.tramalho.rest.spring.boot.file

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "file")
data class FileStorageConfig(val uploadDir: String)



