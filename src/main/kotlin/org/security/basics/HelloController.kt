package org.security.basics

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController{

    @GetMapping("/hello")
    fun hello(): String {
    val context = SecurityContextHolder.getContext()
    val authentication = context.getAuthentication()
        return "Hello ${authentication.name}!"
    }

}