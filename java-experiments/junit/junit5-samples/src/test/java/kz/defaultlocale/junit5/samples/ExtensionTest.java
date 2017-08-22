package kz.defaultlocale.junit5.samples;

import static kz.defaultlocale.junit5.samples.Writing.write;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CustomExtension.class)
public class ExtensionTest {

    @Test
    void first(int a) {

    }

    @Test
    void another(String resolved) {
        write("Resolved parameter: "+resolved);
    }
    
    @Test
    void extra() {
        
    }
}
