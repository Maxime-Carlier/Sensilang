package fr.unice.polytech.dsl.sensilang.test.markov;

import fr.unice.polytech.dsl.sensilang.model.markov.MarkovSensor;
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.State;
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.Transition;
import org.junit.Before;
import org.junit.Test;

public class MarkovTest {

    private MarkovSensor markovSensor;

    @Before
    public void init(){
        this.markovSensor = new MarkovSensor("swag");
    }

    @Test
    public void basicTest() {
        State s0 = new State(0, "zero", 0.6);
        State s1 = new State(1, "un", 37.67);
        State s2 = new State(2, "deux", 2.7);
        this.markovSensor.state(s0);
        this.markovSensor.state(s1);
        this.markovSensor.state(s2);
        this.markovSensor.init();

        Transition t01 = new Transition(0, 1, 0.2);
        Transition t10 = new Transition(1, 0, 0.8);
        Transition t12 = new Transition(1, 2, 0.1);
        Transition t02 = new Transition(0, 2, 0.4);
        Transition t20 = new Transition(2, 0, 0.3);
        this.markovSensor.transition(t01);
        this.markovSensor.transition(t10);
        this.markovSensor.transition(t12);
        this.markovSensor.transition(t02);
        this.markovSensor.transition(t20);
        this.markovSensor.start();

        System.out.println(markovSensor.getFinalValue(3));

    }

}
