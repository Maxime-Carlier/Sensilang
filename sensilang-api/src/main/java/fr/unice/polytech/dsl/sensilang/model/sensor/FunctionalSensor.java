package fr.unice.polytech.dsl.sensilang.model.sensor;

import java.util.function.Function;

public abstract class FunctionalSensor implements Sensor {
    protected Function<Long, Number> function;
    private final String id;

    public FunctionalSensor(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
