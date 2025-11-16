package pages.pricelist.component;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Класс для фильтрации прайс-листа товаров по бренду в Яндекс Маркете.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 */
public class FilterComponent {
    /**
     * Кнопка "Показать еще" для отображения всех брендов.
     */
    private final SelenideElement SHOW_MORE_BUTTON = $x("//div[@data-auto='filter' and contains(.//span,'Бренд')]//div[@data-zone-name='showMoreFilters']//button");

    /**
     * Метод для получения локатора бренда по названию.
     *
     * @param brand название бренда.
     * @return локатор бренда.
     */
    private ElementsCollection getBrandLocator(String brand) {
        return $$x("//div[@data-auto='filter' and contains(.//span,'Бренд')]//label[@role='checkbox']//span").filter(text(brand));
    }

    /**
     * Метод, который устанавливает значение бренда для фильтрации товаров.
     * После установки значения товары будут отфильтрованы по выбранному бренду.
     *
     * @param brand минимальное значение цены для фильтрации
     */
    @Step("Фильтрация по бренду: {brand}")
    public void filterByBrand(String brand) {
        if (!selectBrandFromList(getBrandLocator(brand), brand)) {
            showAllBrands();
            searchAndSelectBrand(brand);
        }
    }

    /**
     * Метод, который показывает все бренды.
     * После нажатия кнопки, открывается список всех доступных брендов.
     */
    public void showAllBrands() {
        SHOW_MORE_BUTTON.shouldBe(visible, enabled).click();
    }

    /**
     * Метод, который выполняет поиск и выбор бренда через поисковую строку.
     * Используется когда бренд не отображается в основном списке.
     *
     * @param brand название бренда для поиска и выбора.
     */
    public void searchAndSelectBrand(String brand) {
        if (getBrandLocator(brand).get(0).isDisplayed()) {
            getBrandLocator(brand).get(0).click();
        } else {
            searchBrandInInput(brand);
        }
    }

    /**
     * Метод, который выполняет поиск бренда через поле ввода.
     * Вводит название бренда в поисковую строку и выбирает найденный результат.
     *
     * @param brand название бренда для поиска.
     */
    private void searchBrandInInput(String brand) {
        $x("//div[@data-auto='filter' and contains(.//span,'Бренд')]//input")
                .shouldBe(visible).setValue(brand);
        getBrandLocator(brand).first().click();
    }

    /**
     * Метод, который выбирает бренд из списка доступных брендов.
     * Выполняет поиск бренда в переданном списке элементов и кликает на него, если находит.
     *
     * @param brandElements список брендов.
     * @param brand         название бренда для выбора.
     * @return true если бренд был найден и выбран, false если бренд не найден в списке.
     */
    private boolean selectBrandFromList(ElementsCollection brandElements, String brand) {
        SelenideElement brandElement = brandElements.findBy(text(brand));
        if (brandElement.exists()) {
            brandElement.click();
            return true;
        }
        return false;
    }
}

