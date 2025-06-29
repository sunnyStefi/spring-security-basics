package org.security.basics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.core.context.SecurityContextHolder

@SpringBootApplication
class BasicsApplication

fun main(args: Array<String>) {
    //SecurityContextHolder.setStrategyName("MODE_INHERITABLETHREADLOCAL")
    runApplication<BasicsApplication>(*args)
}
