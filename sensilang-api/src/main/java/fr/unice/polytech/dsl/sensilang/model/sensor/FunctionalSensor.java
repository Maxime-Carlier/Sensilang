package fr.unice.polytech.dsl.sensilang.model.sensor;

import java.util.function.Function;

public abstract class FunctionalSensor extends AbstractSensor {

    protected Function<Long, Number> function;

    public FunctionalSensor(String id) {
        super(id);
    }
}
