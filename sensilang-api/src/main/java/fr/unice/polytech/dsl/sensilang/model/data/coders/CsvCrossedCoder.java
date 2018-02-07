package fr.unice.polytech.dsl.sensilang.model.data.coders;

import fr.unice.polytech.dsl.sensilang.model.data.adapters.FileAdapter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Coder for (small) CSV file with can be stored safely in memory and
 * where data is stored as a crossed array
 *
 *  time | sensor_1 | sensor_2 | sensor_3
 *     1 |      100 |      25  |      10
 *    10 |      102 |       8  |     255
 */
public class CsvCrossedCoder<T extends Number> implements Coder {

    Map<Long, CSVRecord> cache;

    int columnValueIndex;

    public CsvCrossedCoder(FileAdapter adapter, int columnValueIndex) {
        try {
            File f = adapter.get();
            Reader in = new FileReader(f);
            cache = new HashMap<>();
            this.columnValueIndex = columnValueIndex;

            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreSurroundingSpaces().parse(in);
            for (CSVRecord r : records) {
                String timeStampStr = r.get(0);
                long timeStamp = Long.parseLong(timeStampStr);
                cache.put(timeStamp, r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Short getValueAsShort(long timeStamp) {
        CSVRecord tuple = cache.get(timeStamp);
        if (tuple != null) {
            try {
                String shortStr = tuple.get(columnValueIndex);
                return Short.parseShort(shortStr);
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Integer getValueAsInt(long timeStamp) {
        CSVRecord tuple = cache.get(timeStamp);
        if (tuple != null) {
            try {
                String intStr = tuple.get(columnValueIndex);
                return Integer.parseInt(intStr);
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Long getValueAsLong(long timeStamp) {
        CSVRecord tuple = cache.get(timeStamp);
        if (tuple != null) {
            try {
                String longStr = tuple.get(columnValueIndex);
                return Long.parseLong(longStr);
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Float getValueAsFloat(long timeStamp) {
        CSVRecord tuple = cache.get(timeStamp);
        if (tuple != null) {
            try {
                String floatStr = tuple.get(columnValueIndex);
                return Float.parseFloat(floatStr);
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Double getValueAsDoule(long timeStamp) {
        CSVRecord tuple = cache.get(timeStamp);
        if (tuple != null) {
            try {
                String doubleStr = tuple.get(columnValueIndex);
                return Double.parseDouble(doubleStr);
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        return null;
    }
}
