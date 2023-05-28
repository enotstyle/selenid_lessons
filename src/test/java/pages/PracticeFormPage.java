package pages;

import demoqa.components.Calendar;
import demoqa.components.RegistrationResultsModal;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PracticeFormPage {

    private final static String titleText = "Student Registration Form";
    RegistrationResultsModal resultsModal = new RegistrationResultsModal();
    Calendar calendar = new Calendar();

    public PracticeFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(titleText));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public PracticeFormPage setFirstName(String firstName) {
        $("#firstName").setValue(firstName);
        return this;
    }

    public PracticeFormPage setLastName(String lastName) {
        $("#lastName").setValue(lastName);
        return this;
    }

    public PracticeFormPage setUserEmail(String userEmail) {
        $("#userEmail").setValue(userEmail);
        return this;
    }

    public PracticeFormPage setGenderOther() {
        $("[for=gender-radio-3]").click();
        return this;
    }

    public PracticeFormPage setUserPhoneNumber(String phoneNumber) {
        $("#userNumber").setValue(phoneNumber);
        return this;
    }

    public PracticeFormPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);
        return this;
    }

    public PracticeFormPage setSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    public PracticeFormPage setHobbies(Hobby Hobby) {
        $(byText(Hobby.getTittle())).click();
        return this;
    }

    public PracticeFormPage uploadFile(File file) {
        $("#uploadPicture").uploadFile(file);
        return this;
    }

    public PracticeFormPage setCurrentAddress(String address) {
        $("#currentAddress").setValue(address);
        return this;
    }

    public PracticeFormPage setState(String state) {
        $("#react-select-3-input").setValue(state).pressEnter();
        return this;
    }

    public PracticeFormPage setCity(String city) {
        $("#react-select-4-input").setValue(city).pressEnter();
        return this;
    }

    public PracticeFormPage submit() {
        $("#submit").click();
        return this;
    }

    public PracticeFormPage resultModalAppears() {
        resultsModal.modalAppears();
        return this;
    }

    public PracticeFormPage verifyResultModal(String key, String value) {
        resultsModal.verifyResults(key, value);
        return this;
    }

    public static enum Hobby {
        SPORTS("Sports"),
        READING("Reading"),
        MUSIC("Music");

        private final String tittle;

        Hobby(String tittle) {
            this.tittle = tittle;
        }

        public String getTittle() {
            return tittle;
        }
    }
}

