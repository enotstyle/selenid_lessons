package demoqa.components;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {

    public void modalAppears() {
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }


    public void verifyResults(String key, String value) {
        String[] labelNames = {
                "Student Name",
                "Student Email",
                "Gender",
                "Mobile",
                "Date of Birth",
                "Subjects",
                "Hobbies",
                "Picture",
                "Address",
                "State and City"};
        $(".table-responsive").$(byText(key)).sibling(0).shouldHave(text(value));


    }
}
