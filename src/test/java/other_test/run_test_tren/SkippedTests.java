package other_test.run_test_tren;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Simple")
public class SkippedTests {
    @Test
    @Disabled
    void test1() {
        assertTrue(true);
    }

    @Test
    @Disabled("Some reason")
    void test2() {
        assertTrue(true);
    }

}
