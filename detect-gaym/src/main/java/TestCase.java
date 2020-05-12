import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

public class TestCase {
    public static String description;
    public static int port;
    public static String arguments;
    public static String nameHar;
    public static JSONArray data;
    public static JSONArray steps;

    private static void beforeTest() {
        Helper.initProxyForChromeDriver(port, arguments, nameHar);
    }

    private static void afterTest() {
        Helper.endWorkProxy();
    }

    private static void test() throws InterruptedException, UnsupportedEncodingException {
        Helper.showTitle(description);
        Iterator dataItr = data.iterator();
        while (dataItr.hasNext()) {
            JSONObject dataObj = (JSONObject) dataItr.next();
            String title = dataObj.get("title").toString();
            String url = dataObj.get("url").toString();
            String ga_category = dataObj.get("ga_category").toString();
            String ga_action = dataObj.get("ga_action").toString();
            String ga_label = dataObj.get("ga_label").toString();
            String ym_code = dataObj.get("ym_code").toString();

            StepObjects.openPage(url, title);

            Iterator stepsItr = steps.iterator();
            while (stepsItr.hasNext()) {
                JSONObject stepObj = (JSONObject) stepsItr.next();
                String desc = stepObj.get("description").toString();
                String type = stepObj.get("type").toString();
                String value = stepObj.get("value").toString();
                String locator = stepObj.get("locator").toString();
                int timeout = Integer.parseInt(stepObj.get("timeout").toString());

                if (type.equals(StepObjects.OPEN_PAGE)) StepObjects.openPage(value, desc);
                if (type.equals(StepObjects.INPUT_VALUE)) StepObjects.inputValue(locator, value, desc);
                if (type.equals(StepObjects.CLICK_ELEMENT)) StepObjects.clickElement(locator, desc);
                if (type.equals(StepObjects.WAIT_TEXT)) StepObjects.waitText(locator, value, desc, timeout);
                if (type.equals(StepObjects.WAIT_ELEMENT)) StepObjects.waitElement(locator, desc, timeout);
                if (type.equals(StepObjects.SLEEP)) StepObjects.sleep(desc, timeout);
                if (type.equals(StepObjects.TEST_GA)) {
                    ArrayList<String> harLinks = Helper.getLinksFromHar();
                    StepObjects.testGA(harLinks, ga_category, ga_action, ga_label, desc);
                }
                if (type.equals(StepObjects.TEST_YM)) {
                    ArrayList<String> harLinks = Helper.getLinksFromHar();
                    StepObjects.testYM(harLinks, ym_code, desc);
                }

                Thread.sleep(250);
            }
        }
    }

    public static void execute(String filename) throws IOException, ParseException, InterruptedException {
        JSONObject oj = Helper.readJsonFile(filename);

        description = oj.get("description").toString();
        port = Integer.parseInt(oj.get("port").toString());
        arguments = oj.get("arguments").toString();
        nameHar = oj.get("har").toString();
        data = (JSONArray) oj.get("data");
        steps = (JSONArray) oj.get("steps");

        try {
            beforeTest();
            test();
            afterTest();
        } catch (Exception e) {
            Helper.showError(e);
        }
    }
}
