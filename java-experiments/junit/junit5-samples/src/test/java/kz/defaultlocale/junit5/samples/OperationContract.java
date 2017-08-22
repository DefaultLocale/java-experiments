package kz.defaultlocale.junit5.samples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public interface OperationContract<T> {

    Operation<T> createOperation();

    T createValue();

    @Test
    default void failsOnNull() {
        Operation<T> createOperation = createOperation();
        Assertions.assertThrows(IllegalArgumentException.class, () -> createOperation.run(null, createValue()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> createOperation.run(createValue(), null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> createOperation.run(null, null));
    }

}
