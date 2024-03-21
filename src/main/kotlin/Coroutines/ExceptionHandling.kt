package Coroutines

import kotlinx.coroutines.*

fun main(){
    runBlocking {
        println("Weather report")
        getWeatherReports()
        println("Have a good day")
    }
}
/*
when any one of the child coroutines fails it goes to the parent stops all the child of the parent coroutine
 */
suspend fun getWeatherReports() = coroutineScope {
    val weather = async { getWeather() }
    val temp = async {
        try {
            getTemp()
        } catch (e: AssertionError) {
            println("** ERROR $e **")
            "{Temp failed to calculate}"
        }
    }
    println("${weather.await()} and ${temp.await()}")
}

suspend fun getWeather() : String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemp() : String {
    delay(1000)
    throw AssertionError("Sensor fails to find the temperature")
    return "30 deg"
}