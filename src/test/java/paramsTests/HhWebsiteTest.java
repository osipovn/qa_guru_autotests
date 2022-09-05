package paramsTests;

import baseConfig.TestBase;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


@DisplayName("Параметризованные тесты")
public class HhWebsiteTest extends TestBase {

    @BeforeAll
    static void browser(){
        Configuration.baseUrl = "https://hh.ru";
    }

    @ValueSource(strings = {"Бухгалтер", "Директор"})
    @ParameterizedTest(name = "Результаты поиска не пустые для запроса {0}")
    void liteHhVacancySearchTest(String testData) {
        open("");
        $("input[type='text']").sendKeys(testData);
        $("button[data-qa='search-button']").click();
        $$("div[data-qa='vacancy-serp__results']").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "Юрист,  Высшее юридическое образование",
            "DevOps,  Знание Linux",
    })
    @ParameterizedTest(name = "Результаты поиска содержат текст \"{1}\" для запроса: \"{0}\"")
    void commonComplexSearchTest(String testData, String expectedResult) {
        open(" ");
        $("input[type='text']").sendKeys(testData);
        $("button[data-qa='search-button']").click();
        $("div[data-qa='vacancy-serp__results']").shouldHave(text(expectedResult));
    }

    static Stream<Arguments> dataProviderForSberSiteMenuTest() {
        return Stream.of(
                Arguments.of(Jobs.QA, "Qa engineer", "Тестировщик"),
                Arguments.of(Jobs.DEVELOPER, "Java", "Разработчик"),
                Arguments.of(Jobs.ANALYST, "Data Analyst", "Бизнес-аналитик")
        );
    }
    @MethodSource("dataProviderForSberSiteMenuTest")
    @ParameterizedTest(name = "Для {0} на сайте есть вакансии {1}, {2}")
    void HhVacancySearchTest(Jobs jobs, String first, String second) {
        open("");
        $("input[type='text']").sendKeys(jobs.toString());
        $("button[data-qa='search-button']").click();
        $("div[data-qa='vacancy-serp__results']").shouldHave(text(first));
        $("div[data-qa='vacancy-serp__results']").shouldHave(text(second));
    }
}
