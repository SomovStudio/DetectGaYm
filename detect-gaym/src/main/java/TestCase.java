import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;

public class TestCase {
    public static String description;
    public static int port;
    public static String arguments;
    public static String nameHar;
    //public static ArrayList<String> data;
    //public static ArrayList<String> steps;

    private static void beforeTest() {
        Helper.initProxyForChromeDriver(port, arguments, nameHar);
    }

    private static void afterTest() {
        Helper.endWorkProxy();
    }

    private static void test() {

    }

    public static void execute(String filename) throws IOException, ParseException {
        JSONObject oj = Helper.readJsonFile(filename);

        description = oj.get("description").toString();
        port = Integer.parseInt(oj.get("port").toString());
        arguments = oj.get("arguments").toString();
        nameHar = oj.get("har").toString();

        System.out.println(description);
        System.out.println(port);
        System.out.println(arguments);
        System.out.println(nameHar);

        JSONArray data = (JSONArray) oj.get("data");
        JSONArray steps = (JSONArray) oj.get("steps");

        /*
        try {
            beforeTest();
            test();
            afterTest();
        } catch (Exception e) {
            afterTest();
            Helper.showError(e);
        }
         */
    }


}
