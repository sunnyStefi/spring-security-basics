package org.security.basics

import org.springframework.scheduling.annotation.Async
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.concurrent.Executors

@Service
class HelloService {

    @Async
    fun someWorkAsync() {
        createWork()
    }

    fun someWorkDelegating() {
        // create a Runnable instance
        val work = Runnable {
           createWork()
        }
        // decorate the runnable instance with DelegatingSecurityContextRunnable
        val delegatingSecurityContextRunnable = DelegatingSecurityContextRunnable(work)

        // executor service: create and execute simple work or decorated work
        Executors.newCachedThreadPool().execute(delegatingSecurityContextRunnable)
    }

    fun someWorkDelegatingExecutorService() {
        // create Runnable instances
        val work1 = Runnable {
            createWork()
        }
        val work2 = Runnable {
            createWork()
        }

        // create a simple executorService
        val executor = Executors.newCachedThreadPool()

        // execute work with the decorated service
        DelegatingSecurityContextExecutorService(executor).execute(work1)
        DelegatingSecurityContextExecutorService(executor).execute(work2)
    }

    private fun createWork() {
        return  try {
            val username = SecurityContextHolder.getContext().authentication?.name ?: "Anonymous"
            println("Hello $username!")
        } catch (e: Exception) {}
    }

}