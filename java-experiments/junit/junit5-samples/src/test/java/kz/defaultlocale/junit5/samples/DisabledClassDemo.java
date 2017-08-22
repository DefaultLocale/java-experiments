package kz.defaultlocale.junit5.samples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class DisabledClassDemo {

    @Test
    void someTest() {
        assertEquals(2, 2);
    }
}
