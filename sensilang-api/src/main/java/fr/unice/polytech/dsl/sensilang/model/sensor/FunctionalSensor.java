package fr.unice.polytech.dsl.sensilang.model.sensor;

import java.util.function.Function;

public abstract class FunctionalSensor implements Sensor {
    protected Function<Long, Number> function;
}
