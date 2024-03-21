package Coroutines

import kotlinx.coroutines.*


fun main() = runBlocking{
    supervisorScope {
        val job1  = launch {
            delay(1000)
            throw AssertionError("Error")
            println("task 1")
        }
        val job2 = launch {
            delay(1000)
            println("task 2")
        }

        job1.join()
        job2.join()

        delay(1000)
        println("Completed")
    }
}


