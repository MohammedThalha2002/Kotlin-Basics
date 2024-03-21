package Flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun main() = runBlocking {
    imageDownloading()
}

//The major components of Flow are as below:
// 1.Flow Builder -> (speaker) emits the values
// 2.Operator -> (translator) modifies the values
// 3.Collector -> (listener) collects and shows the value

//Types of flow builders
//
//flowOf(): It is used to create flow from a given set of items.
//asFlow(): It is an extension function that helps to convert type into flows.
//flow{}: This is what we have used in the Hello World example of Flow.
//channelFlow{}: This builder creates flow with the elements using send provided by the builder itself.

// flowOn Operator
//
//flowOn Operator is very handy when it comes to controlling the thread on which the task will be done.

suspend fun imageDownloading(){
    val imageDownloadingValue = flow {
        delay(200)
        emit("10%")
        delay(200)
        emit("50%")
        delay(200)
        emit("70%")
        delay(200)
        emit("100%")
    }.flowOn(Dispatchers.IO)

    imageDownloadingValue.collect {
        log(it)
    }
}

// COLD VS HOT FLOW

//In Cold Flow, in the case of multiple collectors, the complete flow will begin from the beginning for
// each one of the collectors, do the task and emit the values to their corresponding collectors.
// It's like 1-to-1 mapping. 1 Flow for 1 Collector. It means a cold flow can't have multiple collectors
// as it will create a new flow for each of the collectors.
//
//In Hot Flow, in the case of multiple collectors, the flow will keep on emitting the values,
// collectors get the values from where they have started collecting. It's like 1-to-N mapping.
// 1 Flow for N Collectors. It means a hot flow can have multiple collectors.

fun log(message : String){
    println("[${Thread.currentThread().name}] $message")
}
