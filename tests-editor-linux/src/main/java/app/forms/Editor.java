package app.forms;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Editor {
    public static String description;
    public static int port;
    public static ArrayList<String> arguments;
    public static String nameHar;
    public static int pageLoadTimeout;
    public static JSONArray data;
    public static JSONArray steps;

    /* Диалог с текстом */
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /* Диалог открытия файла */
    public static String dialogOpenFile(java.awt.Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); //user.home
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.getName().endsWith(".json") || file.isDirectory()) {
                    return true;
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "JSON file";
            }
        });
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }else{
            return "...";
        }
    }

    /* Чтение json файла */
    public static JSONObject readJsonFile(String filename) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(filename));
        JSONObject oj = (JSONObject) obj;
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

        return (JSONObject) obj;
    }


}
