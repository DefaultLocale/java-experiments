package kz.defaultlocale.junit5.samples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ProcessorTest {

    private Processor processor;

    @BeforeEach
    void create() {
        processor = new Processor();
    }
    
    @Test
    void beforeProcess() {
        assertNull(processor.getResult());
    }
    
    @Nested
    class AfterProcess {
        @BeforeEach
        void process() {
            processor.process(5);
        }
        
        @Test
        void failOnSecondRun() {
            assertThrows(IllegalStateException.class, () -> processor.process(1));
        }
        
        @Test
        void result() {
            assertEquals(15, (int) processor.getResult());
        }
       
    }

}
