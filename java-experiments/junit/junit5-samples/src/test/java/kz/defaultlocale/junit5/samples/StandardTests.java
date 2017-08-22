package kz.defaultlocale.junit5.samples;

import static kz.defaultlocale.junit5.samples.Writing.write;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StandardTests {

    @BeforeAll
    static void beforeAll() {
        write("Before all");
    }

    @BeforeEach
    void beforeEach() {
        write("Before each");
    }

    @Test
    void succeedingTest() {
        write("Successful test");
    }

    @Test
    void failingTest() {
        write("Failing test");
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        write("Skipped test. This line should not appear in the output");
    }

    @AfterEach
    void afterEach() {
        write("After each");
    }

    @AfterAll
    static void afterAll() {
        write("After all");
    }

}
