package org.example.Coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {

    println("PROGRAM STARTED IN ${Thread.currentThread().name}")

    // CONFINED DISPATCHER
    // Used without any parameters
    // inherit coroutine context from the immediate parent even after some suspending function
    val job1 = launch {
        println("----------------------------")
        println("C1 thread is ${Thread.currentThread().name}") // Thread Main
        delay(100L)
        println("C1 after delay thread is ${Thread.currentThread().name}") // Thread Main
    }

    // DEFAULT DISPATCHER
    // runs on a global thread but after some suspending function the thread may vary
    val job2 = launch(Dispatchers.Default) {
        println("----------------------------")
        println("C2 thread is ${Thread.currentThread().name}") // Thread T1
        delay(100L)
        println("C2 after delay thread is ${Thread.currentThread().name}") // Thread T1 or vary
    }

    // UNCONFINED DISPATCHER
    // inherit coroutine context from the immediate parent but after some suspending function the thread may vary
    val job3 = launch(Dispatchers.Unconfined) {
        println("----------------------------")
        println("C3 thread is ${Thread.currentThread().name}") // Thread Main
        delay(100L)
        println("C3 after delay thread is ${Thread.currentThread().name}") // Thread Main or vary
    }

    job1.join()
    job2.join()
    job3.join()
    println("PROGRAM ENDED IN ${Thread.currentThread().name}")
}
