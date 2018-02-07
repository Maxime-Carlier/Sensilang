package fr.unice.polytech.dsl.sensilang.main;

import fr.unice.polytech.dsl.sensilang.model.sensor.FunctionalSensor;
import fr.unice.polytech.dsl.sensilang.model.sensor.RandomSensor;
import fr.unice.polytech.dsl.sensilang.model.sensor.Sensor;

public class Main {
    public static void main(String[] args) {
        Sensor s = new RandomSensor<Integer>("toto", 0, 10, Integer.class);
        for(int i=0;i<10;i++) {
            System.out.println(s.valueAt(i));
        }

        Sensor s1 = new RandomSensor<>("yolo", 10f, 20f, Float.class);
        for(int i=0;i<10;i++) {
            System.out.println(s1.valueAt(i));
        }
    }
}
