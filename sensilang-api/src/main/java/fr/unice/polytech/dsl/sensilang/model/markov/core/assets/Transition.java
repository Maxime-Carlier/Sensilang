package fr.unice.polytech.dsl.sensilang.model.markov.core.assets;

public class Transition {

    private int init;
    private int fin;
    private final double probability;

    public Transition(int init, int fin, double probability) {
        this.init = init;
        this.fin = fin;
        this.probability = probability;
    }

    public int getInit() {
        return init;
    }

    public int getFin() {
        return fin;
    }

    public double getProbability() {
        return probability;
    }
}
