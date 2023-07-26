package com.tramalho.rest.spring.boot.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {

    fun customOpenAPI(): OpenAPI {

        val license = License()
            .name("Apache 2.0")
            .url("https://www.apache.org/licenses/LICENSE-2.0")

        val info = Info()
        info.title("Spring Boot 3 + Kotlin 1.9.0")
            .version("v1")
            .description("Some Description about this project")
            .termsOfService("https://en.wikipedia.org/wiki/Terms_of_service")
            .license(license)

        return OpenAPI().info(info)
    }
}