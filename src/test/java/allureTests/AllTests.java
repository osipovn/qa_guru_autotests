package allureTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@DisplayName("Тесты с демонстрацией в Allure")
@Disabled
public class AllTests {

    private static String REPOSITORY = "jenkinsci/docker";
    private static String ISSUE = "#1434";

    @BeforeAll
    static void config() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1280x1024";
        Configuration.browser = "chrome";
    }

    @Test
    @DisplayName("Тест без описания шагов")
    public void testIssue() {
        open("");

        $("input[type='text']").setValue(REPOSITORY).pressEnter();
        $(By.linkText(REPOSITORY)).click();
        $("#issues-tab").click();

        $(withText(ISSUE)).should(Condition.exist);
    }

    @Test
    @DisplayName("Тест с описанием шагов, используя Лямбду")
    public void testWithLambda() {
        step("Открыть заданный сайт", () -> {
            open("");
        });

        step("Найти репозиторий " + REPOSITORY, () -> {
            $("input[type='text']").setValue(REPOSITORY).pressEnter();
        });

        step("Перейти в репозиторий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Перейти на вкладку Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверить наличие сборки с номером " + ISSUE, () -> {
            $(withText(ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Тест с описанием шагов, используя отдельный класс")
    public void testWithSteps() {
        Steps steps = new Steps();

        steps.openMainPage();

        steps.searchRepo(REPOSITORY);
        steps.clickRepo(REPOSITORY);
        steps.clickIssue();

        steps.checkIssue(ISSUE);
    }
}
