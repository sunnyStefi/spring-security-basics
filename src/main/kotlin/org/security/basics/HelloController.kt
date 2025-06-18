package org.security.basics

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController (private val encoder: PasswordEncoder) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello Mama"
    }
}