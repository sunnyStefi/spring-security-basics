package org.security.basics

import org.security.basics.utilities.Sscm
import org.springframework.scheduling.annotation.Async
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService

@RestController
class HelloController(private val sscm : Sscm) {

    @Async
    @GetMapping("/hello")
    fun hello() {
    val context = SecurityContextHolder.getContext()
    val authentication = context.getAuthentication()
        "Hello ${authentication.name}!"
    }

    @GetMapping("/hello-task")
    fun hi() throws Exception
    {
        createCallableTask()
        val e = Executors.newCachedThreadPool();
        try {
            var contextTask = new DelegatingSecurityContextCallable < > (task);
            return "Ciao, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown();
        }
    }

    “@GetMapping("/hello-thread-pool")
    public String hola() throws Exception {
        createCallableTask()
        ExecutorService e = Executors.newCachedThreadPool();
        e =  DelegatingSecurityContextExecutorService(e);
        try {
            return "Hola, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }

    private fun createCallableTask : String {
        return Callable: String () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
    }
}