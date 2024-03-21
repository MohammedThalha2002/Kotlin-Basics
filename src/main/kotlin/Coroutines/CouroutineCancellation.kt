package org.example.Coroutines

import kotlinx.coroutines.*

// Coroutines can be cancelled only when they are cooperative
// They are said to be cooperative if delay, yield, withTimeout, withContext are used within the coroutine

/*
If a parent job gets cancelled, then its child jobs also get cancelled.
When a child job is canceled using job.cancel(), it terminates, but it does not cancel its parent.
If a job fails with an exception, it cancels its parent with that exception. This is known as propagating the error upwards (to the parent, the parent's parent, and so on). .
 */

fun main(){
    // non cooperative coroutine
    println("---- NON-COOPERATIVE COROUTINE----")
    nonCooperativeCoroutine()

    // cooperative coroutine
    println("---- COOPERATIVE COROUTINE----")
    cooperativeCoroutine()
}

fun nonCooperativeCoroutine() = runBlocking {
    println("Program started")

    val job : Job = launch {
        for (i in 1..100){
            println("$i ")
            Thread.sleep(50)
        }
    }

    delay(500)
    job.cancel()  // as delay | withTimeout are not used within the coroutine... the coroutine can't be cancelled
    job.join()

    println("Program ended")
}

fun cooperativeCoroutine() = runBlocking {
    println("Program started")

    val job : Job = launch {
        for (i in 1..100){
            println("$i ")
            delay(50)
        }
    }

    delay(500)
    job.cancel()  // as delay is used within the coroutine... the coroutine will become cooperative and thus will be cancelled after the delay of 500
    job.join()

    println("Program ended")
}