package fr.unice.polytech.dsl.sensilang.language;

import fr.unice.polytech.dsl.sensilang.model.sensor.AbstractSensor;
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
        this.binding.setVariable(s.getId(), s);
    }

    public void simulate(long start, long end) {
        System.out.format("%s\t | %-5s | %s\n","s", "t","v");
        for (long l = start; l <= end; l++) {
            for (AbstractSensor s : sensors) {
                if (s.getFinalValue(l) != null) {
                    System.out.format("%s\t | %-5d | %f\n", s.getId(), l, s.getFinalValue(l).doubleValue());
                } else {
                    System.out.format("%s\t | %-5d | N/A\n", s.getId(), l);
                }
            }
        }
    }

    public void simulate(long start, long end, long increment) {
        System.out.format("%s\t | %-5s | %s\n","s", "t","v");
        for (long l = start; l <= end; l+=increment) {
            for (AbstractSensor s : sensors) {
                if (s.getFinalValue(l) != null) {
                    System.out.format("%s\t | %-5d | %f\n", s.getId(), l, s.getFinalValue(l).doubleValue());
                } else {
                    System.out.format("%s\t | %-5d | N/A\n", s.getId(), l);
                }
            }
        }
    }
}
