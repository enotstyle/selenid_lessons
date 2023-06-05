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
import static org.openqa.selenium.By.linkText;

@DisplayName("Тестовый набор с аннотацией")
@Feature("Поиск")
public class TestWithAnnotation {

    private static String LINK = "https://github.com/";
    private static String NAME = "Selenide";
    private static String NAME_OF_ISSUE = "Скачивание видеофайлов из Селенойда";

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
    @DisplayName("Проверка названия issue")
    void checkNameIssueInRepo() {
        Steps step = new Steps();
        step.openPage(LINK);
        step.enterSearch(NAME);
        step.clickOnLink();
        step.goToIssueTab();
        step.checkNameIssue(NAME_OF_ISSUE);
    }
}

class Steps {
    @Step("Открываем страницу {link}")
    public void openPage(String link) {
        open(link);
    }

    @Step("Ищем {name} и нажимаем Enter")
    public void enterSearch(String name) {
        $("[placeholder=\"Search GitHub\"]").setValue("Selenide").pressEnter();
    }

    @Step("Кликаем по ссылке")
    public void clickOnLink() {
        $("[href=\"/selenide/selenide\"]").click();
    }

    @Step("Переходим в Issue таб")
    public void goToIssueTab() {
        $("#issues-tab").click();
    }

    @Step("Првоеряем что есть issue с названим {name}")
    public void checkNameIssue(String name) {
        $("#issue_2303_link").shouldHave(Condition.text(name));
    }
}