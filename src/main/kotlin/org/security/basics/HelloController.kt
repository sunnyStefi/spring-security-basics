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

    //Strategy #2: multi-thread per request
//    @GetMapping("/hello-async")
//    fun hello() : String {
//        val context = SecurityContextHolder.getContext()
//        val authentication = context.getAuthentication()
//        return "Hello ${authentication.name}!"
//    }
//
//    @GetMapping("/hello-task")
//    fun helloAsync() throws Exception
//    {
//        createCallableTask()
//        val e = Executors.newCachedThreadPool();
//        try {
//            var contextTask = new DelegatingSecurityContextCallable < > (task);
//            return "Ciao, " + e.submit(contextTask).get() + "!";
//        } finally {
//            e.shutdown();
//        }
//    }
//
//    “@GetMapping("/hello-thread-pool")
//    public String hola() throws Exception {
//        createCallableTask()
//        ExecutorService e = Executors.newCachedThreadPool();
//        e =  DelegatingSecurityContextExecutorService(e);
//        try {
//            return "Hola, " + e.submit(task).get() + "!";
//        } finally {
//            e.shutdown();
//        }
//    }
//
//    private fun createCallableTask : String {
//        return Callable: String () -> {
//            SecurityContext context = SecurityContextHolder.getContext();
//            return context.getAuthentication().getName();
//        };
//    }
}