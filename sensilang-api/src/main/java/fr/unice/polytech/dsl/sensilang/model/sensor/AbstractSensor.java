package fr.unice.polytech.dsl.sensilang.model.sensor;

import fr.unice.polytech.dsl.sensilang.model.sensor.mutators.Mutator;

public abstract class AbstractSensor {
    private String id;
    private Mutator mutator;

    public AbstractSensor(String id) {
        this.id = id;
    }

    public Number getFinalValue(long timeStamp) {
        if (mutator != null) {
            return mutator.mutate(valueAt(timeStamp));
        } else {
            return valueAt(timeStamp);
        }
    }

    protected abstract Number valueAt(long timeStamp);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMutator(Mutator mutator) {
        this.mutator = mutator;
    }
}
