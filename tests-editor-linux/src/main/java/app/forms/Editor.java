package app.forms;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Iterator;

public class Editor {
    public static final String DEFAULT = "default";
    public static final String UTF_8 = "utf_8";
    public static final String UTF_8_BOM = "utf_8_bom";
    public static final String WINDOWS_1251 = "windows_1251";

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

    /* Чтение файлов в соответствии с выбранной кодировкой */
    public static JSONObject readFileInEncoding(String filename, String encoding) throws IOException, ParseException {
        if(encoding.equals(DEFAULT)) {
            FileReader reader = new FileReader(filename);
            Object obj = new JSONParser().parse(reader);
            return (JSONObject) obj;
        }
        if(encoding.equals(UTF_8)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            Object obj = new JSONParser().parse(reader);
            return (JSONObject) obj;
        }
        if(encoding.equals(UTF_8_BOM)){

        }
        if(encoding.equals(WINDOWS_1251)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "windows-1251"));
            Object obj = new JSONParser().parse(reader);
            return (JSONObject) obj;
        }
        return null;
    }

    public static void saveFileInEncoding(String filename, String encoding) throws IOException {
        if(encoding.equals(DEFAULT)){

        }
        if(encoding.equals(UTF_8)){
            BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output_file.txt"), "UTF-8"));
            w.write("");
            w.flush();
            w.close();
        }
    }

    /* Чтение json файла */
    public static void readJsonFile(String filename, String encoding) throws IOException, ParseException {
        JSONObject oj = readFileInEncoding(filename, encoding);
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
    }


}
