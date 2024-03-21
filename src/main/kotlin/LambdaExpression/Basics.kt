package LambdaExpression

fun main(){
    basics()
}

fun basics(){
    val company = { println("ZOHO")}
    // invoking function method1
    company()
    // invoking function method2
    company.invoke()

    // Here a function is stored a variable - like js
    val product = { prod : String -> println(prod) }
    // invoking
    product("ZOHO CLIQ")

    //  (Int, Int) parameter types
    //   -> String return type of the lambda function
    val add : (Int, Int) -> String = {a :Int, b : Int ->
        println("addition of a and b is ${a+b}")
        "Result is returned at the last line"
    }

    add(4,5)

    val sum = {a : Int, b : Int ->
        println("sum of a and b is ${a+b}")
        a+b // last line will be returned
    }
    val res = sum(2,4)
    println("The result is $res")
}