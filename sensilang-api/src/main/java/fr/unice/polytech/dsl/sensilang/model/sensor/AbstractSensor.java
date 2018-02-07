package fr.unice.polytech.dsl.sensilang.model.sensor;

public abstract class AbstractSensor implements Sensor {
    private String id;

    public AbstractSensor(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
