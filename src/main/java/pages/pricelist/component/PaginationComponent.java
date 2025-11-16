package pages.pricelist.component;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

/**
 * Класс для работы с пагинацией на странице прайс-листа.
 * Предоставляет методы для навигации по страницам с товарами.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 */
public class PaginationComponent {
    /**
     * Метод для прокрутки страницы к элементу пагинации.
     */
    public void clickNextPage() {
        ElementsCollection nextPages = $$x("//div[@data-zone-name='next']");
        nextPages.get(0).click();
    }

    /**
     * Метод для проверки доступности следующей страницы.
     *
     * @return true если кнопка следующей страницы доступна и отображается, а иначе false
     */
    public boolean isNextPageAvailable() {
        ElementsCollection nextPages = $$x("//div[@data-zone-name='next']");
        return !nextPages.isEmpty();
    }
}