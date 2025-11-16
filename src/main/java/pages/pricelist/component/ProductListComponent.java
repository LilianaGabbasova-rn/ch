package pages.pricelist.component;

import com.codeborne.selenide.*;
import helpers.ConfigProvider;
import io.qameta.allure.Allure;
import pages.card.ProductCardPage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.*;

/**
 * Класс для работы со списком товаров на странице прайс-листа.
 * Предоставляет методы для проверки товаров по фильтрам и навигации по страницам.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 * @see ProductListLocators
 * @see LoadProduct
 */
public class ProductListComponent implements ProductListLocators {
    /**
     * Компонент для ожидания загрузки товаров.
     */
    public LoadProduct load;
    /**
     * Множество для отслеживания проверенных товаров.
     */
    private Set<String> checkedProducts = new HashSet<>();

    /**
     * Конструктор класса для работы со списком товаров.
     */

    public ProductListComponent() {
        this.load = new LoadProduct();
    }

    /**
     * Метод для проверки соответствия всех товаров на всех страницах заданным фильтрам.
     *
     * @param brandsInNames список брендов для проверки ожидаемого результата.
     * @param brandsCard    список брендов.
     * @return true если все товары на всех страницах соответствуют фильтрам
     */
    public boolean checkAllPagesMatchFilters(List<String> brandsInNames, List<String> brandsCard) {
        PaginationComponent pagination = new PaginationComponent();
        checkedProducts.clear();
        load.waitForProductsLoad();
        int currentPage = 1;
        while (pagination.isNextPageAvailable()) {
            if (currentPage <= ConfigProvider.MAX_PAGE) {
                pagination.clickNextPage();
                load.waitForProductsLoad();
                currentPage++;
            }
            else
            {
                break;
            }
        }
        if (!checkCurrentPageMatchesFilters(brandsInNames, brandsCard)) {
            return false;
        }
        return true;
    }

    /**
     * Метод для проверки соответствия товаров на текущей странице заданным фильтрам.
     *
     * @param brands     список брендов для проверки ожидаемого результата.
     * @param brandsCard список брендов.
     * @return true если все товары на текущей странице соответствуют фильтрам.
     */
    private boolean checkCurrentPageMatchesFilters(List<String> brands, List<String> brandsCard) {
        load.waitForProductsLoad();
        String originalWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        for (int i = 0; i < PRODUCT_CARDS.size(); i++) {
            SelenideElement productCard = PRODUCT_CARDS.get(i);
            SelenideElement priceElement = PRODUCT_PRICE.get(i);

            String productName = executeJavaScript(
                    "return arguments[0].textContent || arguments[0].innerText;", productCard);
            String priceText = executeJavaScript(
                    "return arguments[0].textContent || arguments[0].innerText;", priceElement);
            String productKey = productName + "_" + priceText;

            Allure.step("Товар: " + productName);

            if (checkedProducts.contains(productKey)) {
                continue;
            }

            boolean brandValid = isProductBrandValid(productName, brands);
            if (!brandValid) {
                brandValid = checkBrandInProductCard(PRODUCT_CARDS_LINK.get(i), brandsCard, originalWindow);
            }

            if (brandValid) {
                checkedProducts.add(productKey);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод для проверки бренда товара через открытие карточки товара.
     *
     * @param brands         список ожидаемых брендов.
     * @param originalWindow идентификатор исходного окна браузера.
     * @return true если бренд товара соответствует одному из ожидаемых брендов.
     */
    private boolean checkBrandInProductCard(SelenideElement productLink, List<String> brands,
                                            String originalWindow) {
        load.waitForProductsLoad();
        executeJavaScript("arguments[0].click();", productLink);

        for (String windowHandle : WebDriverRunner.getWebDriver().getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                switchTo().window(windowHandle);
                break;
            }
        }

        ProductCardPage productCardPage = page(ProductCardPage.class);
        boolean brandMatches = productCardPage.isBrandMatches(brands);

        Selenide.closeWindow();
        switchTo().window(originalWindow);
        load.waitForProductsLoad();
        return brandMatches;
    }

    /**
     * Метод для проверки валидности бренда товара по названию.
     *
     * @param productName   название товара.
     * @param brandsInNames список ожидаемых брендов.
     * @return true если название товара содержит один из ожидаемых брендов.
     */
    public boolean isProductBrandValid(String productName, List<String> brandsInNames) {
        for (String expectedBrand : brandsInNames) {
            if (productName.toLowerCase().contains(expectedBrand.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
