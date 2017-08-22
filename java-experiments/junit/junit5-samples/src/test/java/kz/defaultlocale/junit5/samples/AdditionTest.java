package kz.defaultlocale.junit5.samples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AdditionTest implements OperationContract<Integer> {

    @Override
    public Operation<Integer> createOperation() {
        return new Addition();
    }

    @Override
    public Integer createValue() {
        return 1;
    }

    @Test
    void add() {
        assertEquals(2, (int) createOperation().run(1, 1));
    }

}
