package org.security.basics

import org.springframework.scheduling.annotation.Async
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.concurrent.Executors

@Service
class HelloService {

    @Async
    fun someWorkAsync() {
        try {
            val username = SecurityContextHolder.getContext().authentication?.name ?: "Anonymous"
            println("Hello $username!")
        } catch (e: Exception) {
           //
        }
    }

    fun someWorkDelegating() {
        // create a Runnable instance
        val work = Runnable {
            try {
                val username = SecurityContextHolder.getContext().authentication?.name ?: "Anonymous"
                println("Hello $username!")
            } catch (e: Exception) {}
        }
        // decorate the runnable instance with DelegatingSecurityContextRunnable
        val delegatingSecurityContextRunnable = DelegatingSecurityContextRunnable(work)

        // executor service: create and execute simple work or decorated work
        Executors.newCachedThreadPool().execute(delegatingSecurityContextRunnable)
    }
}