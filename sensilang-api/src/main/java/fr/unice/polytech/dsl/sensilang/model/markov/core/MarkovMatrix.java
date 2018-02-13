package fr.unice.polytech.dsl.sensilang.model.markov.core;

import fr.unice.polytech.dsl.sensilang.model.markov.core.assets.Transition;

import java.util.Arrays;
import java.util.logging.Level;

public class MarkovMatrix {

    private double[][] matrix;
    private int size;

    public MarkovMatrix(int size) {
        this.matrix = new double[size][size];
        // TODO: test if filled with zeros, technically it is
        this.size = size;
    }

    public MarkovMatrix(Number[][] matrix) {
        this.setMatrix(matrix);
        this.size = matrix.length;
    }

    public void init() {
        MarkovChain.logger.log(Level.INFO, "Initiating matrix...");
        this.fillLoopState();
    }

    public void addTransition(Transition t) {
        this.matrix[t.getInit()][t.getFin()] = t.getProbability();
    }

    // TODO: pas de validation en l'état, osef?
    // Filling in loop states in order to make sure that the matrix is "Markovian"
    private void fillLoopState() {
        double sum = 0;
        double[] line;
        for (int i = 0; i < size; i++) {
            line = this.matrix[i];
            sum = Arrays.stream(line).sum();
            if (sum < 1) {
                // TODO: there is an approximation here but that does look fine (i.e [0.8, 0.19999999999999996])
                this.matrix[i][i] = 1-sum;
            }
        }
    }

    public double[] getLine(int index) {
        return this.matrix[index];
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return size;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public void setMatrix(Number[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                this.matrix[i][j] = matrix[i][j].doubleValue();
    }

    public void setSize(int size) {
        this.size = size;
    }

}
