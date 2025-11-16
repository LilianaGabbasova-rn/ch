package yandex;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.CustomAllureSelenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Базовый абстрактный класс для настройки браузера в тестах.
 * Настраивает Chrome браузер перед каждым тестом и закрывает его после.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 */
abstract public class BaseTest {
    /**
     * Метод, который настраивает параметры браузера.
     */
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1200";
        Configuration.pageLoadStrategy = "normal";
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Selenide.clearBrowserCookies();

    }

    /**
     * Метод, который выполняется перед каждым тестом.
     * Настраивает браузер и добавляет Allure наблюдателя для отчетности.
     */
    @BeforeEach
    public void init() {
        setUp();
        SelenideLogger.addListener("allure", new CustomAllureSelenide().screenshots(true).savePageSource(true).includeSelenideSteps(true));
    }

    /**
     * Метод, который выполняется после каждого теста.
     * Закрывает браузер и освобождает ресурсы.
     */
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
