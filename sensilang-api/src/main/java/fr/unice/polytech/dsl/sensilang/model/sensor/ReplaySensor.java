package fr.unice.polytech.dsl.sensilang.model.sensor;

import fr.unice.polytech.dsl.sensilang.model.data.adapters.FileAdapter;
import fr.unice.polytech.dsl.sensilang.model.data.adapters.FileSystemDataAdpter;
import fr.unice.polytech.dsl.sensilang.model.data.coders.Coder;
import fr.unice.polytech.dsl.sensilang.model.data.coders.CsvCrossedCoder;

public class ReplaySensor<T extends Number> extends AbstractSensor {

    private FileAdapter     fileAdapter;
    private Coder           coder;
    private Class<T>        type;

    public ReplaySensor(String id, Class<T> type) {
        super(id);
        this.type = type;
    }

    public ReplaySensor withLocalFile(String pathToFile) {
        this.fileAdapter = new FileSystemDataAdpter(pathToFile);
        return this;
    }

    public ReplaySensor asCrossedCsv(int columnIndex) {
        coder = new CsvCrossedCoder(fileAdapter, columnIndex);
        return this;
    }

    @Override
    public Number valueAt(long timeStamp) {
        if (type.equals(Short.class)) {
            return coder.getValueAsShort(timeStamp);
        } else if (type.equals(Integer.class)) {
            return coder.getValueAsInt(timeStamp);
        } else if (type.equals(Long.class)) {
            return coder.getValueAsLong(timeStamp);
        } else if (type.equals(Float.class)) {
            return coder.getValueAsFloat(timeStamp);
        } else if (type.equals(Double.class)) {
            return coder.getValueAsFloat(timeStamp);
        } else {
            throw new IllegalStateException("Tried to get value as: " + type.getSimpleName() + " but wasn't a subset of Number");
        }
    }
}
