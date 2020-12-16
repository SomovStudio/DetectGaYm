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
        textAreaInformation.setCaretPosition(0);
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
                System.getProperty("line.separator") + "execute_js - позволяет выполнять JavaScript код." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "   - Описание - текстовое представление действия" +
                System.getProperty("line.separator") + "   - Локатор - не нужно заполнять" +
                System.getProperty("line.separator") + "   - Значение - строка JavaScript кода (двойные кавычки обязательно нужно экранировать)" +
                System.getProperty("line.separator") + "   - Время ожидания - не нужно заполнять" + System.getProperty("line.separator") +
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
                System.getProperty("line.separator") + "--------------------------------------------------------------------------------------------------------------" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "");
        textAreaInformation.setCaretPosition(0);
    }

    public void inctructionCreateTest(){
        textAreaInformation.setText("ОБЩАЯ ИНФОРМАЦИЯ" +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "Осномное меню программы" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "Файл:" +
                System.getProperty("line.separator") + "   - Чтобы создать тест нужно в меню 'Файл' нажать пункт 'Создать новый тест' " +
                System.getProperty("line.separator") + "   - Чтобы открыть тест нужно в меню 'Файл' нажать пункт 'Открыть тест' " +
                System.getProperty("line.separator") + "   - Чтобы открыть тест используя специальную кодировку нужно в меню 'Файл' нажать пункт 'Открыть тест как...' и выбрать необходимую кодировку" +
                System.getProperty("line.separator") + "   - Чтобы сохранить тест нужно в меню 'Файл' нажать пункт 'Сохранить тест' " +
                System.getProperty("line.separator") + "   - Чтобы сохранить тест используя специальную кодировку нужно в меню 'Файл' нажать пункт 'Сохранить тест как' и выбрать необходимую кодировку" +
                System.getProperty("line.separator") + "Запуск:" +
                System.getProperty("line.separator") + "   - Чтобы запустить выполнение теста нужно в меню 'Запуск' нажать пункт 'Выполнить тест'" +
                System.getProperty("line.separator") + "   - Чтобы запустить выполнение группы тестов нужно в меню 'Запуск' нажать пункт 'Выполнить группу тестов'" +
                System.getProperty("line.separator") + "   - Чтобы тесты учитывали кодировку необходимо включить флаг 'Выполнять тест в текущей кодировку'" +
                System.getProperty("line.separator") + "Поддержка:" +
                System.getProperty("line.separator") + "   - Если нужно сформировать команду для запуска теста например из bat файла, для этого в меню 'Поддержка' нажать пункт 'Сформировать команду для запуска'" +
                System.getProperty("line.separator") + "   - Если нужно выполнить проверку json файла, для этого в меню 'Поддержка' нажать пункт 'Валидатор JSON'" + System.getProperty("line.separator") + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "На вкладке 'Общие параметры' необходимо задать параметры теста" +
                System.getProperty("line.separator") + "   - Описание - текстовое поле для краткого описания теста (наименование)" +
                System.getProperty("line.separator") + "   - Порт - текстовое поле в котором указывается порт для работы прокси (по умолчанию 9091)" +
                System.getProperty("line.separator") + "   - Опции - список настроек необходимых при работе Chrome WebDriver (по умолчанию --ignore-certificate-errors)" +
                System.getProperty("line.separator") + "   - HAR - текстовое поле в котором указывается имя для текущего пакета данных" +
                System.getProperty("line.separator") + "   - Лимит - текстовое поле в котором указывается количество секунд ожидания для загрузки старницы (0 означает неограниченное время ожидания)" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "На вкладке 'Данные' необходимо задать параметры для проверки событий GA и YM" +
                System.getProperty("line.separator") + "   - Заголовок - текстовое поле для ввода любого текста выводимого в консоль при выполнении теста" +
                System.getProperty("line.separator") + "   - URL - текстовое поле в котором указывается ссылка на страницу на которой будет выполнятся тест" +
                System.getProperty("line.separator") + "   - GA:category - текстовое поле для ввода ключа category события GA" +
                System.getProperty("line.separator") + "   - GA:action - текстовое поле для ввода ключа action события GA" +
                System.getProperty("line.separator") + "   - GA:label - текстовое поле для ввода ключа label события GA" +
                System.getProperty("line.separator") + "   - YM:code - текстовое поле для ввода ключа события YM" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "На вкладке 'Шаги' описываются шаги которые необходимо выполнить на странице сайта включая проверку событий GA/YM" +
                System.getProperty("line.separator") + "   - Описание - текстовое поле в котором описывается данный шаг, данный текст выводится в консоль при выполнении теста" +
                System.getProperty("line.separator") + "   - Тип действия - текстовое поле для выбора или ввода ключа действия" +
                System.getProperty("line.separator") + "   - Локатор/Протокол/Условия - текстовое поле для ввода локатора в формате xpath или протокол GA/YM или ключ условия" +
                System.getProperty("line.separator") + "   - Значение - текстовое поле для ввода значения которое необходимо вписать в какой либо элемент (например input) или использовать в условии" +
                System.getProperty("line.separator") + "   - Время ожидания - указывается количество секунд ожидания для выполнения действия (или количество попыток при проверке событий GA/YM)" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "ПРИМЕР СОЗДАНИЯ ТЕСТА" +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "1. Поле 'Описание' ввести текст: Проверка событий на главной странице" +
                System.getProperty("line.separator") + "2. Поле 'Порт' указать номер: 9091" +
                System.getProperty("line.separator") + "3. Поле 'Опции' выбрать опцию: --ignore-certificate-errors" +
                System.getProperty("line.separator") + "4. Поле 'HAR' ввести текст: test.har" +
                System.getProperty("line.separator") + "5. Поле 'Лимит' ввести значение: 60" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "6. Переходим на вкладку 'Данные' и заполняем таблицу данными" +
                System.getProperty("line.separator") + "6.1 Поле 'Заголовок' ввести текст: Открыл страницу https://test.ru" +
                System.getProperty("line.separator") + "6.2 Поле 'URL' ввести значение: https://test.ru" +
                System.getProperty("line.separator") + "6.3 Поле 'GA:category' ввести значение ключа для category: test_ga_category" +
                System.getProperty("line.separator") + "6.4 Поле 'GA:action' ввести значение ключа для action: test_ga_action" +
                System.getProperty("line.separator") + "6.5 Поле 'GA:label' ввести значение ключа для label: test_ga_label" +
                System.getProperty("line.separator") + "6.6 Поле 'YM:code' ввести значение ключа для code: test_ym_code" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "7. Переходим на вкладку 'Шаги' и заполняем таблицу данными" +
                System.getProperty("line.separator") + "7.1 Описание первой строки:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Ввел имя" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: input_value" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' ввести локатор элемента: //input[@id='test-form-input-name']" +
                System.getProperty("line.separator") + "   - Поле 'Значение' ввести текст: Тестировщик" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 1" +
                System.getProperty("line.separator") + "7.2 Описание второй строки:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Ввел телефон" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: input_value" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' ввести локатор элемента: //input[@id='test-form-input-phone']" +
                System.getProperty("line.separator") + "   - Поле 'Значение' ввести текст: 9999999999" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 1" +
                System.getProperty("line.separator") + "7.3 Описание третьей строки:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Нажал на кнопку" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: click_element" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' ввести локатор элемента: //buttom[@id='test-form-button']" +
                System.getProperty("line.separator") + "   - Поле 'Значение' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 1" +
                System.getProperty("line.separator") + "7.4 Описание четвертой строки:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Жду сообщения об успешной отправке заявки" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: wait_element" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' ввести локатор элемента: //div[@id='test-order-success']" +
                System.getProperty("line.separator") + "   - Поле 'Значение' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 25" +
                System.getProperty("line.separator") + "7.5 Описание пятой строки:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Проверяю событие GA" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: test_defaults_ga" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Значение' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 5" +
                System.getProperty("line.separator") + "7.6 Описание шестой строки:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Проверяю событие YM" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: test_defaults_ym" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Значение' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 5" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "8. Сохранияем тест под именем test-form.json выполнив действия в меню 'Файл'->'Сохранить тест'" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "9. Запустим тест выполнив действия в меню 'Запуск'->'Выполнить тест'" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "Если всё было сделано правильно тест будет запущен в окне браузера." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "ДОПОЛНИТЕЛЬНО" +
                System.getProperty("line.separator") + "=======================================================" +
                System.getProperty("line.separator") + "Чтобы выполнить опциональную проверку GA и YM событий нужно указать протокол и значение:" +
                System.getProperty("line.separator") + "- Действие: test_optionally_ga | GA протокол: google-analytics.com/collect" +
                System.getProperty("line.separator") + "   pageview&_s=1&dl=https://test.ru" +
                System.getProperty("line.separator") + "   ec=test_ga_category" +
                System.getProperty("line.separator") + "   ea=test_ga_action" +
                System.getProperty("line.separator") + "   el=test_ga_label" +
                System.getProperty("line.separator") + "- Действие: test_optionally_ym | YM протокол: mc.yandex.ru/watch" +
                System.getProperty("line.separator") + "   test_ym_code" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "--------------------------------------------------------------------------------------------------------------" +
                System.getProperty("line.separator") + "Чтобы реализовать выполнение условий необходимо описать шаги следующего вида:" +
                System.getProperty("line.separator") + "1. Описание строки поиска элемента:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Находим элемент на странице" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: find_element" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' ввести локатор элемента: //input[@id='test-form-input-name']" +
                System.getProperty("line.separator") + "   - Поле 'Значение' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 5" +
                System.getProperty("line.separator") + "2. Описание строки начала условия:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Сравниваем значение элемента" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: if_get_attribute_value" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' ввести ключ условия: ==" +
                System.getProperty("line.separator") + "   - Поле 'Значение' ввести текст: Тестировщик" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 0" +
                System.getProperty("line.separator") + "3. Описание множестра строк различных действий" +
                System.getProperty("line.separator") + "4. Описание строки завершения условия:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Конец условия" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: end_if" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Значение' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 0" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "Так же можно использовать ключи else и else_if чтобы построить сложное условие." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "--------------------------------------------------------------------------------------------------------------" +
                System.getProperty("line.separator") + "Чтобы ввести в элемент значение из другого элемента необходимо описать шаги следующего вида:" +
                System.getProperty("line.separator") + "1. Описание строки поиска элемента:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Находим элемент на странице" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: find_element" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' ввести локатор элемента: //input[@id='test-form-input-phone']" +
                System.getProperty("line.separator") + "   - Поле 'Значение' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 5" +
                System.getProperty("line.separator") + "2. Описание строки ввода данных из другого элемента:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Вводим телефон полученный из другого элемента" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: input_value" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' ввести локатор элемента: //input[@id='test-form-input-name']" +
                System.getProperty("line.separator") + "   - Поле 'Значение' выбираем ключь: get_text" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 1" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "Таким образом значение из одного элемента можно ввести в другой элемент страницы." + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "--------------------------------------------------------------------------------------------------------------" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "Чтобы выполнить JavaScript код воспользуйтесь специальным действием execute_js" +
                System.getProperty("line.separator") + "Описание строки выполнения скрипта:" +
                System.getProperty("line.separator") + "   - Поле 'Описание' ввести текст: Выполнение JavaScript" +
                System.getProperty("line.separator") + "   - Поле 'Тип данных' выбрать действие: execute_js" +
                System.getProperty("line.separator") + "   - Поле 'Локатор/Протокол/Условия' не заполняем" +
                System.getProperty("line.separator") + "   - Поле 'Значение' вводим строку кода (обязательно нужно экранировать двойные кавычки) $(\\\"body\\\").find(\\\"#popup-abonent\\\").css({display:\\\"none!important\\\"});" +
                System.getProperty("line.separator") + "   - Поле 'Время ожидания' ввести значение: 0" +
                System.getProperty("line.separator") + "--------------------------------------------------------------------------------------------------------------" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "Ручная проверка событий Yandex metrika" +
                System.getProperty("line.separator") + "Чтобы отслеживать события из Яндекс.Метрики, вставьте параметр site.ru/?_ym_debug=1 в браузерную строку." +
                System.getProperty("line.separator") + "Пока параметр в браузерной строке, вы будете видеть события Яндекс.Метрики в консоли." +
                System.getProperty("line.separator") + "Если закроете и снова откроете страницу, вам придётся заново вставлять параметр /?_ym_debug=1" +
                System.getProperty("line.separator") + "");
        textAreaInformation.setCaretPosition(0);
    }
}
