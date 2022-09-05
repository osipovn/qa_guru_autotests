package qaDemoPo.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import qaDemoPo.pages.RegistrationFormPage;
import static qaDemoPo.tests.TestData.*;


public class RegistrationFormWithPageObjectsTests {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void browser() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1280x1024";
        Configuration.browser = "chrome";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @Test
    void fillStandartTest() {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(userGender)
                .setUserNumber(userNumber)
                .setBirthDay(birthDay, birthMonth, birthYear)
                .setHobbies(hobbies)
                .markHobbies(markHobb)
                .uploadPicture(uploadPath)
                .setCurrentAddress(currentAddress)
                .setStateAndCity(state, city)
                .clickSubmit();

        registrationFormPage.checkResultTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", userGender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .checkResult("Subjects", hobbies[0] + ", " + hobbies[1] + ", " + hobbies[2] + ", " + hobbies[3])
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "uploadFile.png")
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);
    }
}
