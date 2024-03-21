package LambdaExpression

import kotlin.reflect.KFunction1

// a function which is passed as the parameter for the another function is called higher order function

fun main(){
    reactUseState()
}

fun reactUseState(){
    var count = 0;
    val title = "REACT APP"

    fun child(title: String, count: Int, setCount: (Int) -> Int){
        println("The title is $title")
        println("The count val is $count")
        var count = setCount(count+1)
        println("Now the count value is $count")
    }

    fun setCount(n : Int) : Int{
        count = n;
        println("count updated")
        return count
    }

    child(title, count, ::setCount);

    var setCountLambda : (Int) -> Int = {n : Int ->
        count = n
        println("count updated")
        count
    }

    child(title, count, setCountLambda);
}
