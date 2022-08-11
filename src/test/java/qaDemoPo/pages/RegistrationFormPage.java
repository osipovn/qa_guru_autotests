package qaDemoPo.pages;

import com.codeborne.selenide.SelenideElement;
import qaDemoPo.pages.components.CalendarComponents;
import qaDemoPo.pages.components.ResultsTableComponents;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {

    private final static String TITLE_TEXT = "Student Registration Form";
    private CalendarComponents calendarComponents = new CalendarComponents();
    private ResultsTableComponents resultsTableComponents = new ResultsTableComponents();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            buttonSubmit = $("#submit");


    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }
    public RegistrationFormPage setUserEmail(String value) {
        emailInput.setValue(value);

        return this;
    }
    public RegistrationFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setUserNumber(String value) {
        $("#userNumber").setValue(value);

        return this;
    }

    public RegistrationFormPage clickSubmit() {
        buttonSubmit.click();

        return this;
    }

    public RegistrationFormPage setHobbies(String value) {
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage markHobbies(int key) {
        $("[for=hobbies-checkbox-"+ key +"]").click();

        return this;
    }

    public RegistrationFormPage uploadPicture(String value) {
        $("#uploadPicture").uploadFile(new File(value));

        return this;
    }
    public RegistrationFormPage setCurrentAddress(String value) {
        $("#currentAddress").setValue(value);

        return this;
    }

    public RegistrationFormPage setStateAndCity(String state, String city) {
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();

        return this;
    }

    public RegistrationFormPage setBirthDay(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendarComponents.setDate(day, month, year);

        return this;
    }

    public RegistrationFormPage checkResultTableVisible() {
        resultsTableComponents.checkVisible();

        return this;
    }

    public RegistrationFormPage checkResult(String key, String value) {
        resultsTableComponents.checkResult(key, value);

        return this;
    }
}
