package org.security.basics

import org.security.basics.utilities.Sscm
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val sscm : Sscm) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello Mama"
    }

}