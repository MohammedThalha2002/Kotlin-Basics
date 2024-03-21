package Basics

fun main(){
    val bird = Bird()
    val chicken = Chicken()
    bird.makeSound()
    chicken.makeSound()
}

interface Animal {
    val name : String
    val canFly : Boolean
    fun makeSound()
}

class Bird : Animal {
    override val name: String = "Bird"
    override val canFly: Boolean = true
    override fun makeSound() {
        println("chik chik")
    }
}

class Chicken : Animal by Bird(){
    override val name: String = "Chicken"
    override val canFly: Boolean = false
}