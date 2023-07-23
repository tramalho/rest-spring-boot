package com.tramalho.rest.spring.boot.config.serialization

import org.springframework.http.MediaType

class MediaTypeUtils {

    companion object {
        private const val APPLICATION_YAML_VALUE = "application/x-yaml"
        val APPLICATION_YAML = MediaType.parseMediaType(APPLICATION_YAML_VALUE)

        val SUPPORTED_MEDIA_TYPES = mapOf(
            "xml" to MediaType.APPLICATION_XML,
            "json" to MediaType.APPLICATION_JSON,
            "x-yaml" to APPLICATION_YAML,
        )
    }
}