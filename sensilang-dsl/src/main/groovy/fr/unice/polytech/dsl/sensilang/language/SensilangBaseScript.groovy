package fr.unice.polytech.dsl.sensilang.language

import fr.unice.polytech.dsl.sensilang.model.sensor.RandomSensor

abstract class SensilangBaseScript extends Script{
    // Method mapping declarations goes there
    def sayHello(String name) {
        println("Hello " + name)
    }

    def RandomSensor(int lower, int upper) {
        RandomSensor<Integer> s = new RandomSensor<>(lower, upper, Integer.class)
        ((SensilangBinding) this.getBinding()).getModel().addSensor(s)
    }

    def export(long start, long end) {
        ((SensilangBinding) this.getBinding()).getModel().simulate(start, end)
    }
}
