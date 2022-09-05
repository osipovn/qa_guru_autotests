package allureTests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Steps {
    @Step("Открыть заданный сайт")
    public void openMainPage() {
        open("");
    }

    @Step("Найти репозиторий {rep}")
    public void searchRepo(String rep) {
        $("input[type='text']").setValue(rep).pressEnter();
    }

    @Step("Перейти в репозиторий {rep}")
    public void clickRepo(String rep) {
        $(By.linkText(rep)).click();
    }

    @Step("Перейти на вкладку Issues")
    public void clickIssue() {
        $("#issues-tab").click();
    }

    @Step("Проверить, что сборка с номером {iss} есть в списке")
    public void checkIssue(String iss) {
        $(withText(iss)).should(Condition.exist);
    }
}
