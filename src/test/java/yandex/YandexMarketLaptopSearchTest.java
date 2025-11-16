package yandex;

import helpers.ConfigProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.home.YandexMarketHomeCatalogPage;
import pages.pricelist.YandexMarketProductListPage;
import java.util.List;
import static com.codeborne.selenide.Selenide.open;


/**
 * Тестовый класс для проверки комплексного сценария работы со смартфонами в Яндекс Маркете.
 * Включает фильтрацию товаров и проверку соответствия фильтрам.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 * @see BaseTest
 */
public class YandexMarketLaptopSearchTest extends BaseTest {

    /**
     * Параметризованный тестовый метод для проверки комплексного сценария фильтрации и поиска смартфонов в Яндекс Маркете.
     * Выполняет шаги: открытие каталога, навигация по категориям, фильтрация по бренду и
     * проверка результатов фильтрации.
     *
     * @param category     категория товара.
     * @param subcategory  подкатегория товара.
     * @param listBrands   список брендов.
     * @param resultSearch список ожидаемых брендов.
     * @throws AssertionError если заголовок страницы не соответствует ожидаемому и, если
     *                        товары на страницах после применения фильтра, не соответствуют бренду.
     * @see helpers.DataProvider#parametersCheck источник тестовых данных.
     * @see YandexMarketHomeCatalogPage класс для работы с главной страницей и каталогом.
     */
    @Owner("Габбасова Лилиана Альбертовна")
    @DisplayName("Проверка прайс-листа после фильтрации товаров")
    @Feature("Фильтрация товаров")
    @Story("Навигация по каталогу и применение фильтра по бренду")
    @Description("Этот тест нужен для того, чтобы проверить работу навигации по категориям," + " фильтрацию по бренду." + " Здесь проверяется соответствуют ли товары на страницах выбранной фильтрации.")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#parametersCheck")
    public void yandexMarketLaptopFilterTest(String category, String subcategory, List<String> listBrands, List<String> resultSearch) {
        open(ConfigProvider.URL);

        YandexMarketHomeCatalogPage yandexHomePageCatalog = new YandexMarketHomeCatalogPage();
        yandexHomePageCatalog.openCatalog();

        yandexHomePageCatalog.navigateCatalog(category, subcategory);

        YandexMarketProductListPage yandexMarketPageAfterCatalog = new YandexMarketProductListPage();
        Assertions.assertTrue(yandexMarketPageAfterCatalog.isTitleContains(subcategory), "Это не та страница, должен быть раздел c:" + subcategory);

        YandexMarketProductListPage yandexMarketProductListPage = new YandexMarketProductListPage();
        yandexMarketProductListPage.filterByBrands(listBrands);

        yandexMarketProductListPage.waitResultSearch();

        Assertions.assertTrue(yandexMarketProductListPage.checkAllPagesMatchFilters(resultSearch, listBrands), "Один из товаров не соответствует фильтрам: бренды: " + resultSearch);
    }
}

