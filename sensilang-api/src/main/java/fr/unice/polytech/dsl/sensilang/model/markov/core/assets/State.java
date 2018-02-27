package fr.unice.polytech.dsl.sensilang.model.markov.core.assets;

import java.math.BigDecimal;

public class State {

    private String state;
    private int index;
    private double value;

    public State(int index, String state, double value) {
        this.state = state;
        this.index = index;
        this.value = value;
    }

    //TODO clean before handing-in
    public State(int index, String state) {
        this.state = state;
        this.index = index;
        this.value = 1.1;
    }

    public State(int index, String state, BigDecimal value) {
        this.state = state;
        this.index = index;
        this.value = value.doubleValue();
    }

    public String getState() {
        return state;
    }

    public int getIndex() {
        return index;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "State{" +
                "state='" + state + '\'' +
                ", index=" + index +
                ", value=" + value +
                '}';
    }

}
