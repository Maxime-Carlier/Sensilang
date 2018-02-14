package fr.unice.polytech.dsl.sensilang.main;

public class SensilangPoint {

    private String sensor;
    private long time;
    private Number value;

    public SensilangPoint(String sensor, long time, Number vamue) {
        this.sensor = sensor;
        this.time = time;
        this.value = vamue;
    }

    public String getSensor() {
        return sensor;
    }

    public long getTime() {
        return time;
    }

    public Number getValue() {
        return value;
    }
}
