package pages.pricelist;

import io.qameta.allure.Step;
import pages.pricelist.component.FilterComponent;
import pages.pricelist.component.ProductListComponent;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс для работы со страницей прайс-листа товаров в Яндекс Маркете.
 * Предоставляет методы для проверки содержимого страницы.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 * @see FilterComponent
 * @see ProductListComponent
 */
public class YandexMarketProductListPage {
    protected final FilterComponent filters;
    /**
     * Компонент для работы со списком товаров.
     */
    protected final ProductListComponent productList;

    /**
     * Конструктор класса страницы прайс-листа товаров.
     */

    public YandexMarketProductListPage() {
        this.filters = new FilterComponent();
        this.productList = new ProductListComponent();
    }

    /**
     * Метод, который проверяет, что заголовок страницы содержит указанный текст.
     *
     * @param title текст для проверки в заголовке страницы.
     * @return true если заголовок содержит указанный текст, иначе false.
     */
    @Step("Проверка, что раздел содержит '{subcategory}'")
    public boolean isTitleContains(String title) {
        return $x("//h1[@data-auto='title']").getText().contains(title);
    }

    /**
     * Метод для фильтрации товаров по брендам.
     *
     * @param brands список брендов для фильтрации
     */
    @Step("Фильтрация по брендам: {brands}")
    public void filterByBrands(List<String> brands) {
        for (String brand : brands) {
            filters.filterByBrand(brand);
            productList.load.waitForProductsLoad();
        }
    }

    /**
     * Метод для ожидания результата поиска после фильтрации товаров.
     */
    @Step("Ожидание результата поиска")
    public void waitResultSearch() {
        productList.load.waitForProductsLoad();
    }

    /**
     * Метод, который проверяет, содержатся ли ожидаемые бренды на страницах прайс-листа
     * в карточках товара.
     *
     * @param brands     ожидаемые бренды товаров.
     * @param brandsCard бренды товаров.
     * @return возвращает true, если страницы соответствуют фильтрам, а иначе false.
     */
    @Step("Проверка всех страниц на соответствие фильтрам: цена - от {minPrice} до {maxPrice}, бренды - {brands}")
    public boolean checkAllPagesMatchFilters(List<String> brands, List<String> brandsCard) {
        return productList.checkAllPagesMatchFilters(brands, brandsCard);
    }
}