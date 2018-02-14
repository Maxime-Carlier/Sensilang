package fr.unice.polytech.dsl.sensilang.model.markov;

import fr.unice.polytech.dsl.sensilang.model.markov.core.MarkovChain;
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.State;
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.Transition;
import fr.unice.polytech.dsl.sensilang.model.sensor.AbstractSensor;

public class MarkovSensor extends AbstractSensor {

    private MarkovChain markovChain;

    public MarkovSensor(String id) {
        super(id);
        this.markovChain = new MarkovChain();
    }

    public void init() {
        this.markovChain.initMatrix();
    }

    public void start() {
        this.markovChain.start();
    }

    public void state(State state) {
        this.markovChain.addState(state);
    }

    public void transition(Transition transition) {
        this.markovChain.addTransition(transition);
    }

    @Override
    protected Number valueAt(long timeStamp) {
        State res = null;
        for (long i = 0; i < timeStamp; i++) {
            res = this.markovChain.iterate();
        }
        return res.getValue();
    }
}
