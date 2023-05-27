package demoqa;

import org.junit.jupiter.api.Test;

import pages.PracticeFormPage;

import java.io.File;

import static pages.PracticeFormPage.Hobby;

public class PracticeFormPO extends BaseTest {

    @Test
    void successfulRegistrationTest() {
        String firstName = "Ivan";
        String lastName = "Petrov";
        String email = "IvanPetrov@mail.ru";
        String phoneNumber = "88005553535";
        String subject = "Computer Science";
        String currentAddress = "kirova 22-44";
        String state = "NCR";
        String city = "Noida";
        File testFile = new File("C:\\Users\\Terenin.Iva\\IdeaProjects\\selenid_lessons\\src\\test\\resources\\readme.txt");

        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGenderOther()
                .setUserPhoneNumber(phoneNumber)
                .setBirthDate("11", "May", "2009")
                .setSubject(subject)
                .setHobbies(Hobby.SPORTS)
                .uploadFile(testFile)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submit();
    }
}

