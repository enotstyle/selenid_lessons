package demoqa.pages;

import demoqa.components.Calendar;
import demoqa.components.RegistrationResultsModal;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {


    private final static String titleText = "Student Registration Form";
    RegistrationResultsModal resultsModal = new RegistrationResultsModal();
    Calendar calendar = new Calendar();

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(titleText));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationFormPage setFirstName(String firstName) {
        $("#firstName").setValue(firstName);
        return this;
    }

    public RegistrationFormPage setLastName(String lastName) {
        $("#lastName").setValue(lastName);
        return this;
    }

    public RegistrationFormPage setUserEmail(String userEmail) {
        $("#userEmail").setValue(userEmail);
        return this;
    }

    public RegistrationFormPage setGenderOther() {
        $("[for=gender-radio-3]").click();
        return this;
    }

    public RegistrationFormPage setUserPhoneNumber(String phoneNumber) {
        $("#userNumber").setValue(phoneNumber);
        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationFormPage setSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();
        return this;
    }

    public RegistrationFormPage setHobbies(Hobby Hobby) {
        $(byText(Hobby.getTittle())).click();
        return this;
    }

    public RegistrationFormPage uploadFile(File file) {
        $("#uploadPicture").uploadFile(file);
        return this;
    }

    public RegistrationFormPage setCurrentAddress(String address) {
        $("#currentAddress").setValue(address);
        return this;
    }

    public RegistrationFormPage setState(String state) {
        $("#react-select-3-input").setValue(state).pressEnter();
        return this;
    }

    public RegistrationFormPage setCity(String city) {
        $("#react-select-4-input").setValue(city).pressEnter();
        return this;
    }

    public RegistrationFormPage submit() {
        $("#submit").click();
        return this;
    }

    public RegistrationFormPage resultModalAppears() {
        resultsModal.modalAppears();
        return this;
    }

    public RegistrationFormPage verifyResultModal(String key, String value) {
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

