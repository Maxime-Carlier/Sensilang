package fr.unice.polytech.dsl.sensilang.language;

import dnl.utils.text.table.TextTable;
import fr.unice.polytech.dsl.sensilang.main.SaveToInflux;
import fr.unice.polytech.dsl.sensilang.main.SensilangPoint;
import fr.unice.polytech.dsl.sensilang.model.markov.MarkovSensor;
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

    public MarkovSensor getMarkovSensor(String id) {
        for (AbstractSensor s : this.sensors) {
            if (s.getId().equals(id)){
                try{
                    return (MarkovSensor) s;
                }
                catch (ClassCastException e) {
                    System.err.println("Wrong sensor ID. Doens't lead to a Markovian sensor.");
                }
            }
        }

        return null;
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

    public void simulate(long start, long end) {
        simulate(start, end, 1);
    }

    public void simulate(long start, long end, long increment) {
        List<Object[]> rows = new ArrayList<>();
        String[] headers = new String[sensors.size() + 1];
        headers[0] = "Time";
        for(int i=0;i<sensors.size();i++) {
            headers[i + 1] = sensors.get(i).getId();
        }

        for (long l = start; l <= end; l+=increment) {
            Object[] row = new Object[sensors.size() + 1];
            row[0] = String.valueOf(l);
            for(int i=0; i<sensors.size();i++) {
                if (sensors.get(i).getFinalValue(l) != null) {
                    row[i + 1] = String.valueOf(sensors.get(i).getFinalValue(l).doubleValue());
                } else {
                    row[i + 1] = "N/A";
                }
            }
            rows.add(row);
        }
        Object[][] data = new Object[rows.size()][];
        data = rows.toArray(data);
        TextTable tt = new TextTable(headers, data);
        tt.printTable();
    }
}
