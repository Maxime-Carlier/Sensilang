package fr.unice.polytech.dsl.sensilang.model.sensor;

/**
 * Common interface for all Sensors
 */
public interface Sensor {
    /**
     * Compute the value for the given POSIX time
     * @param timeStamp The Posix timestamp
     * @return The Sensor value
     */
    Number valueAt(long timeStamp);
}
