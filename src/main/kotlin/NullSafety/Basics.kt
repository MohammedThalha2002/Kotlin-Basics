package NullSafety

fun main(){
    basics()
    notNullAssertion()
}

fun basics(){
    var s1: String = "Hello"
    // s1 = null // compilation error

    var s2: String? = "Hello"
    s2 = null // ok
    print(s2)

    // safe call operator
    println(s2?.length)
}

fun notNullAssertion(){
//    The not null assertion (!!) operator converts any value to a non-null type and throws an exception if the value is null.
//    If anyone want NullPointerException then he can ask explicitly using this operator.

    val str : String?  = "ZOHO"
    println(str!!.length)
}