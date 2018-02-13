package fr.unice.polytech.dsl.sensilang.model.sensor.mutators;

import com.sun.istack.internal.Nullable;

public interface Mutator {
    Number mutate(@Nullable Number n);
}
