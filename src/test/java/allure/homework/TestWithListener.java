package allure.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestWithListener {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    void checkNameIssueInRepo() {

        open("https://github.com/");
        $("[placeholder=\"Search GitHub\"]").setValue("Selenide").pressEnter();
        $("[href=\"/selenide/selenide\"]").click();
        $("#issues-tab").click();
        $("#issue_2303_link").shouldHave(Condition.text("Скачивание видеофайлов из Селенойда"));
    }
}
