package fr.unice.polytech.dsl.sensilang.model.sensor;

public class RandomSensor<T extends Number> extends FunctionalSensor {

    java.util.Random rand = new java.util.Random(System.currentTimeMillis());

    public RandomSensor(T lower, T upper, Class<T> type) {
        if(type.equals(Integer.class)) {
            function = (Long l) -> rand.nextInt((Integer)upper-(Integer)lower) + (Integer)lower;
        }
    }

    @Override
    public Number valueAt(long timeStamp) {
        return function.apply(timeStamp);
    }
}

