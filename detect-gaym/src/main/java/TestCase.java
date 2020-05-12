import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class TestCase {
    public static String description;
    public static int port;
    public static String arguments;
    public static String nameHar;
    public static ArrayList<String> data;
    public static ArrayList<String> steps;

    private static void beforeTest() {
        Helper.initProxyForChromeDriver(port, arguments, nameHar);
    }

    private static void afterTest() {
        Helper.endWorkProxy();
    }

    private static void test() {

    }

    public static void execute(String filename) {
        port = 9091;
        arguments = "--ignore-certificate-errors";
        nameHar = "test.har";
        description = "";

        try {
            beforeTest();
            test();
            afterTest();
        } catch (Exception e) {
            afterTest();
            Helper.showError(e);
        }
    }


}
