package org.security.basics

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class ApplicationConfig(private val authenticationProvider: CustomAuthenticationProvider) {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authenticationProvider(authenticationProvider)
            .authorizeHttpRequests { authorize -> authorize.anyRequest().authenticated() }
            .httpBasic(Customizer.withDefaults())
        return http.build()
    }

}