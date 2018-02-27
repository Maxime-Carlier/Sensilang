package fr.unice.polytech.dsl.sensilang.main;

import fr.unice.polytech.dsl.sensilang.model.sensor.*;
import fr.unice.polytech.dsl.sensilang.model.sensor.mutators.Mutator;
import fr.unice.polytech.dsl.sensilang.model.sensor.mutators.NoiseMutator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;

public class Main {
    public static void main(String[] args) {
        AbstractSensor s1 = new ReplaySensor<>("replay_sensor1", Integer.class)
                .withLocalFile("sensilang-api/src/main/resources/Bike1.csv")
                .asCrossedCsv(10);

        Mutator noiseMutator = new NoiseMutator(0.1);

        s1.setMutator(noiseMutator);

        for(int i=0; i<=1000; i+=100) {
            System.out.println(s1.getFinalValue(i));
        }
    }
}
