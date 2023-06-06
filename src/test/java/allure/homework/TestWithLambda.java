package allure.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Тестовый набор с лямбдой")
@Feature("Поиск")
public class TestWithLambda {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Feature("Поиск репозитория") //как называется фичи
    @Story("Успешный поиск Selenide") //как пользователь ведет себя при созданни фичи
    @Owner("Иван")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com") //ссылка

    void checkNameIssueInRepo() {
        step("Открываем старинцу https://github.com/",
                () -> open("https://github.com/"));
        step("Вводим \"Selenide\" и нажимаем Enter",
                () -> $("[placeholder=\"Search GitHub\"]").setValue("Selenide").pressEnter());
        step("Кликаем по ссылке \"/selenide/selenide\"",
                () -> $("[href=\"/selenide/selenide\"]").click());
        step("Переходим в issue таб",
                () -> $("#issues-tab").click());
        step("Проверяем что есть issue с текстом \"Скачивание видеофайлов из Селенойда\"",
                () -> $("#issue_2303_link").shouldHave(Condition.text("Скачивание видеофайлов из Селенойда")));
    }
}
