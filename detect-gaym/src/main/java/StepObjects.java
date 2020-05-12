import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StepObjects {
    public static final String OPEN_PAGE = "open_page";
    public static final String INPUT_VALUE = "input_value";
    public static final String CLICK_ELEMENT = "click_element";
    public static final String WAIT_TEXT = "wait_text";
    public static final String WAIT_ELEMENT = "wait_element";
    public static final String SLEEP = "sleep";
    public static final String TEST_GA = "test_ga";
    public static final String TEST_YM = "test_ym";

    public static void openPage(String url, String description) {
        Helper.driver.get(url);
        if (!description.equals("")) System.out.println("STEP: " + description);
        else System.out.println("STEP: Open page " + url);
    }

    public static void inputValue(String locator, String value, String description) {
        WebElement element = Helper.driver.findElement(By.xpath(locator));
        element.sendKeys(value);
        if (!description.equals("")) System.out.println("STEP: " + description);
        else System.out.println("STEP: input in element [" + locator + "] is value " + value);
    }

    public static void click(String locator, String description) {
        WebElement element = Helper.driver.findElement(By.xpath(locator));
        element.click();
        if (!description.equals("")) System.out.println("STEP: " + description);
        else System.out.println("STEP: click on element [" + locator + "]");
    }

    public static void waitText(String locator, String value, String description, int timeout) throws InterruptedException {
        boolean result = Helper.waitTextInElement(locator, value, timeout);
        if(!result) {
            Helper.showFail("FAIL: not found element [" + locator + "]");
            return;
        }
        if (!description.equals("")) System.out.println("STEP: " + description);
        else System.out.println("STEP: text "+value+" in element [" + locator + "] is displayed");
    }

}
