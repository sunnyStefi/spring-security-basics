package org.security.basics.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ApplicationConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                auth.anyRequest().authenticated()
            }
            .httpBasic { c ->
                c.authenticationEntryPoint(CustomEntryPoint())
            }
            .formLogin { c -> c.successHandler(CustomSuccessHandler())
            }
        return http.build()
    }
}
