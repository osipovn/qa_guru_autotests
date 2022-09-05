package qaDemoPo.tests;

import baseConfig.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qaDemoPo.pages.RegistrationFormPage;

import static io.qameta.allure.Allure.step;
import static qaDemoPo.tests.TestData.*;

@DisplayName("Тест формы demoQa, используя PageObject")
public class RegistrationFormWithPageObjectsTests extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void browser() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    @DisplayName("Проверка заполнения формы регистрации")
    void fillStandartTest() {
        step("Открытие страницы с формой регистрации", () -> {registrationFormPage.openPage();});

        step("Заполнение формы регистрации", () -> {registrationFormPage.setFirstName(firstName)
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
                .clickSubmit();});

        step("Проверка корректности заполнения формы регистрации", () -> {registrationFormPage.checkResultTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", userGender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
                .checkResult("Subjects", hobbies[0] + ", " + hobbies[1] + ", " + hobbies[2] + ", " + hobbies[3])
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "uploadFile.png")
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);});
    }
}
