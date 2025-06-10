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
//            .httpBasic { c->
//                c.realmName("OTHER");
//                c.authenticationEntryPoint(CustomEntryPoint);
//            }
        http.formLogin(Customizer.withDefaults());
        return http.build()
    }
}
