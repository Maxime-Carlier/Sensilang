package fr.unice.polytech.dsl.sensilang.model.sensor;

import java.util.function.Function;

public abstract class FunctionalSensor extends AbstractSensor {

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

    public FunctionalSensor(String id) {
        super(id);
    }
}
