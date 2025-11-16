package helpers;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.util.List;

/**
 * Класс для предоставления конфигурационных параметров тестирования.
 * Загружает настройки из конфигурационных файлов и предоставляет к ним доступ.
 *
 * @author Габбасова Лилиана Альбертовна
 * @version 2
 */
public interface ConfigProvider {
    Config config = readConfig();

    /**
     * Метод для чтения конфигурации из файла.
     * Если при запуске указан параметр testProfile, используется указанный файл,
     * иначе загружается tests.conf.
     *
     * @return объект Config с загруженными настройками
     */
    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile")) :
                ConfigFactory.load("tests.conf");
    }
    /**
     * Базовый URL для тестирования
     */
    String URL = config.getString("yandex.url");

    /**
     * Максимальное количество страниц для проверки прайс-листа.
     */
    Integer MAX_PAGE = config.getInt("validation.max_page");

    /**
     * Категория товаров для тестирования
     */
    String CATEGORY = config.getString("catalog.category");

    /**
     * Подкатегория товаров для тестирования
     */
    String SUBCATEGORY = config.getString("catalog.subcategory");

    /**
     * Список брендов для фильтрации
     */
    List<String> BRANDS = config.getStringList("filters.brands");
    /**
     * Ожидаемый результат поиска
     */
    List<String> EXPECTED_RESULT_SEARCH = config.getStringList("check.expected_result_search");

}