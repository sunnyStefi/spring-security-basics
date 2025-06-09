package org.security.basics

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.FilterChainProxy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.session.DisableEncodeUrlFilter


@Configuration
@EnableWebSecurity(debug = true)
class BasicsConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.addFilterAt(IsHappyRequestFilter(), BasicAuthenticationFilter::class.java)
            .authorizeHttpRequests { auth ->
                auth.anyRequest().authenticated()}
        return http.build()
    }

}