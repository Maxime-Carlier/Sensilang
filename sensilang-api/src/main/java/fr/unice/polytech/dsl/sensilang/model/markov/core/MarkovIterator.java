package fr.unice.polytech.dsl.sensilang.model.markov.core;

import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.Transition;

import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;

public class MarkovIterator implements Iterator<Integer> {

    private final Random rand;
    private boolean hasNext;
    private int next;
    private MarkovMatrix matrix;

    public MarkovIterator(MarkovMatrix matrix){
        this.rand = new Random();
        this.matrix = matrix;
        this.hasNext = false;
    }

    public MarkovIterator(int size) {
        this.rand = new Random();
        this.matrix = new MarkovMatrix(size);
        this.hasNext = false;
    }

    /* CREATION AND MANIPULATION */

    public void start() {
        MarkovChain.logger.log(Level.INFO, "Starting iterator...");
        this.matrix.init();
        int size = this.matrix.getSize();
        // Randomizing first state
        this.next = this.rand.nextInt(size);
        this.hasNext = true;
    }

    public void addTransition(Transition t) {
        this.matrix.addTransition(t);
    }

    /* BEHAVIOR */
    /* https://stackoverflow.com/questions/9330394/how-to-pick-an-item-by-its-probability */
    private void findNext()
    {
        double random = rand.nextDouble();
        double[] line = this.matrix.getLine(this.next);
        double cumulativeProbability = 0.0;
        double tmp;
        for (int i = 0; i < line.length; i++){
            tmp = line[i];
            cumulativeProbability += tmp;
            if (random <= cumulativeProbability) {
                this.next = i;
                break;
            }
        }
    }

    /* OPERATIONS */
    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public Integer next() {
        int n = this.next;
        this.findNext();
        return n;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Removal is just not right in our case, on a Markov model.");
    }

    /* GETTERS AND SETTERS */

    public MarkovMatrix getMatrix() {
        return matrix;
    }
}
