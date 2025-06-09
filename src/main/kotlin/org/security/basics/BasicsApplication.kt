package org.security.basics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude =[UserDetailsServiceAutoConfiguration::class])
class BasicsApplication

fun main(args: Array<String>) {
    runApplication<BasicsApplication>(*args)
}
