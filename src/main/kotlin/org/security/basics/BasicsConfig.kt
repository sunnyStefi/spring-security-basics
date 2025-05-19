package org.security.basics

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.FilterChainProxy
import org.springframework.security.web.SecurityFilterChain


@Configuration
class BasicsConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { authorize -> authorize.anyRequest().authenticated() }
            .httpBasic(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun logFilters(filterChainProxy: FilterChainProxy): CommandLineRunner {
        return CommandLineRunner {
            println("=== Spring Security Filter Chain ===")
            filterChainProxy.filterChains.forEach { chain ->
                println("Pattern: $chain")
                chain.filters.forEach { filter ->
                    println(" - ${filter::class.qualifiedName}")
                }
            }
            println("=====================================")
        }
    }
}