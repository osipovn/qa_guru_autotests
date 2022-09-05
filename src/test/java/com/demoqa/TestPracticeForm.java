package com.demoqa;

import baseConfig.TestBase;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@DisplayName("Тест формы demoQa без PageObject")
public class TestPracticeForm extends TestBase {

    @BeforeAll
    static void browser(){
        Configuration.baseUrl = "https://demoqa.com";
    }

    static void removeAds() {
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
    }

    @Test
    @DisplayName("Проверка заполнения формы регистрации")
    void fillStandartTest(){
        step("Открытие страницы с формой регистрации", () -> {
            open("/automation-practice-form");
            removeAds();
        });
        step("Заполнение формы регистрации", () -> {
            $("#firstName").setValue("Ivan");
            $("#lastName").setValue("Ivanov");
            $("#userEmail").setValue("ivanov@mail.ru");
            $(byText("Male")).click();
            $("#userNumber").setValue("9822292032");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption("1991");
            $(".react-datepicker__month-select").selectOption("June");
            $(".react-datepicker__day--015").click();
            $("#subjectsContainer").click();
            $("#subjectsInput").setValue("Arts").pressEnter();
            $("#subjectsInput").setValue("Math").pressEnter();
            $("#subjectsInput").setValue("Computer Science").pressEnter();
            $("#subjectsInput").setValue("Commerce").pressEnter();
            $("[for=hobbies-checkbox-1]").click();
            $("[for=hobbies-checkbox-3]").click();
            $("#uploadPicture").uploadFile(new File("src/test/resources/uploadFile.png"));
            $("#currentAddress").setValue("Moscow city, Lubyanka str., b. 1");
            $("#state").click();
            $(byText("NCR")).click();
            $("#city").click();
            $(byText("Delhi")).click();
            $("#submit").click();
        });
        step("Проверка корректности заполнения формы регистрации", () -> {
            $(".modal-body").shouldHave(
                    text("Ivan"),
                    text("Ivanov"),
                    text("ivanov@mail.ru"),
                    text("Male"),
                    text("982229203"),
                    text("15 June,1991"),
                    text("Arts, Maths, Computer Science, Commerce"),
                    text("Sports, Music"),
                    text("uploadFile.png"),
                    text("Moscow city, Lubyanka str., b. 1"),
                    text("NCR Delhi")
            );
        });
    }
}
