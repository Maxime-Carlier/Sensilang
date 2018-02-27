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
        double x[] = {0.0, 1.0, 2.0};
        double y[] = {1.0, -1.0, 2.0};

        UnivariateInterpolator interpolator = new SplineInterpolator();
        UnivariateFunction function = interpolator.interpolate(x, y);



    }
}
