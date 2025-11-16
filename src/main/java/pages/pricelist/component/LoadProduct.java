package pages.pricelist.component;

import com.codeborne.selenide.CollectionCondition;

/**
 * Класс для ожидания загрузки товаров на странице прайс-листа.
 * Обеспечивает ожидание исчезновения индикаторов загрузки перед взаимодействием с товарами.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 * @see ProductListLocators
 */
public class LoadProduct implements ProductListLocators {

    /**
     * Метод для ожидания полной загрузки товаров.
     * Ожидает исчезновения загрузки.
     */
    public void waitForProductsLoad() {
        if (!LOADER.isEmpty()) {
          LOADER.shouldHave(CollectionCondition.size(0));
        }

        if (!LOADING_INDICATOR.isEmpty()) {
            LOADING_INDICATOR.shouldHave(CollectionCondition.size(0));;
        }
    }
}