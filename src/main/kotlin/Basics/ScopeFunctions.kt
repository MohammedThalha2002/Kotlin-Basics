package Basics

import kotlin.random.Random

// SCOPE FUNCTIONS makes code concise and readable

fun main(){
    letFunction()
}

// 1. LET
// CONTEXT OBJECT - it
// RETURN VALUE - lambda result
// Used for null safety operations - avoid null pointer exception

fun letFunction(){
    val name : String? = null;

    // BEFORE
    //    if(name != null){
    //        println(name.length)
    //        println(name.capitalize())
    //    }

    // AFTER
    name?.let{
        println(it.length)
        println(it.capitalize())
    }

}

// 2. APPLY
// CONTEXT OBJECT - this
// RETURN VALUE - context obj
// can be used to directly access the class

// BEFORE USING ~APPLY
class Person{
    var name : String = ""
    var age : Int = 0
}
fun createPerson(){
    val p1 = Person()
    p1.name = "Thalha"
    p1.age = 22
}
// AFTER USING ~APPLY
fun createPerson1(){
    val p1 = Person().apply {
        name = "Thalha"
        age = 22
    }
}

// 3. WITH
// CONTEXT OBJECT - this
// RETURN VALUE - lambda result

fun createPerson2(){
    val p2 = Person()

    with(p2){
        println(name)
        println(age)
    }
}

/*
4. ALSO
CONTEXT OBJECT - it
RETURN VALUE - context obj
it is used when u want to access the obj and do some operation but without directly affecting the obj
*/

// BEFORE
fun beforeAlso(){
    val list = mutableListOf(1,2,3);

    // doing some operations
    println(list)
    list.add(10)
    println(list)
    list.removeAt(0)
    println(list)
    // list is totally changed
}

fun afterAlso(){
    val list = mutableListOf(1,2,3);

    val dupList = list.also {
        println(it)
        it.add(10)
        println(it)
        it.removeAt(0)
        println(it)
    }

    // both are same
    println(list)
    println(dupList)
}

// 5. RUN
// CONTEXT OBJECT - this
// RETURN VALUE - lambda result
// POWER OF [ LET + WITH ]

fun createPerson3(){
    val p3 : Person? = null

    // this throws null error
    //    with(p3){
    //        println(name)
    //        println(age)
    //    }

    // to overcome this
    p3?.run {
        println(name)
        println(age)
    }

}

