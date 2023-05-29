package other_test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class JUnitSimpleTest {

    @DisplayName("Демонстрационный тест")
    @Test
    void simpleTest() {
        Assertions.assertTrue(3 > 2);
    }

    @BeforeEach
    void setUp() {
        open("https://google.com");
    }

    @CsvSource({
            "Allure testops, https://qameta.io",
            "Selenide, https://selenide.org"}
    )
    @ParameterizedTest(name = "Тест {0}, на {1}")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void searchResultsCountTest(
            String productName,
            String productUrl) {
        $("[name=q]").setValue(productName).pressEnter();
        $("[id=search]").shouldHave(text(productUrl));
    }

    @CsvSource({
            "Allure testops, https://qameta.io",
            "Selenide, https://selenide.org"}
    )

    @ParameterizedTest(name = "Тест {0}, на {1}")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void searchResultsCountTest(String productName=) {
        $("[name=q]").setValue(productName).pressEnter();
        $$("[id=search]").shouldHave(text(productUrl));
    }

}
