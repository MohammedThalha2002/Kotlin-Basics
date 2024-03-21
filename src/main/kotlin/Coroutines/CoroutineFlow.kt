package org.example.Coroutines

import kotlinx.coroutines.*
import kotlin.time.measureTime

fun main(){
    sequentialExecution()
    concurrentExecution()
    lazyExecution()
}

fun sequentialExecution() = runBlocking{
    println("----- Sequential Execution -----")
    val time = measureTime {
      sendInvoice()
      sendMail()
    }

    println("Time taken is $time")
}

// executed in parallel
fun concurrentExecution() = runBlocking {
    println()
    println("----- Concurrent Execution -----")
    val time = measureTime {
        launch { sendInvoice() }
        launch { sendMail() }
    }

    println("Time taken is $time")
}

// executed in parallel
fun lazyExecution() = runBlocking {
    println()
    println("----- Lazy Execution -----")
    val time = measureTime {
        val invoice =  async(start = CoroutineStart.LAZY) { sendInvoice() }
        val mail  = async(start = CoroutineStart.LAZY) { sendMail() }
        // if we run without the await method the coroutine will run directly which is wasting of the resource
        // this can be overcome through lazy by adding async(start = CoroutineStart.LAZY)
        // now this will be executed only when the await method is called
//        invoice.await()
//        mail.await()
    }

    println("Time taken is $time")
}

suspend fun sendInvoice(){
    delay(100L)
    println("Invoice sent successfully")
}

suspend fun sendMail(){
    delay(100L)
    println("Mail sent successfully")
}