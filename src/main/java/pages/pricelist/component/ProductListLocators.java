package pages.pricelist.component;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$x;

/**
 * Интерфейс с локаторами для списка товаров на странице прайс-листа.
 * Содержит локаторы для работы с карточками товаров, ценами и индикаторами загрузки.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 */
public interface ProductListLocators {
    /**
     * Локатор карточек товаров.
     */
    ElementsCollection PRODUCT_CARDS = $$x("//article//span[@itemprop='name']");

    /**
     * Локатор ссылок на карточки товаров.
     */
    ElementsCollection PRODUCT_CARDS_LINK = $$x("//article//a[span[@itemprop='name']]");

    /**
     * Локатор индикатора загрузки.
     */
    ElementsCollection LOADING_INDICATOR = $$x("//div[@data-auto='rollSkeleton']");

    /**
     * Локатор загрузки.
     */
    ElementsCollection LOADER = $$x("//div[contains(@data-auto,'loader')]");
    /**
     * Локатор элементов с ценами товаров.
     */
    ElementsCollection PRODUCT_PRICE = $$x("//article//span[contains(text(),'Цена')]");
}
