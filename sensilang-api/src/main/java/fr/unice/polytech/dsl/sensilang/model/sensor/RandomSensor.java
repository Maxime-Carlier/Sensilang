package fr.unice.polytech.dsl.sensilang.model.sensor;

import java.util.Random;

public class RandomSensor<T extends Number> extends FunctionalSensor {

    Random rand = new Random(System.currentTimeMillis());

    public RandomSensor(T lower, T upper, Class<T> type) {
        if(type.equals(Integer.class)) {
            function = (Long l) -> rand.nextInt((Integer)upper-(Integer)lower) + (Integer)lower;
        } else {
            throw new IllegalArgumentException("Unsuported type " + type.getName());
        }
    }

    @Override
    public Number valueAt(long timeStamp) {
        return function.apply(timeStamp);
    }
}

