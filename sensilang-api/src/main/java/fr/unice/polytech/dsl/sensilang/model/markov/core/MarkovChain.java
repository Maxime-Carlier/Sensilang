package fr.unice.polytech.dsl.sensilang.model.markov.core;

import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.State;
import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.Transition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarkovChain {

    public static final Logger logger = Logger.getLogger("markov");

    private Map<Integer, State> stateMap;
    private MarkovIterator markov;
    private boolean markovInitiated;
    private boolean running;

    public MarkovChain() {
        this.stateMap = new HashMap<>();
        this.markovInitiated = false;
        // TODO: add this condition to instantiations methods
        this.running = false;
    }

    public void addState(State state) {
        if (!this.markovInitiated) {
            this.stateMap.put(state.getIndex(), state);
        }
        else {
            throw new IllegalStateException("Matrix already instantiated, cannot add states anymore.");
        }
    }

    public void initMatrix() {
        if (!this.markovInitiated) {
            this.markov = new MarkovIterator(this.stateMap.size());
            this.markovInitiated = true;
        }
        else {
            throw new IllegalStateException("Matrix already instantiated, cannot add states anymore.");
        }
    }

    public void addTransition(Transition t) {
        if (this.markovInitiated) {
            this.markov.addTransition(t);
        }
        else {
            throw new IllegalStateException("Matrix not instantiated yet, cannot add transition.");
        }
    }

    public void start() {
        logger.log(Level.INFO, "States: " + this.stateMap.toString());
        logger.log(Level.INFO, "Matrix: " + Arrays.deepToString(this.markov.getMatrix().getMatrix()));
        logger.log(Level.INFO, "Starting chain...");
        this.markov.start();
        this.running = true;
        logger.log(Level.INFO, "Matrix filled: " + Arrays.deepToString(this.markov.getMatrix().getMatrix()));
    }

    public State iterate() {
        State result;
        if (this.markov.hasNext()) {
            int stateIndex = this.markov.next();
            result = this.stateMap.get(stateIndex);
            logger.log(Level.INFO, result.toString());
        }
        else {
            logger.log(Level.WARNING, "Cannot iterate, hasNext is false.");
            throw new IndexOutOfBoundsException("Cannot iterate, hasNext is false.");
        }

        return result;
    }

}
