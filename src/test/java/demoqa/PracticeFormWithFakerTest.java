package demoqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;

import java.io.File;
import java.util.Locale;

import static demoqa.utils.RandomUtils.getRandomEmail;
import static demoqa.utils.RandomUtils.randomString;

public class PracticeFormWithFakerTest extends BaseTest {


    @Test
    void successfulRegistrationTest() {

        String firstName = Faker.instance().name().firstName();
        String lastName = Faker.instance(new Locale("ru")).name().lastName();
        String email = Faker.instance().internet().emailAddress();
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

