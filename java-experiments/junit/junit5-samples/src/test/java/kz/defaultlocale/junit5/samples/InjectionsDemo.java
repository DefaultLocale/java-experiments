package kz.defaultlocale.junit5.samples;

import static kz.defaultlocale.junit5.samples.Writing.write;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CustomParameterResolver.class)
public class InjectionsDemo {

    @BeforeEach
    void init(TestInfo info) {
        write("Injected display name: " + info.getDisplayName());
        write("Injected tags: " + info.getTags());
        write("Injected class: " + info.getTestClass().get());
        write("Injected method: " + info.getTestMethod().get());
    }

    @Test
    @DisplayName("Custom display name")
    @Tags({
        @Tag("Sample"),
        @Tag("Another tag")
    })
    void sample() {
        assertTrue(true);
    }
    
    @Test
    void acceptsReporter(TestReporter reporter) {
        reporter.publishEntry("Custom key", "Custom value");
    }
    
    @Test
    void acceptsCustomInjection(Sample sample) {
        assertEquals("injection", sample.getName());
    }

}
