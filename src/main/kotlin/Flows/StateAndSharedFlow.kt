package Flows

import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking{
    async { stateFlowFun() }
    async { sharedFlowFun() }
}

// -- StateFlow --
//Hot Flow
//Needs an initial value and emits it as soon as the collector starts collecting.
//val stateFlow = MutableStateFlow(0)
//Only emits the last known value.
//It has the value property, we can check the current value.
//It does not emit consecutive repeated values. It emits the value only when it is distinct from the previous item.


suspend fun stateFlowFun() = runBlocking{
    println("-- STATEFLOW --")
    val stateFlow = MutableStateFlow(0);
    // Collect values from stateFlow
    launch {
        stateFlow.collect { value ->
            println("Collector 1 received: $value")
        }
    }

// Collect values from stateFlow
    launch {
        stateFlow.collect { value ->
            println("Collector 2 received: $value")
        }
    }

// Update the state
    launch {
        repeat(3) { i ->
            stateFlow.value = i
        }
    }
}

// -- SharedFlow --
//Hot Flow
//Does not need an initial value so does not emit any value by default.
//val sharedFlow = MutableSharedFlow<Int>()
//Can be configured to emit many previous values using the replay operator.
//It does not have the value property.
//It emits all the values and does not care about the distinct from the previous item.
// It emits consecutive repeated values also.


fun sharedFlowFun() = runBlocking{
    println("-- SHARED FLOW --")
    val sharedFlow = MutableSharedFlow<Int>()

// Collect values from sharedFlow
    launch {
        sharedFlow.collect { value ->
            println("Collector 1 received: $value")
        }
    }

// Collect values from sharedFlow
    launch {
        sharedFlow.collect { value ->
            println("Collector 2 received: $value")
        }
    }

// Emit values to sharedFlow
    launch {
        repeat(3) { i ->
            sharedFlow.emit(i)
        }
    }
}