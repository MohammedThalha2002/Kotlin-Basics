package OOPS

//class Outer {
//    val foo = "Outside Nested class."
//
//    class Nested {
//        // Error! cannot access member of outer class.
//        fun callMe() = foo
//    }
//}
//
//fun main(args: Array<String>) {
//
//    val outer = Outer()
//    println(outer.Nested().callMe())
//}

//The above code won't compile because we tried to access foo property of Outer class from inside Nested class.
//
//In order to solve this issue, you need to mark the nested class with inner to create an inner class. Inner classes carry a reference to an outer class, and can access outer class members.

class Outer {

    val a = "Outside Nested class."

    inner class Inner {
        fun callMe() = a
    }
}

fun main(args: Array<String>) {

    val outer = Outer()
    println("Using outer object: ${outer.Inner().callMe()}")

    val inner = Outer().Inner()
    println("Using inner object: ${inner.callMe()}")
    dataClass()
}

// DATA CLASS

data class User(val name: String, val age: Int)

fun dataClass() {
    val jack = User("jack", 29)
    println("name = ${jack.name}")
    println("age = ${jack.age}")

    println(jack.toString())
}