package pages.card;

import io.qameta.allure.Step;
import java.util.List;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Класс для работы с карточкой товара на странице прайс-листа.
 * Предоставляет методы для получения информации о бренде товара.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 */
public class ProductCardPage {

    /**
     * Метод для получения названия бренда товара из изображения.
     * Извлекает текст из атрибута alt изображения бренда.
     *
     * @return название бренда или пустую строку если бренд не найден
     */
    public String getBrandFromImage() {
        return $x("//div[@data-zone-name='showAll']//img").shouldBe(visible)
                .getAttribute("alt")
                .trim();
    }

    /**
     * Метод для проверки соответствия бренда товара ожидаемым брендам.
     * Сравнивает полученный бренд с каждым из ожидаемых брендов.
     *
     * @param brands список ожидаемых брендов для проверки
     * @return true если бренд товара соответствует одному из ожидаемых брендов, а иначе false.
     */
    @Step("Проверка соответствия бренда товара ожидаемым брендам в карточке товара: {brands}")
    public boolean isBrandMatches(List<String> brands) {
        String actualBrand = getBrandFromImage();
        for (String expectedBrand : brands) {
            if (actualBrand.equalsIgnoreCase(expectedBrand)) {
                return true;
            }
        }
        return false;
    }
}