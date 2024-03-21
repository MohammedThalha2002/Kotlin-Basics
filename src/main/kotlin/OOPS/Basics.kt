package OOPS

import java.util.*

class Lamp {

    // property (data member)
    private var isOn: Boolean = false

    // member function
    fun turnOn() {
        isOn = true
    }

    // member function
    fun turnOff() {
        isOn = false
    }

    fun displayLightStatus(lamp: String) {
        if (isOn == true)
            println("$lamp lamp is on.")
        else
            println("$lamp lamp is off.")
    }
}

// The primary constructor has a constrained syntax, and cannot contain any code
class Person(name : String, age : Int) {
    var name : String
    var age : Int

    // init block to put initialization code
    init {
        this.name = name.capitalize()
        this.age = age
    }

    // secondary constructor
    constructor(name : String, age : Int, dept : String) : this(name, age) {
        println("Dept is $dept")
    }
}

class Girl {
    var age: Int = 0
        get() = field
        set(value) {
            field = if (value < 18)
                18
            else if (value >= 18 && value <= 30)
                value
            else
                value-3
        }
}

fun main(){
    val p1 = Person("Thalha", 22)
    val p2 = Person("Thalha", 22, "ZOHO")

    //
    val l1 = Lamp()
    l1.turnOff()
    l1.displayLightStatus("l1")

    //
    val girl = Girl()
    girl.age = 20
    println(girl.age)
}