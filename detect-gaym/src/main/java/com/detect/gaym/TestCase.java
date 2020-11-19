package com.detect.gaym;

import com.detect.gaym.Helper;
import com.detect.gaym.StepObjects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCase {
    public static String description;
    public static int port;
    public static ArrayList<String> arguments;
    public static String nameHar;
    public static int pageLoadTimeout;
    public static JSONArray data;
    public static JSONArray steps;

    public static boolean conditionStatus;
    public static boolean conditionResult;
    public static boolean conditionCompleted;


    private static void beforeTest() {
        Helper.initProxyForChromeDriver(port, arguments, nameHar);
    }

    private static void afterTest() {
        Helper.endWorkProxy();
    }

    private static void test() throws Exception {
        Helper.showTitle(description);
        if(pageLoadTimeout > 0) {
            Helper.driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            System.out.println("SETTING: Page load time limit " + pageLoadTimeout + " seconds");
        }

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

                if (conditionStatus == true && conditionResult == true && (type.equals(StepObjects.IF_GET_TEXT) || type.equals(StepObjects.ELSE_IF_GET_TEXT) ||
                        type.lastIndexOf(StepObjects.IF_GET_ATTRIBUTE) > -1 || type.lastIndexOf(StepObjects.ELSE_IF_GET_ATTRIBUTE) > -1 ||
                        type.equals(StepObjects.ELSE)))
                {
                    conditionCompleted = true;
                }
                if(conditionCompleted == false) {
                    if (type.equals(StepObjects.IF_GET_TEXT) || type.equals(StepObjects.ELSE_IF_GET_TEXT))
                    {
                        conditionStatus = true;
                        conditionResult = StepObjects.ifGetText(locator, value, desc, timeout);
                    }
                    else if (type.lastIndexOf(StepObjects.IF_GET_ATTRIBUTE) > -1 || type.lastIndexOf(StepObjects.ELSE_IF_GET_ATTRIBUTE) > -1)
                    {
                        conditionStatus = true;
                        conditionResult = StepObjects.ifAttribute(type, locator, value, desc, timeout);
                    }
                    else if (type.equals(StepObjects.ELSE))
                    {
                        conditionStatus = true;
                        conditionResult = true;
                    }
                }
                if (type.equals(StepObjects.END_IF)){
                    conditionStatus = false;
                    conditionResult = false;
                    conditionCompleted = false;
                }
                //System.out.println("CONDITION STATUS: " + conditionStatus + " | RESULT: " + conditionResult + " | COMPTATE: " + conditionCompleted);
                if (conditionStatus == true && conditionResult == false && conditionCompleted == false) continue;
                if (conditionCompleted == true) continue;


                if (type.equals(StepObjects.OPEN_PAGE)) StepObjects.openPage(value, desc);
                if (type.equals(StepObjects.OPEN_DEFAULT_PAGE)) StepObjects.openPage(url, desc);
                if (type.equals(StepObjects.REFRESH_PAGE)) StepObjects.refreshPage(desc);
                if (type.equals(StepObjects.INPUT_VALUE)) StepObjects.inputValue(locator, value, desc, timeout);
                if (type.equals(StepObjects.CLICK_ELEMENT)) StepObjects.clickElement(locator, desc, timeout);
                if (type.equals(StepObjects.FIND_ELEMENT)) StepObjects.findElement(locator, desc, timeout);
                if (type.equals(StepObjects.WAIT_TEXT)) StepObjects.waitText(locator, value, desc, timeout);
                if (type.equals(StepObjects.WAIT_ELEMENT)) StepObjects.waitElement(locator, desc, timeout);
                if (type.equals(StepObjects.WAIT_ELEMENT_NOT_VISIBLE)) StepObjects.waitElementNotVisible(locator, desc, timeout);
                if (type.equals(StepObjects.SLEEP)) StepObjects.sleep(desc, timeout);
                if (type.equals(StepObjects.GET_HAR)) StepObjects.getHar();
                if (type.equals(StepObjects.GET_HAR_GA)) StepObjects.getHarGA();
                if (type.equals(StepObjects.GET_HAR_YM)) StepObjects.getHarYM();
                if (type.equals(StepObjects.CLEAR_HAR)) Helper.clearHar();
                if (type.equals(StepObjects.TEST_DEFAULTS_GA)) StepObjects.testGA(ga_category, ga_action, ga_label, desc, timeout);
                if (type.equals(StepObjects.TEST_DEFAULTS_YM)) StepObjects.testYM(ym_code, desc, timeout);
                if (type.equals(StepObjects.TEST_OPTIONALLY_GA)) StepObjects.testOptionallyGA(value, locator, desc, timeout);
                if (type.equals(StepObjects.TEST_OPTIONALLY_YM)) StepObjects.testOptionallyYM(value, locator, desc, timeout);

                Thread.sleep(250);
            }
        }
    }

    public static void execute(String filename, String encoding) throws Exception {
        try {
            conditionStatus = false;
            conditionResult = false;
            conditionCompleted = false;
            System.out.println("LOAD: test-case file " + filename);
            //JSONObject oj = Helper.readJsonFile(filename);
            JSONObject oj = Helper.readJsonFileInEncoding(filename, encoding);

            description = oj.get("description").toString();
            port = Integer.parseInt(oj.get("port").toString());
            nameHar = oj.get("har").toString();
            pageLoadTimeout = Integer.parseInt(oj.get("timeout").toString());
            data = (JSONArray) oj.get("data");
            steps = (JSONArray) oj.get("steps");

            arguments = new ArrayList<String>();
            JSONArray args = (JSONArray) oj.get("arguments");
            Iterator argsItr = args.iterator();
            while (argsItr.hasNext()) {
                String argument = argsItr.next().toString();
                arguments.add(argument);
            }
        } catch (Exception e) {
            Helper.showError(e);
        }

        try {
            beforeTest();
            test();
            afterTest();

            System.out.println("TESTING: finished - SUCCESS");
        } catch (Exception e) {
            Helper.showError(e);
        }
    }

    public static void executeAll(String folder, String encoding) throws Exception {
        try {
            conditionStatus = false;
            conditionResult = false;
            conditionCompleted = false;
            if(Files.exists(Paths.get(folder))){
                System.out.println("FOLDER: test-cases files " + folder);
            }else{
                String path = System.getProperty("user.dir");
                path = path.substring(0, path.length() - 4);
                folder = path + folder;
                System.out.println("FOLDER: test-cases files " + folder);
            }
            File dir = new File(folder);
            File[] files = dir.listFiles();
            List<File> listFiles = Arrays.asList(files != null ? files : new File[0]);
            for(File file : listFiles){
                String filename = file.getPath();

                System.out.println("LOAD: test-case file " + filename);
                //JSONObject oj = Helper.readJsonFile2(filename);
                JSONObject oj = Helper.readJsonFileInEncoding(filename, encoding);

                description = oj.get("description").toString();
                port = Integer.parseInt(oj.get("port").toString());
                nameHar = oj.get("har").toString();
                pageLoadTimeout = Integer.parseInt(oj.get("timeout").toString());
                data = (JSONArray) oj.get("data");
                steps = (JSONArray) oj.get("steps");

                arguments = new ArrayList<String>();
                JSONArray args = (JSONArray) oj.get("arguments");
                Iterator argsItr = args.iterator();
                while (argsItr.hasNext()) {
                    String argument = argsItr.next().toString();
                    arguments.add(argument);
                }
                beforeTest();
                test();
                afterTest();
            }

            System.out.println("TESTING: finished - SUCCESS");
        } catch (Exception e) {
            Helper.showError(e);
        }
    }
}
