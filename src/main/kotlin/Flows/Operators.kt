package Flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException

fun main(){
//    startLongRunningTask()
    retryTask()
}

fun printLog(message : String){
    println("[${Thread.currentThread().name}] ${message}")
}

fun startLongRunningTask() = runBlocking {
    launch {
        doLongRunningTaskOne()
            .zip(doLongRunningTaskTwo()) { resultOne, resultTwo ->
                return@zip resultOne + resultTwo
            }
            .flowOn(Dispatchers.Default)
            .catch { e ->
                // handle exception
                printLog(e.message.toString())
            }
            .collect {res ->
                // result
                printLog(res)
            }
    }
}

private fun doLongRunningTaskOne(): Flow<String> {
    return flow {
        // your code for doing a long-running task
        // Added delay to simulate
        delay(5000)
        emit("One")
    }
}

private fun doLongRunningTaskTwo(): Flow<String> {
    return flow {
        // your code for doing a long-running task
        // Added delay to simulate
        delay(4000)
        emit("Two")
    }
}

fun retryTask() = runBlocking {
    launch {
        longRunningTask()
            .flowOn(Dispatchers.Default)
            .retryWhen { cause, attempt ->
                if (cause is IOException && attempt < 3) {
                    delay(2000)
                    printLog("retrying - $attempt | cause - $cause")
                    return@retryWhen true
                } else {
                    return@retryWhen false
                }
            }
            .catch {e->
                // error
                printLog(e.message.toString())
            }
            .collect {
                // success
                printLog(it.toString())
            }
    }
}


private fun longRunningTask(): Flow<Int> {
    return flow {
        // your code for doing a long-running task
        // Added delay, random number, and exception to simulate
        delay(2000)
        val randomNumber = (0..3).random()
        if (randomNumber == 0) {
            throw IOException()
        } else if (randomNumber == 1) {
            throw IndexOutOfBoundsException()
        }
        delay(500)
        emit(randomNumber)
    }
}
