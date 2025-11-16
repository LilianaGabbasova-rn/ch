package helpers;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

/**
 * Класс для предоставления параметров тестирования.
 * Содержит методы для передачи тестовых данных в параметризованные тесты.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 */
public class DataProvider {
    /**
     * Метод для предоставления параметров тестирования фильтрации телефонов.
     *
     * @return поток аргументов, содержащий категорию, подкатегорию,
     * а также названия брендов для фильтрации и ожидаемый результат поиска.
     */
    public static Stream<Arguments> parametersCheck() {
        return Stream.of(
                Arguments.of(ConfigProvider.CATEGORY,
                        ConfigProvider.SUBCATEGORY,
                           ConfigProvider.BRANDS,
                        ConfigProvider.EXPECTED_RESULT_SEARCH)
        );
    }
}
