package fr.unice.polytech.dsl.sensilang.language;

import fr.unice.polytech.dsl.sensilang.model.sensor.AbstractSensor;
import fr.unice.polytech.dsl.sensilang.model.sensor.Sensor;
import groovy.lang.Binding;

import java.util.ArrayList;

class SensilangModel {
    private Binding binding;

    private ArrayList<AbstractSensor> sensors;

    public SensilangModel(Binding binding) {
        this.binding = binding;
        this.sensors = new ArrayList<>();
    }

    public void addSensor(AbstractSensor s) {
        this.sensors.add(s);
    }

    public void simulate(long start, long end) {
        System.out.format("%s\t | %-5s | %s\n","s", "t","v");
        for (long l = start; l < end; l++) {
            for (AbstractSensor s : sensors) {
                System.out.format("%s\t | %-5d | %d\n", s.getId(), l, s.valueAt(l));
            }
        }
    }
}
