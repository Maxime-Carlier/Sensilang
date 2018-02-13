package fr.unice.polytech.dsl.sensilang.test.markov;

import fr.unice.polytech.dsl.sensilang.model.markov.core.MarkovChain;
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.State;
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.Transition;
import org.junit.Before;
import org.junit.Test;

public class MarkovTest {

    private MarkovChain markovChain;

    @Before
    public void init(){
        this.markovChain = new MarkovChain();
    }

    @Test
    public void basicTest() {
        State s0 = new State(0, "zero", 0.6);
        State s1 = new State(1, "un", 37.67);
        State s2 = new State(2, "deux", 2.7);
        this.markovChain.addState(s0);
        this.markovChain.addState(s1);
        this.markovChain.addState(s2);
        this.markovChain.initMatrix();

        Transition t01 = new Transition(0, 1, 0.2);
        Transition t10 = new Transition(1, 0, 0.8);
        Transition t12 = new Transition(1, 2, 0.1);
        Transition t02 = new Transition(0, 2, 0.4);
        this.markovChain.addTransition(t01);
        this.markovChain.addTransition(t10);
        this.markovChain.addTransition(t12);
        this.markovChain.addTransition(t02);
        this.markovChain.start();

        this.markovChain.iterate();
        this.markovChain.iterate();
        this.markovChain.iterate();
        this.markovChain.iterate();
        this.markovChain.iterate();
    }

}
