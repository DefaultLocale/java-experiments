package kz.defaultlocale.junit5.samples;

import static kz.defaultlocale.junit5.samples.Writing.write;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test class with a display name")
class DisplayNameDemo {

    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
        write("Display name sample");
    }

}
