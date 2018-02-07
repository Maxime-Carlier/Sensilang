package fr.unice.polytech.dsl.sensilang.model.sensor;

import java.util.function.Function;

public class FunctionSensor<T extends Number> extends FunctionalSensor {

    private Function<Long, Number> law;

    public FunctionSensor(String id) {
        super(id);
        this.law = (x) -> 0;
    }

    public void setLaw(Function<Long, Number> law) {
        this.law = law;
    }

    @Override
    public Number valueAt(long timeStamp) {
        return this.law.apply(timeStamp);
    }
}

