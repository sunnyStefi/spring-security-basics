package org.security.basics

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class HelloService {

    @Async
    fun someWork() {
        println("Starting work ...")
        try {
            Thread.sleep(100)
        } catch (e:InterruptedException){
            Thread.currentThread().interrupt()
        }
        println("Finishing work ...")
    }
}