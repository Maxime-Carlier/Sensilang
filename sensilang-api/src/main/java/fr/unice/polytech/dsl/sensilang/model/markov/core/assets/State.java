package fr.unice.polytech.dsl.sensilang.model.markov.core.assets;

public class State {

    private String state;
    private int index;
    private double value;

    public State(int index, String state, double value) {
        this.state = state;
        this.index = index;
        this.value = value;
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
