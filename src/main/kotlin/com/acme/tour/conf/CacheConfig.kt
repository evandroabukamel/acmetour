package com.acme.tour.conf

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class CacheConfig {

    @Value("\${spring.profiles.active}")
    lateinit var profile: String

    @Bean
    fun cacheManager(): CacheManager? {
        return if (profile == "prod")
            ConcurrentMapCacheManager("promotions")
        else null
    }
}
