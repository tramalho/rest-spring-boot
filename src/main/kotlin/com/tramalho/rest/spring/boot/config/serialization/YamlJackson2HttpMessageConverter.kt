package com.tramalho.rest.spring.boot.config.serialization

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.tramalho.rest.spring.boot.config.serialization.MediaTypeUtils.Companion.APPLICATION_YAML
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter

class YamlJackson2HttpMessageConverter : AbstractJackson2HttpMessageConverter(
    OBJ_MAPPER, APPLICATION_YAML
) {

    companion object {
        private val OBJ_MAPPER = YAMLMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    }
}