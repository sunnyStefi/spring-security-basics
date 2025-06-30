package org.security.basics

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(val service: HelloService) {

    // MODE_THREADLOCAL
    @GetMapping("/hello")
    fun hello(injectedAuthentication: Authentication) : String {
    val contextAuthentication = SecurityContextHolder.getContext().authentication
    if (contextAuthentication == injectedAuthentication)
        return """
            Hello ${contextAuthentication.name} <br>
            This is my principal: ${injectedAuthentication.principal} <br>
            These are my details: ${injectedAuthentication.details} <br>
            """.trimIndent()
    return "oops"
    }

    // MODE_INHERITABLETHREADLOCAL
    @GetMapping("/hello-async")
    fun helloAsync() {
        return service.someWorkAsync()
    }

    // DelegatingSecurityContextRunnable
    @GetMapping("/hello-delegating")
    fun helloDelegating() {
        return service.someWorkDelegating()
    }

    // DelegatingSecurityContextRunnable
    @GetMapping("/hello-delegating-service")
    fun helloDelegatingExecutorService() {
        return service.someWorkDelegatingExecutorService()
    }
}