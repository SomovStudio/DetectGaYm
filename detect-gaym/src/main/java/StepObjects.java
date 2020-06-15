import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StepObjects {
    public static final String OPEN_PAGE = "open_page";
    public static final String OPEN_DEFAULT_PAGE = "open_default_page";
    public static final String REFRESH_PAGE = "refresh_page";
    public static final String INPUT_VALUE = "input_value";
    public static final String CLICK_ELEMENT = "click_element";
    public static final String WAIT_TEXT = "wait_text";
    public static final String WAIT_ELEMENT = "wait_element";
    public static final String SLEEP = "sleep";
    public static final String TEST_OPTIONALLY_GA = "test_optionally_ga";
    public static final String TEST_DEFAULTS_GA = "test_defaults_ga";
    public static final String TEST_OPTIONALLY_YM = "test_optionally_ym";
    public static final String TEST_DEFAULTS_YM = "test_defaults_ym";
    public static final String GET_HAR = "get_har";
    public static final String GET_HAR_GA = "get_har_ga";
    public static final String GET_HAR_YM = "get_har_ym";

    public static void openPage(String url, String title) {
        Helper.driver.get(url);
        if (!title.equals("")) System.out.println(title);
        else System.out.println("STEP: Open page " + url);
    }

    public static void refreshPage(String description) {
        Helper.driver.navigate().refresh();
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: refresh on page");
    }

    public static void inputValue(String locator, String value, String description, int timeout) throws InterruptedException {
        if(timeout > 0) Helper.waitElement(locator, timeout);
        WebElement element = Helper.driver.findElement(By.xpath(locator));
        element.sendKeys(value);
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: input in element [" + locator + "] - is value " + value);
    }

    public static void clickElement(String locator, String description, int timeout) throws InterruptedException {
        if(timeout > 0) Helper.waitElement(locator, timeout);
        WebElement element = Helper.driver.findElement(By.xpath(locator));
        element.click();
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: click on element [" + locator + "]");
    }

    public static void waitText(String locator, String value, String description, int timeout) throws InterruptedException {
        boolean result = Helper.waitTextInElement(locator, value, timeout);
        if(!result) {
            Helper.showFail("FAIL: not found element [" + locator + "]");
            return;
        }
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: text "+value+" in element [" + locator + "] - is displayed");
    }

    public static void waitElement(String locator, String description, int timeout) throws InterruptedException {
        boolean result = Helper.waitElement(locator, timeout);
        if(!result) {
            Helper.showFail("FAIL: not found element [" + locator + "]");
            return;
        }
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: element [" + locator + "] - is displayed");
    }

    public static void sleep(String description, int timeout) throws InterruptedException {
        long millis = timeout * 1000;
        Thread.sleep(millis);
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: sleep " + timeout + " seconds");
    }

    public static void testGA(String category, String action, String label, String description, int timeout) throws UnsupportedEncodingException, InterruptedException {
        boolean result = false;
        for (int time = -1; time < timeout; time++) // wait sec
        {
            Thread.sleep(1000);
            ArrayList<String> harLinks = Helper.getLinksFromHar();
            for (String link:harLinks) {
                if(link.contains("google-analytics.com/collect") && link.contains("&t=event&"))
                {
                    if(link.contains("ec="+category) && link.contains("ea="+action) && link.contains("el="+label)){
                        result = true;
                        break;
                    }
                }
            }
            if(result) break;
        }
        if(!result) {
            if (!description.equals("")) Helper.showFail(description + " - FAILED");
            else Helper.showFail("TEST: event GA ["+category+"]["+action+"]["+label+"] - FAILED");
        }else{
            if (!description.equals("")) Helper.showPass(description + " - PASSED");
            else Helper.showPass("event GA ["+category+"]["+action+"]["+label+"] - PASSED");
        }
    }

    public static void testOptionallyGA(String value, String locator, String description, int timeout) throws UnsupportedEncodingException, InterruptedException {
        if(locator == "") locator = "google-analytics.com/collect";
        boolean result = false;
        for (int time = -1; time < timeout; time++) // wait sec
        {
            Thread.sleep(1000);
            ArrayList<String> harLinks = Helper.getLinksFromHar();
            for (String link:harLinks) {
                if(link.contains(locator) && link.contains(value))
                {
                    result = true;
                    break;
                }
            }
            if(result) break;
        }
        if(!result) {
            if (!description.equals("")) Helper.showFail(description + " - FAILED");
            else Helper.showFail("TEST: event GA ["+value+"] - FAILED");
        }else{
            if (!description.equals("")) Helper.showPass(description + " - PASSED");
            else Helper.showPass("event GA ["+value+"] - PASSED");
        }
    }

    public static void testYM(String code, String description, int timeout) throws InterruptedException, UnsupportedEncodingException {
        boolean result = false;
        for (int time = -1; time < timeout; time++) // wait sec
        {
            Thread.sleep(1000);
            ArrayList<String> harLinks = Helper.getLinksFromHar();
            for (String link:harLinks)
            {
                if(link.contains("mc.yandex.ru/watch") && link.contains("&page-url=goal")){
                    result = link.contains(code);
                    if(result) break;
                }
            }
            if(result) break;
        }
        if(!result) {
            if (!description.equals("")) Helper.showFail(description + " - FAILED");
            else Helper.showFail("TEST: event YM ["+code+"] - FAILED");
        }else{
            if (!description.equals("")) Helper.showPass(description + " - PASSED");
            else Helper.showPass("event YM ["+code+"] - PASSED");
        }
    }

    public static void testOptionallyYM(String value, String locator, String description, int timeout) throws InterruptedException, UnsupportedEncodingException {
        if(locator == "") locator = "mc.yandex.ru/watch";
        boolean result = false;
        for (int time = -1; time < timeout; time++) // wait sec
        {
            Thread.sleep(1000);
            ArrayList<String> harLinks = Helper.getLinksFromHar();
            for (String link:harLinks)
            {
                if(link.contains(locator) && link.contains(value)){
                    result = true;
                    break;
                }
            }
            if(result) break;
        }
        if(!result) {
            if (!description.equals("")) Helper.showFail(description + " - FAILED");
            else Helper.showFail("TEST: event YM ["+value+"] - FAILED");
        }else{
            if (!description.equals("")) Helper.showPass(description + " - PASSED");
            else Helper.showPass("event YM ["+value+"] - PASSED");
        }
    }

    public static void getHar() throws UnsupportedEncodingException {
        ArrayList<String> harLinks = Helper.getLinksFromHar();
        for (String link:harLinks)
        {
            System.out.println("HAR: event " + link);
        }
    }

    public static void getHarGA() throws UnsupportedEncodingException {
        ArrayList<String> harLinks = Helper.getLinksFromHar();
        for (String link:harLinks)
        {
            if (link.contains("google-analytics.com/collect") && link.contains("&t=event&")) {
                System.out.println("HAR: event GA " + link);
            }
        }
    }

    public static void getHarYM() throws UnsupportedEncodingException {
        ArrayList<String> harLinks = Helper.getLinksFromHar();
        for (String link:harLinks)
        {
            if (link.contains("mc.yandex.ru/watch") && link.contains("&page-url=goal")) {
                System.out.println("HAR: event YM " + link);
            }
        }
    }
}
