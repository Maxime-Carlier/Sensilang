package fr.unice.polytech.dsl.sensilang.model.sensor.mutators;

import java.util.Random;

public class NoiseMutator implements Mutator{

    double range;
    Random rand = new Random(System.currentTimeMillis());

    /**
     * Default constructor
     * @param range The range allowed for the value to be modified
     * Ex : if range = 0.1 and the value is 1, the final value is between 1*(1-0.1) and 1*(1+0.1)
     * which mean : 0.9 ≤ 1 ≥ 1.1
     */
    public NoiseMutator(double range) {
        this.range = range;
    }

    @Override
    public Number mutate(Number n) {
        double lower = (1 - range) * n.doubleValue();
        double upper = (1 + range) * n.doubleValue();
        return rand.nextDouble() * (upper-lower) + lower;
    }
}
