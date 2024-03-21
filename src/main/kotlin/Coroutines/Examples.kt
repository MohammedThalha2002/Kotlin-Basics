package Coroutines

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.time.measureTime

fun main() = runBlocking {
    example1()
    example2()
    println("")
}

// EXAMPLE 1
// In this suspend function, for getting each names we are running the loop sequentially, which will eventually take more time

fun example1() = runBlocking{
    var namesList : List<String>
    var time = measureTime {
        namesList = getNamesList(listOf(1,2,3,4,5,6,7));
    }
    println("WITHOUT OPTIMIZED - $time")
    println(namesList)

    time = measureTime {
        namesList = getNamesListOptimized(listOf(1,2,3,4,5,6,7));
    }
    println("WITH OPTIMIZED - $time")
    println(namesList)
}

suspend fun getNamesList(IdList : List<Int>) : List<String>{
    val namesList = mutableListOf<String>();

    for (id in IdList){
        namesList.add(getNameById(id))
    }
    return namesList
}

// So we can trigger these functions at a same time using coroutine for parallel execution
suspend fun getNamesListOptimized(IdList : List<Int>) : List<String> {
    val namesList = mutableListOf<Deferred<String>>();

    runBlocking(){
        for (id in IdList) {
            val name = async { getNameById(id) }
            namesList.add(name)
        }
    }
    return namesList.awaitAll()
}

suspend fun getNameById(id : Int) : String{
    delay(1000 + id* 10.toLong())
    return "Name $id"
}

// EXAMPLE 2
// On executing a network call it should be launched inside Dispatcher.IO context
suspend fun example2() : Result<String> {
    val res = doNetworkCall();
    return if(res == "SUCCESS"){
        Result.success("Success");
    } else {
        Result.failure(Exception())
    }
}

suspend fun doNetworkCall() : String {
    return withContext(Dispatchers.IO) {
        delay(1000)
        if (Random.nextBoolean()) "SUCCESS" else "FAILURE"
    }
}

