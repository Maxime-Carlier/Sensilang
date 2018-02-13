package fr.unice.polytech.dsl.sensilang.language

import fr.unice.polytech.dsl.sensilang.model.sensor.AbstractSensor
import fr.unice.polytech.dsl.sensilang.model.sensor.FunctionSensor
import fr.unice.polytech.dsl.sensilang.model.sensor.FunctionalSensor
import fr.unice.polytech.dsl.sensilang.model.sensor.RandomSensor
import fr.unice.polytech.dsl.sensilang.model.sensor.ReplaySensor
import fr.unice.polytech.dsl.sensilang.model.sensor.mutators.NoiseMutator

import java.util.function.Function

abstract class SensilangBaseScript extends Script{
    // Method mapping declarations goes there
    def sayHello(String name) {
        println("Hello " + name)
    }

    def randomSensor(String id, int lower, int upper) {
        RandomSensor<Integer> s = new RandomSensor<>(id, lower, upper, Integer.class)
        ((SensilangBinding) this.getBinding()).getModel().addSensor(s)
        sensorMultiplier(s)
    }

    def functionSensor(String id) {
        ['with': { Function function ->
            FunctionSensor<Number> s = new FunctionSensor<Number>(id)
            ((SensilangBinding) this.getBinding()).getModel().addSensor(s)
            s.setLaw(function)
            sensorMultiplier(s)
        }]
    }

    def sensorMultiplier(FunctionalSensor sensor) {
        ['clone': { Integer amount ->
            SensilangModel model = ((SensilangBinding) this.getBinding()).getModel()
            for (int x = 0; x < amount; x++) {
                clone = sensor.clone()
                clone.setId(clone.getId() + "_" + x)
                model.addSensor(clone);
            }
        }]
    }

    def replaySensor(String id, Class<? extends Number> type) {
        ReplaySensor<? extends Number> replaySensor = new ReplaySensor<>(id, type)
        [forFile : { String pathToFile ->
            replaySensor.withLocalFile(pathToFile)
            [asCrossedCSV : {int columnIndex ->
                replaySensor.asCrossedCsv(columnIndex)
                ((SensilangBinding) this.getBinding()).getModel().addSensor(replaySensor)
            }]
        }]
    }

    def addNoise(double noiseIntensity, String... sensors) {
        NoiseMutator noiseMutator = new NoiseMutator(noiseIntensity)
        for (String s : sensors) {
            AbstractSensor theSensor = (AbstractSensor)((SensilangBinding) this.getBinding()).getVariable(s)
            theSensor.setMutator(noiseMutator)
        }
    }

    def export(long start, long end) {
        ((SensilangBinding) this.getBinding()).getModel().simulate(start, end)
    }

    def export(long start, long end, long increment) {
        ((SensilangBinding) this.getBinding()).getModel().simulate(start, end, increment)
    }
}
