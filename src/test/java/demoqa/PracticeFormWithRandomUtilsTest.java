package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import java.io.File;

import static demoqa.utils.RandomUtils.getRandomEmail;
import static demoqa.utils.RandomUtils.randomString;

public class PracticeFormWithRandomUtilsTest extends BaseTest {


    @Test
    void successfulRegistrationTest() {

        String firstName = randomString(8);
        String lastName = randomString(8);
        String email = getRandomEmail();
        String phoneNumber = "1234567890";
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
                .setHobbies(PracticeFormPage.Hobby.SPORTS)
                .uploadFile(testFile)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submit()
                .resultModalAppears()
                .verifyResultModal("Student Name", firstName + " " + lastName);

        //TODO сделать верефикацию результатов в одной функцией
    }
}

