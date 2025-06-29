package org.security.basics

import org.springframework.scheduling.annotation.Async
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class HelloService {

    @Async
    fun someWork() {
        println("Starting work ...")
        try {
            val username = SecurityContextHolder.getContext().authentication?.name ?: "Anonymous"
            println("Hello $username!")
        } catch (e: Exception) {
            Thread.currentThread().interrupt()
        }
    }
}