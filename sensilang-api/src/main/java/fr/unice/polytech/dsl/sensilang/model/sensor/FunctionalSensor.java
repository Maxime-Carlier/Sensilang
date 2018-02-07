package fr.unice.polytech.dsl.sensilang.model.sensor;

import java.util.function.Function;

public abstract class FunctionalSensor implements Sensor {

    @Override
    protected FunctionalSensor clone() {
        try {
            return (FunctionalSensor) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO add logger
            e.printStackTrace();
            return this;
        }
    }

    protected Function<Long, Number> function;
    private String id;

    public FunctionalSensor(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
