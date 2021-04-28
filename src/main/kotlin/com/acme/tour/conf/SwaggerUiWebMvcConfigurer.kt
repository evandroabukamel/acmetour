package com.acme.tour.conf

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class SwaggerUiWebMvcConfigurer(
    @Value("\${springfox.documentation.swagger-ui.base-url:}")
    private val baseUrl: String
) : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val baseUrl = StringUtils.trimTrailingCharacter(baseUrl, '/')
        registry.addResourceHandler("$baseUrl/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
            .resourceChain(false)
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("$baseUrl/swagger-ui/")
            .setViewName("forward:$baseUrl/swagger-ui/index.html")
    }
}
