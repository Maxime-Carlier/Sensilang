package fr.unice.polytech.dsl.sensilang.model.data.coders;

public interface Coder {
    Short       getValueAsShort(long timeStamp);
    Integer     getValueAsInt(long timeStamp);
    Long        getValueAsLong(long timeStamp);
    Float       getValueAsFloat(long timeStamp);
    Double      getValueAsDoule(long timeStamp);
}
