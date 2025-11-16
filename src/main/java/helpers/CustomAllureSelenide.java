package helpers;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.LogEvent;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * Кастомный наблюдатель для логирования событий Selenide с автоматическими скриншотами в Allure отчетах.
 * Расширяет функциональность стандартного AllureSelenide, добавляя создание скриншотов после каждого события.
 *
 * @see AllureSelenide
 * @author Габбасова Лилиана Альбертовна
 * @version 1
 */
public class CustomAllureSelenide extends AllureSelenide {

    /**
     * Обрабатывает событие после его выполнения.
     * Создает скриншот и передает управление родительскому классу.
     * @param event событие Selenide
     */
    @Override
    public void afterEvent(LogEvent event) {
        takeScreenshotForEvent(event);
        super.afterEvent(event);
    }
    /**
     * Создает скриншот для событий и добавляет его как вложение в Allure отчет.
     * @param event событие Selenide
     */
    private void takeScreenshotForEvent(LogEvent event) {
        try
        {
            if (WebDriverRunner.hasWebDriverStarted())
            {
                byte[] screenshot = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                Allure.getLifecycle().addAttachment(
                        event.getElement() + " - " + event.getSubject(),
                        "image/png",
                        "png",
                        screenshot
                );
            }
        }
        catch (Exception ignored)
        {
        }
    }
}