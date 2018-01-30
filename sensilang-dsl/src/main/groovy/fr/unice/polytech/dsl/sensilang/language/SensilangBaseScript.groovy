package fr.unice.polytech.dsl.sensilang.language

abstract class SensilangBaseScript extends Script{
    // Method mapping declarations goes there
    def sayHello(String name) {
        println("Hello " + name)
    }
}
