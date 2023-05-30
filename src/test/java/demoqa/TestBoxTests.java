package demoqa;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBoxTests extends BaseTest {

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
