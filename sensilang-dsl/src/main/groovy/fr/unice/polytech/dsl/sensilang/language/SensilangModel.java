package fr.unice.polytech.dsl.sensilang.language;

import dnl.utils.text.table.TextTable;
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
        List<SensilangPoint> points = compute(start, end, increment);
        List<Object[]> rows = new ArrayList<>();
        String[] headers = new String[sensors.size() + 1];
        headers[0] = "Time";
        for(int i=0;i<sensors.size();i++) {
            headers[i + 1] = sensors.get(i).getId();
        }
        points.forEach(sensilangPoint -> {
            Object[] row = new Object[sensors.size() + 1];
            row[0] = String.valueOf(sensilangPoint.getTime());
            for(int i=0; i<sensors.size();i++) {
                if (sensilangPoint.getValue().intValue() != -1) {
                    row[i + 1] = String.valueOf(sensilangPoint.getValue().doubleValue());
                } else {
                    row[i + 1] = "N/A";
                }
            }
            rows.add(row);
        });
        Object[][] data = new Object[rows.size()][];
        data = rows.toArray(data);
        TextTable tt = new TextTable(headers, data);
        tt.printTable();
        return points;
    }
}
