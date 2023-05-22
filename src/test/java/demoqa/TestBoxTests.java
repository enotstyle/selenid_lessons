package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TestBoxTests {

    @BeforeAll
    static void beforeAll() {
//        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
        Configuration.pageLoadStrategy = "none";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {
        open("/text-box");
        $(".main-header").shouldHave(text("Text Box"));
        $("#userName").setValue("Ivan");
        $("#userEmail").setValue("xxx@mail.ru");
        $("#currentAddress").setValue("lenina 2-23");
        $("#permanentAddress").setValue("kirova 44-33");
        $("#submit").click();
        $("#output").shouldBe(visible);
        $("#output #name").shouldHave(text("Ivan"));
        $("#output #email").shouldHave(text("xxx@mail.ru"));
        $("#output #currentAddress").shouldHave(text("lenina 2-23"));
        $("#output #permanentAddress").shouldHave(text("kirova 44-33"));
    }
}
