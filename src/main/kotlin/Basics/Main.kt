package Basics

import java.util.Scanner

fun main(){
//    basicIO()
    ifElse()
    whenStatement()
    forLoopsAndArray()
    strings()
    functions()
}

fun basicIO(){
    // using scanner class
    val n : Int = Scanner(System.`in`).nextInt();

    // using readLine() method
    val x : Int? = readLine()?.toInt();

    println("$n and $x")
}

fun ifElse(){
    val a = 20
    val b = 30

    val max = if(a > b){
        println("a is greater")
        a
    } else {
        println("b is greater")
        b
    }

//    else if also available

    println("$max is the max num")
}

fun whenStatement(){
    val name = "Sun"

    when(name) {
        "Sun" -> print("Sun is a Star")
        "Moon" -> print("Moon is a Satellite")
        "Earth" -> print("Earth is a planet")
        else -> print("I don't know anything about it")
    }
    println()
}

fun forLoopsAndArray(){
    val arr = arrayOf(1, 2, 3, 4, 5)
    for (i in 0..arr.size-1)
    {
        print(" "+arr[i])
    }

    println()

    for (i in 0..<arr.size)
    {
        print(" "+arr[i])
    }

    println()

    for (i in arr)
    {
        print(" $i")
    }

    arr.set(0, 10)  // set the first element equal to 10
    arr.set(1, 6)   // set the second element equal to 6

    println(arr.get(0)) // print the first element using get()
    println(arr[1])
}

fun strings(){
    val g = "GeeksForGeeks"
    val e = "Geeks"
    println(g.length)
    println(g[4])
    println(g.get(4))
    println(g.subSequence(0, 5))
    println(g.compareTo(e))
}

private fun functions(){
    fun a(){
        println("a is called")
    }

    a()

    fun student( name: String="Parveen", standard: String="IX" , roll_no: Int=11 ) {
        println("Name of the student is: $name")
        println("Standard of the student is: $standard")
        println("Roll no of the student is: $roll_no")
    }

    val name_of_student = "Gaurav"
    val standard_of_student = "VIII"
    val roll_no_of_student = 25

    // passing the arguments with name as defined in function
    student(name=name_of_student,roll_no=roll_no_of_student)

}