package com.lignting.api.configs

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfiguration {
    @Bean
    fun springShopOpenAPI(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("BlogDemo by lignting")
                    .contact(Contact())
                    .description("a blog website create by lignting")
                    .version("v1.0.0")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
//            .externalDocs(
//                ExternalDocumentation()
//                    .description("外部文档")
//                    .url("https://springshop.wiki.github.org/docs")
//            )
}