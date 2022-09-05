package pricingDandD;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Disabled
public class CheckPricing {

    @BeforeAll
    static void config() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
    }

    @Test
    void openPlansPageTest() {
        open("https://github.com");
        $(".HeaderMenu").$(byText("Pricing")).hover();
        $(".HeaderMenu").$(byText("Compare plans")).click();
        $(".font-mktg").shouldHave(text("Choose the plan that’s right for you.")).scrollTo();
    }
}
