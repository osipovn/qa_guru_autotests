package qaDemoPo.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qaDemoPo.pages.RegistrationFormPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationFormWithPageObjectsTests {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void browser(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1280x1024";
        Configuration.browser = "chrome";
    }

    @Test
    void fillStandartTest(){
        registrationFormPage.openPage()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setUserEmail("ivanov@mail.ru")
                .setGender("Male")
                .setUserNumber("9822292034")
                .setBirthDay("15","June","1991")
                .setHobbies("Arts")
                .setHobbies("Math")
                .setHobbies("Computer Science")
                .setHobbies("Commerce")
                .markHobbies(1)
                .markHobbies(3)
                .uploadPicture("src/test/resources/uploadFile.png")
                .setCurrentAddress("Moscow city, Lubyanka str., b. 1")
                .setStateAndCity("NCR", "Delhi")
                .clickSubmit();

        registrationFormPage.checkResultTableVisible()
                .checkResult("Student Name", "Ivan Ivanov")
                .checkResult("Student Email", "ivanov@mail.ru")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9822292034")
                .checkResult("Date of Birth", "15 June,1991")
                .checkResult("Subjects", "Arts, Maths, Computer Science, Commerce")
                .checkResult("Hobbies", "Sports, Music")
                .checkResult("Picture", "uploadFile.png")
                .checkResult("Address", "Moscow city, Lubyanka str., b. 1")
                .checkResult("State and City", "NCR Delhi");
    }
}
