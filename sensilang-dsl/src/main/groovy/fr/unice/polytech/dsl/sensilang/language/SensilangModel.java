package fr.unice.polytech.dsl.sensilang.language;

import fr.unice.polytech.dsl.sensilang.main.SaveToInflux;
import fr.unice.polytech.dsl.sensilang.main.SensilangPoint;
import fr.unice.polytech.dsl.sensilang.model.sensor.AbstractSensor;
import groovy.lang.Binding;

import java.util.ArrayList;
import java.util.List;

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

    public void save(List<SensilangPoint> points) {
        SaveToInflux saver = new SaveToInflux();
        saver.save(points);
    }

    public List<SensilangPoint> compute(long start, long end, long increment) {
        long timestamp = System.currentTimeMillis() - (end-start) * 1000;
        List<SensilangPoint> points = new ArrayList<>();
        for (long l = start; l <= end; l+=increment) {
            for (AbstractSensor s : sensors) {
                if (s.getFinalValue(l) != null) {
                    points.add(new SensilangPoint(s.getId(), timestamp+l*1000, s.getFinalValue(l).doubleValue()));
                } else {
                    points.add(new SensilangPoint(s.getId(), timestamp+l*1000, -1));
                }
            }
        }
        return points;
    }

    public List<SensilangPoint> simulate(long start, long end) {
        return simulate(start, end, 1);
    }

    public List<SensilangPoint> simulate(long start, long end, long increment) {
        System.out.format("%s\t | %-5s | %s\n","s", "t","v");
        List<SensilangPoint> points = compute(start, end, increment);
        points.forEach(sensilangPoint -> {
            if (sensilangPoint.getValue().intValue() == -1) {
                System.out.format("%s\t | %-5d | N/A\n", sensilangPoint.getSensor(), sensilangPoint.getTime());
            } else {
                System.out.format("%s\t | %-5d | %f\n", sensilangPoint.getSensor(), sensilangPoint.getTime(), sensilangPoint.getValue());
            }
        });
        return points;
    }
}
