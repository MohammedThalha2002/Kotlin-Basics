open class Person(age: Int, name: String) {
    init {
        println("My name is $name.")
        println("My age is $age")
    }

    constructor(age : Int, name : String, dept : String) : this(age, name) {
        println("Department is $dept")
    }

    open fun walking(){
        println("Person is walking")
    }
}

class MathTeacher(age: Int, name: String): Person(age, name) {

    fun teachMaths() {
        println("I teach in primary school.")
    }

    override fun walking(){
        println("Teacher is walking")
    }
}

class Footballer(age: Int, name: String): Person(age, name) {
    fun playFootball() {
        println("I play for LA Galaxy.")
    }
}

fun main(args: Array<String>) {
    val t1 = MathTeacher(25, "Jack")
    t1.teachMaths()

    println()

    val f1 = Footballer(29, "Christiano")
    f1.playFootball()
}