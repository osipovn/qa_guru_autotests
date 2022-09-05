package selenideFirst;

import baseConfig.TestBase;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Домашнее задание на тест репозитория Selenide")
public class TestSelenideGitHub extends TestBase {

    @BeforeAll
    static void browser(){
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void selenideGitTest(){
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $(".markdown-body").shouldHave(text("Soft assertions"));
        $(byText("Soft assertions")).click();
        $(".markdown-body").shouldHave(text("Using JUnit5"));
    }
}
