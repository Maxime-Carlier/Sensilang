package fr.unice.polytech.dsl.sensilang.model.data.coders;

/**
 * TODO
 *
 * Coder for (small) CSV file with can be stored safely in memory and
 * where data is stored as an aggregated array
 *
 *  time | sensor_name | value
 *     1 |    sensor_1 |   100
 *     1 |    sensor_2 |    25
 *     1 |    sensor_3 |    10
 *    10 |    sensor_1 |   102
 *    10 |    sensor_2 |     8
 *    10 |    sensor_3 |   255
 */

public class CsvAggregatedCoder implements Coder {
    @Override
    public Short getValueAsShort(long timeStamp) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Integer getValueAsInt(long timeStamp) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Long getValueAsLong(long timeStamp) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Float getValueAsFloat(long timeStamp) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Double getValueAsDoule(long timeStamp) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
