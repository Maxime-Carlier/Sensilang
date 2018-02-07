package fr.unice.polytech.dsl.sensilang.main;

import fr.unice.polytech.dsl.sensilang.model.sensor.FunctionalSensor;
import fr.unice.polytech.dsl.sensilang.model.sensor.RandomSensor;
import fr.unice.polytech.dsl.sensilang.model.sensor.ReplaySensor;
import fr.unice.polytech.dsl.sensilang.model.sensor.Sensor;

public class Main {
    public static void main(String[] args) {
        Sensor s1 = new ReplaySensor<>("replay_sensor1", Integer.class)
                .withLocalFile("sensilang-api/src/main/resources/Bike1.csv")
                .asCrossedCsv(10);

        for(int i=0; i<=1000; i+=100) {
            System.out.println(s1.valueAt(i));
        }
    }
}
