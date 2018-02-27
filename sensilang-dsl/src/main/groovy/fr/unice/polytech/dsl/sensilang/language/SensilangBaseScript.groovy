package fr.unice.polytech.dsl.sensilang.language

import fr.unice.polytech.dsl.sensilang.main.SensilangPoint
import fr.unice.polytech.dsl.sensilang.model.markov.MarkovSensor
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.State
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.Transition
import fr.unice.polytech.dsl.sensilang.model.sensor.*
import fr.unice.polytech.dsl.sensilang.model.sensor.mutators.NoiseMutator

import java.util.function.Function

abstract class SensilangBaseScript extends Script {
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

    /* MARKOV */

    def markovSensor(String id) {
        MarkovSensor sensor = new MarkovSensor(id)
        ((SensilangBinding) this.getBinding()).getModel().addSensor(sensor)
    }

    def markovState(String id) {
        try {
            MarkovSensor sensor = ((SensilangBinding) this.getBinding()).getModel().getMarkovSensor(id)
            State state
            [add: { int stateId, String stateName, double stateValue ->
                state = new State(stateId, stateName, stateValue)
                sensor.state(state)
            }]
        }
        catch (NullPointerException e) {
            System.err.println("Wrong id, that markovian sensor doesn't exist.");
        }
    }

    def markovTransition(String id) {
        try {
            MarkovSensor sensor = ((SensilangBinding) this.getBinding()).getModel().getMarkovSensor(id);
            Transition transition
            [add: { int initialStateId, int finalStateId, double probability ->
                transition = new Transition(initialStateId, finalStateId, probability)
                sensor.transition(transition)
            }]
        }
        catch (Exception e) {
            System.err.println("Wrong id, that markovian sensor doesn't exist.");
        }
    }


    def markovInit(String id) {
        MarkovSensor sensor = ((SensilangBinding) this.getBinding()).getModel().getMarkovSensor(id)
        sensor.init()
    }

    def markovStart(String id) {
        MarkovSensor sensor = ((SensilangBinding) this.getBinding()).getModel().getMarkovSensor(id)
        sensor.start()
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
        [forFile: { String pathToFile ->
            replaySensor.withLocalFile(pathToFile)
            [asCrossedCSV: { int columnIndex ->
                replaySensor.asCrossedCsv(columnIndex)
                ((SensilangBinding) this.getBinding()).getModel().addSensor(replaySensor)
            }]
        }]
    }

    def addNoise(double noiseIntensity, String... sensors) {
        NoiseMutator noiseMutator = new NoiseMutator(noiseIntensity)
        for (String s : sensors) {
            AbstractSensor theSensor = (AbstractSensor) ((SensilangBinding) this.getBinding()).getVariable(s)
            theSensor.setMutator(noiseMutator)
        }
    }

    def simulate(long start, long end) {
        ((SensilangBinding) this.getBinding()).getModel().simulate(start, end)
    }

    def simulate(long start, long end, long increment) {
        ((SensilangBinding) this.getBinding()).getModel().simulate(start, end, increment)
    }

    def export(long start, long end) {
        ((SensilangBinding) this.getBinding()).getModel().compute(start, end, 1)
    }

    def export(long start, long end, long increment) {
        ((SensilangBinding) this.getBinding()).getModel().compute(start, end, increment)
    }

    def save(List<SensilangPoint> points) {
        ((SensilangBinding) this.getBinding()).getModel().save(points)
    }
}
