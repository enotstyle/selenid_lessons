package other_test;

import org.junit.jupiter.api.*;

public class FirstJunitTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("   Это метод beforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("   Это метод afterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("    Это метод beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("    Это метод afterEach");
    }
    @Test
    void firstTest() {
        System.out.println("    Это firstTest");
        Assertions.assertTrue(3>2);
    }

    @Test
    void secondTest() {
        System.out.println("    Это secondTest");
        Assertions.assertTrue(3>2);
    }
}
