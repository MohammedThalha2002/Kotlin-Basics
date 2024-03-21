//https://developer.android.com/codelabs/basic-android-kotlin-compose-coroutines-kotlin-playground#3

package Coroutines
import kotlinx.coroutines.*

fun main() {
    //  By default coroutines are executed in sequential manner
    runBlockingCode()
    coroutineScopeCode()
    coroutinesLaunch()
    coroutinesAsyncAwait()
}

// SUSPENDING VS BLOCKING
/*
* BLOCKING - blocks the thread and hence the thread has to wait
* SUSPENDING - suspends the thread and hence the thread can by utilized by other
* */

// Run Blocking - BLOCKING
fun runBlockingCode(){
    // runBlocking method blocks the current thread for waiting
    runBlocking {
        checkWeather();
        checkTemp();
        println("Weather report completed [${Thread.currentThread().name}]")
    }
}

// suspending fn can be called only inside another suspending fn or other coroutine scope
fun coroutineScopeCode(){
    runBlocking {
        // coroutineScope method only suspends -> suspending function
        coroutineScope {
            checkWeather();
            checkTemp();
            println("Weather report completed [${Thread.currentThread().name}]")
        }
    }
}

suspend fun checkWeather(){
    delay(1000)
    println("Sunny [${Thread.currentThread().name}]")
}

suspend fun checkTemp(){
    delay(1000)
    println("30 deg [${Thread.currentThread().name}]")
}

/*
GlobalScope.launch || GlobalScope.async ==> used to run the coroutines for the global scope like file downloading
launch || async ==> used to run the local logic like login and the thread it will be started running is the parent thread
*/

/*
"fire and forget" is the nature of launch()
 */
fun coroutinesLaunch() = runBlocking {
    println("Opening instagram")

    val job : Job = launch {
        println("Started downloading the reels from the server")
        delay(2000)
        println("Download completed")
    }

    // job.join() -> it can be used when we need to execute the coroutine before the below code || controlling the execution
    job.join()
    println("Now users can see the reels")
}

/*
* Use the async() function from the coroutines library if you care about when the coroutine finishes and need
*  a return value from it.
*/
fun coroutinesAsyncAwait() = runBlocking {
    println("Opening YouTube")
    println("Clicking on download button of a video")

    val jobDeferred : Deferred<String> = async {
        println("Started downloading...")
        delay(2000)
        "Video downloaded"
    }

    val res = jobDeferred.await()
    println(res)
}

// BEST PRACTICE - take multiple concurrent operations and put it into a single synchronous operation