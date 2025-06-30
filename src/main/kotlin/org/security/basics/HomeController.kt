package org.security.basics

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController{

    @GetMapping("/home")
    fun home(): String {
        return "home"
    }

    @GetMapping("/beach")
    fun beach(): String {
        return "beach"
    }
}