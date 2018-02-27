package fr.unice.polytech.dsl.sensilang.model.sensor;

import org.apache.commons.math3.analysis.UnivariateFunction;

public class InterpSensor extends AbstractSensor{

    private UnivariateFunction univariateFunction;

    public InterpSensor(String id, UnivariateFunction univariateFunction) {
        super(id);
        this.univariateFunction = univariateFunction;
    }

    @Override
    protected Number valueAt(long timeStamp) {
        return univariateFunction.value(timeStamp);
    }
}
