package allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LabelsTest {

    //лучше этот
    @Test
    @Feature("Issue в репозитории") //как называется фичи
    @Story("Создание Issue") //как пользователь ведет себя при созданни фичи
    @Owner("Иван") //человек который написал тест
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "Testing", url = "https://testing.github.com") //ссылка
    @DisplayName("Создаине Issue для авторизиованного пользака")
    public void testStaticLabels() {

    }


    @Test
    public void testDynamicsLabels() {
        Allure.getLifecycle().updateTestCase(t -> t.setName("Создаине Issue для авторизиованного пользака"));
        Allure.feature("Issue в репозитории");
        Allure.story("Создание Issue");
        Allure.label("owner", "Иван");
        Allure.label("severity", SeverityLevel.BLOCKER.value());
        Allure.link("Testing", "https://testing.github.com");
    }
}
