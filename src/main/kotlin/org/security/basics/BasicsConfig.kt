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

        /* instead of creating a bean - which lets inject values,
        * we can also add the user to http object directly
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