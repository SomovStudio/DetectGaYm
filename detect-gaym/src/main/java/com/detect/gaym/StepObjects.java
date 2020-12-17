package com.detect.gaym;

import com.detect.gaym.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class StepObjects {
    public static final String OPEN_PAGE = "open_page";
    public static final String OPEN_DEFAULT_PAGE = "open_default_page";
    public static final String REFRESH_PAGE = "refresh_page";
    public static final String INPUT_VALUE = "input_value";
    public static final String CLICK_ELEMENT = "click_element";

    public static final String FIND_ELEMENT = "find_element";
    public static final String GET_TEXT = "get_text";
    public static final String GET_ATTRIBUTE = "get_attribute_";

    public static final String IF_EQUALLY = "==";
    public static final String IF_NOT_EQUALLY = "!=";
    public static final String IF_LESS = "<";
    public static final String IF_LESS_OR_EQUALLY = "<=";
    public static final String IF_MORE = ">";
    public static final String IF_MORE_OR_EQUALLY = ">=";
    public static final String IF_GET_TEXT = "if_get_text";
    public static final String IF_GET_ATTRIBUTE = "if_get_attribute_";
    public static final String ELSE_IF_GET_TEXT = "else_if_get_text";
    public static final String ELSE_IF_GET_ATTRIBUTE = "else_if_get_attribute_";
    public static final String ELSE = "else";
    public static final String END_IF = "end_if";

    public static final String WAIT_TEXT = "wait_text";
    public static final String WAIT_ELEMENT = "wait_element";
    public static final String WAIT_ELEMENT_NOT_VISIBLE = "wait_element_not_visible";
    public static final String SLEEP = "sleep";
    public static final String TEST_OPTIONALLY_GA = "test_optionally_ga";
    public static final String TEST_DEFAULTS_GA = "test_defaults_ga";
    public static final String TEST_OPTIONALLY_YM = "test_optionally_ym";
    public static final String TEST_DEFAULTS_YM = "test_defaults_ym";
    public static final String GET_HAR = "get_har";
    public static final String GET_HAR_GA = "get_har_ga";
    public static final String GET_HAR_YM = "get_har_ym";
    public static final String CLEAR_HAR = "clear_har";

    public static final String EXECUTE_JS = "execute_js";


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
        if(value.lastIndexOf(GET_ATTRIBUTE) > -1) value = getAttribute(value);
        if(value.equals(GET_TEXT)) value = getText();
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

    public static void waitText(String locator, String value, String description, int timeout) throws Exception {
        boolean result = Helper.waitTextInElement(locator, value, timeout);
        if(!result) {
            Helper.showFail("FAIL: not found element [" + locator + "]");
            return;
        }
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: text "+value+" in element [" + locator + "] - is displayed");
    }

    public static void waitElement(String locator, String description, int timeout) throws Exception {
        boolean result = Helper.waitElement(locator, timeout);
        if(!result) {
            Helper.showFail("FAIL: not found element [" + locator + "]");
            return;
        }
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: element [" + locator + "] - is displayed");
    }

    public static void waitElementNotVisible(String locator, String description, int timeout) throws Exception {
        boolean result = Helper.waitElement(locator, timeout);
        if(!result){
            if (!description.equals("")) System.out.println(description);
            else System.out.println("STEP: element [" + locator + "] - is not displayed");
        }else{
            Helper.showFail("FAIL: element is displayed [" + locator + "]");
            return;
        }
    }

    public static void sleep(String description, int timeout) throws InterruptedException {
        long millis = timeout * 1000;
        Thread.sleep(millis);
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: sleep " + timeout + " seconds");
    }

    public static void testGA(String category, String action, String label, String description, int timeout) throws Exception {
        boolean result = false;
        boolean protocol = false;
        for (int time = -1; time < timeout; time++) // wait sec
        {
            Thread.sleep(1000);
            ArrayList<String> harLinks = Helper.getLinksFromHar();
            for (String link:harLinks) {
                if(link.contains("google-analytics.com/collect")) protocol = true;
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
            if(protocol == false && !description.equals("")) Helper.showTestFail(description + " - FAILED (protocol [google-analytics.com/collect] not found)", GET_HAR_GA);
            else if(protocol == false && description.equals("")) Helper.showTestFail("TEST: event GA ["+category+"]["+action+"]["+label+"] - FAILED (protocol [google-analytics.com/collect] not found", GET_HAR_GA);
            else if(protocol == true && !description.equals("")) Helper.showTestFail(description + " - FAILED", GET_HAR_GA);
            else Helper.showTestFail("TEST: event GA ["+category+"]["+action+"]["+label+"] - FAILED", GET_HAR_GA);
        }else{
            if (!description.equals("")) Helper.showPass(description + " - PASSED");
            else Helper.showPass("event GA ["+category+"]["+action+"]["+label+"] - PASSED");
        }
    }

    public static void testOptionallyGA(String value, String locator, String description, int timeout) throws Exception {
        if(locator == "") locator = "google-analytics.com/collect";
        boolean result = false;
        boolean protocol = false;
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
            if(protocol == false && !description.equals("")) Helper.showTestFail(description + " - FAILED (protocol [google-analytics.com/collect] not found)", GET_HAR_GA);
            else if(protocol == false && description.equals("")) Helper.showTestFail("TEST: event GA ["+value+"] - FAILED (protocol [google-analytics.com/collect] not found)", GET_HAR_GA);
            else if(protocol == true && !description.equals("")) Helper.showTestFail(description + " - FAILED", GET_HAR_GA);
            else Helper.showTestFail("TEST: event GA ["+value+"] - FAILED", GET_HAR_GA);
        }else{
            if (!description.equals("")) Helper.showPass(description + " - PASSED");
            else Helper.showPass("event GA ["+value+"] - PASSED");
        }
    }

    public static void testYM(String code, String description, int timeout) throws Exception {
        boolean result = false;
        boolean protocol = false;
        for (int time = -1; time < timeout; time++) // wait sec
        {
            Thread.sleep(1000);
            ArrayList<String> harLinks = Helper.getLinksFromHar();
            for (String link:harLinks)
            {
                if(link.contains("mc.yandex.ru/watch")) protocol = true;
                if(link.contains("mc.yandex.ru/watch") && link.contains("&page-url=goal")){
                    result = link.contains(code);
                    if(result) break;
                }
            }
            if(result) break;
        }
        if(!result) {
            if(protocol == false && !description.equals("")) Helper.showTestFail(description + " - FAILED (protocol [mc.yandex.ru/watch] not found)", GET_HAR_YM);
            else if(protocol == false && description.equals("")) Helper.showTestFail("TEST: event YM ["+code+"] - FAILED (protocol [mc.yandex.ru/watch] not found)", GET_HAR_YM);
            else if(protocol == true && !description.equals("")) Helper.showTestFail(description + " - FAILED", GET_HAR_YM);
            else Helper.showTestFail("TEST: event YM ["+code+"] - FAILED", GET_HAR_YM);
        }else{
            if (!description.equals("")) Helper.showPass(description + " - PASSED");
            else Helper.showPass("event YM ["+code+"] - PASSED");
        }
    }

    public static void testOptionallyYM(String value, String locator, String description, int timeout) throws Exception {
        if(locator == "") locator = "mc.yandex.ru/watch";
        boolean result = false;
        boolean protocol = false;
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
            if(protocol == false && !description.equals("")) Helper.showTestFail(description + " - FAILED (protocol [mc.yandex.ru/watch] not found)", GET_HAR_YM);
            else if(protocol == false && description.equals("")) Helper.showTestFail("TEST: event YM ["+value+"] - FAILED (protocol [mc.yandex.ru/watch] not found)", GET_HAR_YM);
            else if(protocol == true && !description.equals("")) Helper.showTestFail(description + " - FAILED", GET_HAR_YM);
            else Helper.showTestFail("TEST: event YM ["+value+"] - FAILED", GET_HAR_YM);
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

    public static void findElement(String locator, String description, int timeout) throws InterruptedException {
        try {
            if(timeout > 0) Helper.waitElement(locator, timeout);
            Helper.element = Helper.driver.findElement(By.xpath(locator));
            if (!description.equals("")) System.out.println(description);
            else System.out.println("STEP: search of element [" + locator + "] - found");
        } catch (Exception e) {
            Helper.element = null;
            System.out.println("WARNING: search of element [" + locator + "] - is not found");
        }
    }

    public static String getText() {
        if(Helper.element == null) return "null";
        return Helper.element.getText();
    }

    public static String getAttribute(String value) {
        if(Helper.element == null) return "null";
        String attributeName = "";
        if (value.lastIndexOf(GET_ATTRIBUTE) > -1) attributeName = value.substring(GET_ATTRIBUTE.length());
        if (value.lastIndexOf(IF_GET_ATTRIBUTE) > -1) attributeName = value.substring(IF_GET_ATTRIBUTE.length());
        if (value.lastIndexOf(ELSE_IF_GET_ATTRIBUTE) > -1) attributeName = value.substring(ELSE_IF_GET_ATTRIBUTE.length());
        return Helper.element.getAttribute(attributeName);
    }

    public static boolean ifGetText(String locator, String value, String description, int timeout) {
        if(Helper.element == null) {
            if (!description.equals("")) System.out.println(description);
            else System.out.println("STEP: checking the condition between [null " + locator + " " + value + "]");
            return false;
        }
        String elementText = Helper.element.getText();
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: checking the condition between [" + elementText + " " + locator + " " + value + "]");
        if (locator.equals(IF_EQUALLY) && elementText.equals(value)) return true;
        if (locator.equals(IF_NOT_EQUALLY) && !elementText.equals(value)) return true;
        if (locator.equals(IF_LESS) && Float.parseFloat(elementText) < Float.parseFloat(value)) return true;
        if (locator.equals(IF_LESS_OR_EQUALLY) && Float.parseFloat(elementText) <= Float.parseFloat(value)) return true;
        if (locator.equals(IF_MORE) && Float.parseFloat(elementText) > Float.parseFloat(value)) return true;
        if (locator.equals(IF_MORE_OR_EQUALLY) && Float.parseFloat(elementText) >= Float.parseFloat(value)) return true;
        return false;
    }

    public static boolean ifAttribute(String type, String locator, String value, String description, int timeout) {
        if(Helper.element == null) {
            if (!description.equals("")) System.out.println(description);
            else System.out.println("STEP: checking the condition between [null " + locator + " " + value + "]");
            return false;
        }
        String elementAttribute = getAttribute(type);
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: checking the condition between [" + elementAttribute + " " + locator + " " + value + "]");
        if (locator.equals(IF_EQUALLY) && elementAttribute.equals(value)) return true;
        if (locator.equals(IF_NOT_EQUALLY) && !elementAttribute.equals(value)) return true;
        if (locator.equals(IF_LESS) && Float.parseFloat(elementAttribute) < Float.parseFloat(value)) return true;
        if (locator.equals(IF_LESS_OR_EQUALLY) && Float.parseFloat(elementAttribute) <= Float.parseFloat(value)) return true;
        if (locator.equals(IF_MORE) && Float.parseFloat(elementAttribute) > Float.parseFloat(value)) return true;
        if (locator.equals(IF_MORE_OR_EQUALLY) && Float.parseFloat(elementAttribute) >= Float.parseFloat(value)) return true;
        return false;
    }

    public static void stepElse(String description) {
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: condition else");
    }

    public static void stepEndIf(String description) {
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: condition end");
    }

    public static void executeJS(String value, String description) {
        JavascriptExecutor js = (JavascriptExecutor) Helper.driver;
        js.executeScript(value);
        if (!description.equals("")) System.out.println(description);
        else System.out.println("STEP: Execute javascript code [" + value + "]");
    }
}
