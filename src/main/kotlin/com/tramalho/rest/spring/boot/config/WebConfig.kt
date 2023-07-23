package com.tramalho.rest.spring.boot.config

import com.tramalho.rest.spring.boot.config.serialization.MediaTypeUtils
import com.tramalho.rest.spring.boot.config.serialization.YamlJackson2HttpMessageConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

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
        converters.add(YamlJackson2HttpMessageConverter())
        super.extendMessageConverters(converters)
    }
}