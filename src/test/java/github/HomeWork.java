package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomeWork {

    @Test
    void findJunitTextOnWikiPage() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(byText("Soft assertions")).click();
        $("#wiki-body").shouldHave(text("2. Using JUnit4 just describe rule inside test class:"));
    }

    @Test
    void findTextOnSolutionPage() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://github.com/");
        $(byText("Solutions")).hover();
        $(byText("Enterprise")).click();
        $(".position-relative h1").shouldHave(text("Let’s build from here"));
    }

    @Test
    void dragAndDropTest() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDropTo($("#column-b"));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

    @Test
    void dragAndDropTestWithActions() {
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        open("https://the-internet.herokuapp.com/drag_and_drop");
        actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}


