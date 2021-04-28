package com.acme.tour.conf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig(private var environment: Environment) {

    @Bean
    fun storeApi(): Docket? {
        return if (environment.activeProfiles.contains("prod"))
            null
        else
            Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metadata())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.acme.tour"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun metadata() =
    ApiInfoBuilder()
        .title("API Acme Tour")
        .description("Acme Tour API methods.")
        .version("1.0.0")
        .build()
}
