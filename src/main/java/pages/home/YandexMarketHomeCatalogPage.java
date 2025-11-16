package pages.home;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс для работы на главной странице Яндекс Маркета.
 * Предоставляет возможность открыть каталог по выбранному параметру.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 1
 * @see YandexMarketHomeCatalogPage
 */
public class YandexMarketHomeCatalogPage {

    /**
     * Метод, который позволяет открывать каталог через кнопку.
     */
    @Step("Открытие каталога")
    public void openCatalog() {
        $x("//button[./span[text()='Каталог']]").shouldBe(exist).click();
    }

    /**
     * Метод для выбора категории товара в каталоге.
     * Наводит курсор на указанную категорию.
     *
     * @param category категория товара.
     */
    public void navigateCatalogCategory(String category) {
        $x("//div[@data-auto='catalog-content']//li[.//span[text()='" + category + "']]")
                .shouldBe(visible).hover();
    }

    /**
     * Метод для выбора подкатегории товара в каталоге.
     * Кликает на указанную подкатегорию.
     *
     * @param subcategory подкатегория товара
     */
    public void navigateCatalogSubcategory(String subcategory) {
        $x("//div[@role='tabpanel']//div[@data-zone-name='linkSnippet']//a[text()='" + subcategory + "']").
                shouldBe(visible).click();
    }

    /**
     * Метод, который позволяет выбирать категорию и подкатегорию товара.
     * Помогает открыть страницу по выбранным параметрам.
     *
     * @param category    категория товара
     * @param subcategory подкатегория товара
     */
    @Step("Выбор категории и подкатегории каталога: категория '{category}', подкатегория '{subcategory}'")
    public void navigateCatalog(String category, String subcategory) {
        navigateCatalogCategory(category);
        navigateCatalogSubcategory(subcategory);
    }
}
