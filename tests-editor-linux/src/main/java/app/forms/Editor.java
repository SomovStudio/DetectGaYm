package app.forms;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

public class Editor {
    /*
     *   http://java-online.ru/swing-jfilechooser.xhtml
     *   https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
     * */
    public static void dialogOpenFile() {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
    }

    public static JSONObject readJsonFile(String filename) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(filename));
        return (JSONObject) obj;
    }
}
