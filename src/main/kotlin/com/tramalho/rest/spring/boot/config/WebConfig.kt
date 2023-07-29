package com.tramalho.rest.spring.boot.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JSR310Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.tramalho.rest.spring.boot.config.serialization.MediaTypeUtils
import com.tramalho.rest.spring.boot.config.serialization.YamlJackson2HttpMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.format.DateTimeFormatter


@Configuration
class WebConfig : WebMvcConfigurer {

    @Value("\${cors.originPatterns:default}")
    private var corsOriginPatterns = ""

    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        super.configureContentNegotiation(configurer)

        configurer
            .favorParameter(false)
            //.favorParameter(true)
            //.parameterName("mediaType")
            //.ignoreAcceptHeader(true)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaTypes(MediaTypeUtils.SUPPORTED_MEDIA_TYPES)
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        //converters.add(YamlJackson2HttpMessageConverter())
        super.extendMessageConverters(converters)
    }


    override fun addCorsMappings(registry: CorsRegistry) {
        val allowedOrigins = corsOriginPatterns.split(",").toTypedArray()
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins(*allowedOrigins)
            .allowCredentials(false)

        super.addCorsMappings(registry)
    }
}