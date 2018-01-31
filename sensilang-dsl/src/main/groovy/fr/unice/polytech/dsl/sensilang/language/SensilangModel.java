package fr.unice.polytech.dsl.sensilang.language;

import fr.unice.polytech.dsl.sensilang.model.sensor.Sensor;
import groovy.lang.Binding;

import java.util.ArrayList;

class SensilangModel {
    private Binding binding;

    private ArrayList<Sensor> sensors;

    public SensilangModel(Binding binding) {
        this.binding = binding;
        this.sensors = new ArrayList<>();
    }

    public void addSensor(Sensor s) {
        this.sensors.add(s);
    }

    public void simulate(long start, long end) {
        System.out.format("%-5s | %s\n","t","v");
        for (long l = start; l < end; l++) {
            for (Sensor s : sensors) {
                System.out.format("%-5d | %d\n", l, s.valueAt(l));
            }
        }
    }
}
