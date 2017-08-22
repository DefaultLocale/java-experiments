package kz.defaultlocale.junit5.samples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class SampleTest {

    @Test
    void testAdd() {
        Sample sample = new Sample();
        assertEquals(5, sample.add(2, 3), "Sum doesn't match");
    }

}
