package pricingDandD;

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

@DisplayName("Тест на открытие страницы Цен на GitHub")
public class CheckPricing extends TestBase {

    @Test
    void openPlansPageTest() {
        open("https://github.com");
        $(".HeaderMenu").$(byText("Pricing")).hover();
        $(".HeaderMenu").$(byText("Compare plans")).click();
        $(".font-mktg").shouldHave(text("Choose the plan that’s right for you.")).scrollTo();
    }
}
