package org.security.basics.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ApplicationConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.formLogin{form ->  form
                .defaultSuccessUrl("https://localhost:8080/hello", true) }
        return http.build()
    }
}
