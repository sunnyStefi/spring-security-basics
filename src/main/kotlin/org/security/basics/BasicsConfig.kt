package org.security.basics

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class BasicsConfig {

    /** Customization Style #1: Bean
     *  more flexible since it allows to inject it in different part of the application
     * */
    @Bean
    fun userDetails(): UserDetailsService {
        val user = User.withUsername("user")
            .password("{noop}12345")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic(Customizer.withDefaults())
        http.authorizeHttpRequests { auth -> auth.anyRequest().authenticated() }

        /* Customization Style #2:
        *  add the user to http object directly
        *
        val user = User.withUsername("user")
            .password("{noop}12345")
            .roles("USER")
            .build()
        val userDetailsService = InMemoryUserDetailsManager(user)
        http.userDetailsService(userDetailsService)
        * */

        return http.build()
    }
}