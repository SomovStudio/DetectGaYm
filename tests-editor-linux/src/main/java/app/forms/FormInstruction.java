package app.forms;

import javax.swing.*;

public class FormInstruction {
    public JPanel PanelMain;
    public JTextArea textAreaInformation;

    public FormInstruction() {

    }

    public void instructionUpdate(){
        textAreaInformation.setText("Обновление Chrome Web Driver в системе Linux" +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "1. Скачать архив можно командой:" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   wget https://chromedriver.storage.googleapis.com/86.0.4240.22/chromedriver_linux64.zip" +
                System.getProperty("line.separator") + "   или скачать архив на сайте https://chromedriver.chromium.org/" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "2. Выполнить распаковку и установку драйвера с помощью следующих команд:" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   unzip chromedriver_linux64.zip" +
                System.getProperty("line.separator") + "   sudo mv chromedriver /usr/local/bin/chromedriver" +
                System.getProperty("line.separator") + "   sudo chown root:root /usr/local/bin/chromedriver" +
                System.getProperty("line.separator") + "   sudo chmod +x /usr/local/bin/chromedriver" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "3. Проверить версию устанлвденного дравера командой:" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   chromedriver --version" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "4. Обновить файл chromedriver в папке /detect-gaym/driver/linux/" +
                System.getProperty("line.separator") + "=======================================================" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "Обновление Chrome Web Driver в системе Windows" +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "1. Скачать архив на сайте https://chromedriver.chromium.org/" +
                System.getProperty("line.separator") + "2. Распаковать архив и обновить файл chromedriver.exe в папке \\detect-gaym\\driver\\windows\\" +
                System.getProperty("line.separator") + "=======================================================");
    }

    public void referenceActions() {
        textAreaInformation.setText("ТИПЫ ДЕЙСТВИЙ:" +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "open_page - открывает указанную страницу сайта в браузере." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - ссылка на страницу формата https://site.com" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "open_default_page - открывает страницу сайта (прописанную на вкладке Данные) в браузере." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "refresh_page - перезагружает текущую страницу." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "input_value - ввод данных в поле (элемент input)." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - в формате XPath запрос к элементу html страницы" +
                System.getProperty("line.separator") + "   - Значение - данные которые должны быть введены в поле (или данные из find_element)" +
                System.getProperty("line.separator") + "   - Время ожидания - количество секунд до появления элемента" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "click_element - нажать на элемент страницы." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - в формате XPath запрос к элементу html страницы" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - количество секунд до появления элемента" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "find_element - выполняется поиск элемента который затем будет хранится в глобальной переменной." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - в формате XPath запрос к элементу html страницы" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - количество секунд до появления текста" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "if_get_text - начало условия при котором сравниваются данные (text) элемента глобальной переменной (find_element)" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Условие - значение в формате: ==, !=, <, <=, >, >= (равно, не равно, меньше, меньше или равно, больше, больше или равно)" +
                System.getProperty("line.separator") + "   - Значение - данные которые должны быть проверены на соответствие" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "if_get_attribute_ - начало условия при котором сравниваются данные (attribute) элемента глобальной переменной (find_element)" +
                System.getProperty("line.separator") + "    (обязательно нужно дописывать объект, например if_get_attribute_id)" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Условие - значение в формате: ==, !=, <, <=, >, >= (равно, не равно, меньше, меньше или равно, больше, больше или равно)" +
                System.getProperty("line.separator") + "   - Значение - данные которые должны быть проверены на соответствие" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "else_if_get_text - иначе условие при котором сравниваются данные (text) элемента глобальной переменной (find_element)" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Условие - значение в формате: ==, !=, <, <=, >, >= (равно, не равно, меньше, меньше или равно, больше, больше или равно)" +
                System.getProperty("line.separator") + "   - Значение - данные которые должны быть проверены на соответствие" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "else_if_get_attribute_ - иначе условие при котором сравниваются данные (attribute) элемента глобальной переменной (find_element)" +
                System.getProperty("line.separator") + "    (обязательно нужно дописывать объект, например else_if_get_attribute_id)" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Условие - значение в формате: ==, !=, <, <=, >, >= (равно, не равно, меньше, меньше или равно, больше, больше или равно)" +
                System.getProperty("line.separator") + "   - Значение - данные которые должны быть проверены на соответствие" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "else - иначе условие выполняется в том случае если критерии условия не выполнены." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "end_if - завершение условия." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "wait_text - ожидание появления текста на странице." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - в формате XPath запрос к элементу html страницы" +
                System.getProperty("line.separator") + "   - Значение - текст который должен быть в элементе" +
                System.getProperty("line.separator") + "   - Время ожидания - количество секунд до появления текста" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "wait_element - ожидание появления элемента на странице." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - в формате XPath запрос к элементу html страницы" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - количество секунд до появления элемента" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "wait_element_not_visible - ожидание удаления элемента со страницы." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - в формате XPath запрос к элементу html страницы" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - количество секунд до удаления элемента" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "sleep - задержка действия на указанное количество секунд." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - количество секунд паузы" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "test_defaults_ga - проверка GA в режиме по умолчанию (данными берутся из вкладки Данные)." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - количество попыток чтобы найти GA значение" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "test_optionally_ga - опциональная проверка GA (данные вводятся вручную)." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Протокол - по умолчанию google-analytics.com/collect" +
                System.getProperty("line.separator") + "   - Значение - ключевое слово  GA" +
                System.getProperty("line.separator") + "   - Время ожидания - количество попыток чтобы найти GA значение" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "test_defaults_ym - проверка YM в режиме по умолчанию (данными берутся из вкладки Данные)." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - количество попыток чтобы найти YM значение" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "test_optionally_ym - опциональная проверка YM (данные вводятся вручную)." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Протокол - по умолчанию mc.yandex.ru/watch" +
                System.getProperty("line.separator") + "   - Значение - ключевое слово  YM" +
                System.getProperty("line.separator") + "   - Время ожидания - количество попыток чтобы найти YM значение" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "clear_har - очистить список отправленных событий." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "get_har - получить список всех отправленных событий." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "get_har_ga - получить список всех отправленных GA событий." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "get_har_ym - получить список всех отправленных YM событий." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "=======================================================");
        textAreaInformation.setCaretPosition(0);
    }

    public void inctructionCreateTest(){
        textAreaInformation.setText("Руководство по созданию теста" +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "1. Чтобы создать тест нужно в меню Файл выберите Создать новый тест " + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "2. На вкладке Общие параметры необходимо задать параметры теста" +
                System.getProperty("line.separator") + "   - Описание - это текстовое поле для краткого описания теста (наименование)" +
                System.getProperty("line.separator") + "   - Порт - это текстовое поле в котором указывается порт для работы прокси (по умолчанию 9091)" +
                System.getProperty("line.separator") + "=======================================================");
    }
}
