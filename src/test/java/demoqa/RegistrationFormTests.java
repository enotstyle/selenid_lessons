package demoqa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import demoqa.pages.RegistrationFormPage;

import java.io.File;
import java.util.stream.Stream;

import static demoqa.pages.RegistrationFormPage.Hobby;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTests extends BaseTest {

    static Stream<Arguments> selenideLocaleDataProvider() {
        return Stream.of(
                Arguments.of(Hobby.MUSIC, "Ivan"),
                Arguments.of(Hobby.SPORTS, "Lera"),
                Arguments.of(Hobby.READING, "Zaur")
        );
    }

    @Tag("Jenkins")
    @ParameterizedTest(name = "Register {1} with hobby {0}")
    @MethodSource("selenideLocaleDataProvider")
    void successfulRegistrationTest(Hobby hobby, String name) {
        String firstName = name;
        String lastName = Faker.instance().name().lastName();
        String email = Faker.instance().internet().emailAddress();
        String phoneNumber = Faker.instance().numerify("##########");
        String subject = "Computer Science";
        String currentAddress = "kirova 22-44";
        String state = "NCR";
        String city = "Noida";
        File testFile = new File("src/test/resources/readme.txt");

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();

        step("open page", () -> {
            registrationFormPage.openPage();
        });

        step("fill form", () -> {
            registrationFormPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setUserEmail(email)
                    .setGenderOther()
                    .setUserPhoneNumber(phoneNumber)
                    .setBirthDate("11", "May", "2009")
                    .setSubject(subject)
                    .setHobbies(hobby)
                    .uploadFile(testFile)
                    .setCurrentAddress(currentAddress)
                    .setState(state)
                    .setCity(city)
                    .submit();
        });

        step("verify result", () -> {
            registrationFormPage.resultModalAppears()
                    .verifyResultModal("Student Name", firstName + " " + lastName);
        });
    }
}

