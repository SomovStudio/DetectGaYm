import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Helper {
    public static WebDriver driver;
    public static BrowserMobProxy proxy;

    /* Возвращает путь к драйверу */
    public static String getDriverPath() {
        String path = System.getProperty("user.dir");
        path = path.substring(0, path.length() - 4);
        path = path + "\\driver\\chromedriver.exe";
        System.out.println("PATH DRIVER: " + path);
        return path;
    }

    /* Возвращает путь в папку программы */
    public static String getFolderPath() {
        String path = System.getProperty("user.dir");
        path = path.substring(0, path.length() - 4);
        return path;
    }

    /* Инициализация работы с Прокси */
    public static void initProxyForChromeDriver(int port, ArrayList<String> arguments, String nameHar) {
        System.setProperty("webdriver.chrome.driver", getDriverPath());

        // старт прокси
        proxy = new BrowserMobProxyServer();
        proxy.setTrustAllServers(true);
        proxy.start(port);
        System.out.println("PROXY: start (port: " + port + ")");

        // получить объект Selenium
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // настройка для драйвера
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        ChromeOptions options = new ChromeOptions();
        for (String argument : arguments) {
            options.addArguments(argument);
            System.out.println("PROXY: chrome options (argument: " + argument + ")");
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        // создание драйвера
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        System.out.println("PROXY: created web driver");

        // включить более детальный захват HAR
        proxy.newHar(nameHar);
        System.out.println("PROXY: enable more detailed HAR capture (name: " + nameHar + ")");
    }

    /* Заканчивает работу с Прокси */
    public static void endWorkProxy() {
        driver.close();
        driver.quit();
        proxy.stop();
        System.out.println("PROXY: stop");
    }

    /* Возвращает массив ссылок полученных из HAR */
    public static ArrayList<String> getLinksFromHar() throws UnsupportedEncodingException {
        ArrayList<String> links = new ArrayList<String>();
        // получить данные HAR
        Har har = Helper.proxy.getHar();
        for (int i = 0; i < har.getLog().getEntries().size(); i++) {
            String link = har.getLog().getEntries().get(i).getRequest().getUrl();
            links.add(java.net.URLDecoder.decode(link, "UTF-8"));
        }
        System.out.println("PROXY: received " + links.size() + " links from HAR");
        return links;
    }

    /* Очистка HAR */
    public static void clearHar(String newNameHar) {
        proxy.newHar(newNameHar);
    }

    /* Поиск GA (google analytics) событий */
    public static boolean searchGAEvent(ArrayList<String> harLinks, String category, String action, String label) {
        for (String link : harLinks) {
            if (link.contains("google-analytics.com/collect") && link.contains("&t=event&")) {
                if (link.contains("ec=" + category) && link.contains("ea=" + action) && link.contains("el=" + label)) {
                    System.out.println("STEP: event GA [" + category + "][" + action + "][" + label + "] - detect");
                    return true;
                }
            }
        }
        return false;
    }

    /* Поиск YM (yandex metrika) событий */
    public static boolean searchYMEvent(ArrayList<String> harLinks, String code) {
        for (String link : harLinks) {
            if (link.contains("mc.yandex.ru/watch") && link.contains("&page-url=goal")) {
                if (link.contains(code)) {
                    System.out.println("STEP: event YM [" + code + "] - detect");
                    return true;
                }
            }

        }
        return false;
    }

    /* Вывести заголовок теста */
    public static void showTitle(String testname) {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("| START TEST ---------------------------------");
        System.out.println("| " + testname);
        System.out.println("|---------------------------------------------");
    }

    /* Вывод ошибки */
    public static void showError(Exception e) {
        System.out.println("| ERROR --------------------------------------");
        //System.out.println(e.getMessage());
        System.out.println(e.fillInStackTrace());
        System.out.println("|---------------------------------------------");
        System.out.println(" ");
        endWorkProxy();
        Assert.assertEquals("", "ERROR");
    }

    /* Показать сообщение о том что тест провален */
    public static void showFail(String failMessage){
        System.out.println("| FAILED -------------------------------------");
        System.out.println("| " + failMessage);
        System.out.println("|---------------------------------------------");
        System.out.println(" ");
        endWorkProxy();
        Assert.assertEquals("PASSED", "FAILED");
    }

    /* Показать успех */
    public static void showPass(String message) {
        System.out.println("TEST -----------------------------------------");
        System.out.println("| " + message);
        System.out.println("|---------------------------------------------");
        Assert.assertEquals("PASSED", "PASSED");
    }

    /* Ожидание появление элемента
     * Параметры: локатор, время ожидания в секундах
     */
    public static WebElement waitFindElement(String locator, int timeout) throws InterruptedException {
        int sec = 0;
        WebElement element = null;
        do {
            Thread.sleep(1000);
            try {
                element = driver.findElement(By.xpath(locator));
            } catch (Exception e) {
                element = null;
            }
            if (element != null) {
                if (element.isDisplayed()) break;
            }
            sec++;
        } while (sec < timeout);
        Assert.assertTrue(element.isDisplayed());
        return element;
    }

    public static boolean waitElement(String locator, int timeout) throws InterruptedException {
        WebElement element = null;
        for(int i = 0; i < timeout; i++)
        {
            try {
                element = driver.findElement(By.xpath(locator));
            } catch (Exception e) {
                element = null;
            }
            if (element != null) return true;
            Thread.sleep(1000);
        }
        return false;
    }

    /* Ожидание текста в элементе */
    public static boolean waitTextInElement(String locator, String text, int timeout) throws InterruptedException {
        int sec = 0;
        WebElement element;
        do {
            Thread.sleep(1000);
            element = driver.findElement(By.xpath(locator));
            sec++;
        } while (!element.isDisplayed() && !text.equals(element.getText()) && sec < timeout);
        return text.equals(element.getText());
    }

    /* Проверить существование элемента */
    public static boolean existsElement(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /* Чтение JSON файла */
    public static JSONObject readJsonFile(String filename) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(getFolderPath() + filename));
        JSONObject jo = (JSONObject) obj;
        return jo;
    }

    public static JSONObject readJsonFile2(String filename) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(filename));
        JSONObject jo = (JSONObject) obj;
        return jo;
    }
}
