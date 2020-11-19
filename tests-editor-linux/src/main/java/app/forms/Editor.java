package app.forms;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Editor {
    public static final String TYPE_OS_WINDOWS = "type_os_windows";
    public static final String TYPE_OS_LINUX = "type_os_linux";

    public static final String DEFAULT = "default";
    public static final String UTF_8 = "utf-8";
    public static final String UTF_8_BOM = "utf-8-bom";
    public static final String WINDOWS_1251 = "windows-1251";

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

    /* Путь к папке программы */
    public static String getProgramFolder(){
        String path = FormMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String folder = new File(path).getParent();
        return folder;
    }

    /* Путь к папке для выбранного файла */
    public static String getFileFolder(String filename) {
        String folder = new File(filename).getParent();
        return folder;
    }

    /* Диалог открытия файла */
    public static String dialogOpenFile(java.awt.Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); //user.home
        fileChooser.setCurrentDirectory(new File(getProgramFolder()));
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

    /* Диалог сохранения файлов */
    public static String dialogSaveFile(java.awt.Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        //fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); //user.home
        fileChooser.setCurrentDirectory(new File(getProgramFolder()));
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
        int result = fileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filename = selectedFile.getName();
            String filetype = filename.substring(filename.length() - 4);
            if(filetype.equals("json")){
                return selectedFile.getAbsolutePath();
            }else{
                return selectedFile.getAbsolutePath()+".json";
            }
        }else{
            return "...";
        }
    }

    /* Чтение файлов в соответствии с выбранной кодировкой */
    public static JSONObject readFileInEncoding(String filename, String encoding) throws IOException, ParseException {
        if(encoding.equals(DEFAULT)) {
            FileReader reader = new FileReader(filename);
            Object obj = new JSONParser().parse(reader);
            reader.close();
            return (JSONObject) obj;
        }
        if(encoding.equals(UTF_8)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            Object obj = new JSONParser().parse(reader);
            reader.close();
            return (JSONObject) obj;
        }
        if(encoding.equals(UTF_8_BOM)){
            //BufferedReader br = new BufferedReader(new InputStreamReader(new BOMInputStream( new FileInputStream(filename), false,
            //                ByteOrderMark.UTF_8, ByteOrderMark.UTF_16BE, ByteOrderMark.UTF_16LE,
            //                ByteOrderMark.UTF_32BE, ByteOrderMark.UTF_32LE ) ) );
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            Object obj = new JSONParser().parse(reader);
            reader.close();
            return (JSONObject) obj;
        }
        if(encoding.equals(WINDOWS_1251)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "windows-1251"));
            Object obj = new JSONParser().parse(reader);
            reader.close();
            return (JSONObject) obj;
        }
        return null;
    }

    public static void saveFileInEncoding(String filename, String encoding, String context) throws IOException {
        if(encoding.equals(DEFAULT)){
            FileWriter writer = new FileWriter(filename);
            writer.write(context);
            writer.flush();
            writer.close();
        }
        if(encoding.equals(UTF_8)){
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            writer.write(context);
            writer.flush();
            writer.close();
        }
        if(encoding.equals(WINDOWS_1251)){
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "windows-1251"));
            writer.write(context);
            writer.flush();
            writer.close();
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

    /* Запись json файла */
    public static void saveJsonFile(String filename, String encoding, String[] fields, JList listOptions, JTable tableData, JTable tableSteps) throws IOException, ParseException {
        String json = "{";
        json += System.getProperty("line.separator") + "\"description\":\""+ fields[0] +"\",";
        json += System.getProperty("line.separator") + "\"port\":"+ fields[1] +",";
        json += System.getProperty("line.separator") + "\"arguments\":[";
        int count = listOptions.getModel().getSize();
        for(int i = 0; i < count; i++) {
            String value = listOptions.getModel().getElementAt(i).toString();
            if(i != count - 1) json += System.getProperty("line.separator") + "\"" + value + "\",";
            else json += System.getProperty("line.separator") + "\"" + value + "\"";
        }
        json += System.getProperty("line.separator") + "],";
        json += System.getProperty("line.separator") + "\"har\":\"" + fields[2] + "\",";
        json += System.getProperty("line.separator") + "\"timeout\":" + fields[3]  + ",";
        json += System.getProperty("line.separator") + "\"data\":[";
        if(tableData.getModel().getColumnCount() > -1){
            int countColumnsInTableData = tableData.getModel().getColumnCount();
            int iTitle = 0;
            int iUrl = 0;
            int iGaCategory = 0;
            int iGaAction = 0;
            int iGaLabel = 0;
            int iYmCode = 0;
            for(int j = 0; j < countColumnsInTableData; j++){
                if(tableData.getModel().getColumnName(j).equals("Заголовок")) iTitle = j;
                if(tableData.getModel().getColumnName(j).equals("URL")) iUrl = j;
                if(tableData.getModel().getColumnName(j).equals("GA:category")) iGaCategory = j;
                if(tableData.getModel().getColumnName(j).equals("GA:action")) iGaAction = j;
                if(tableData.getModel().getColumnName(j).equals("GA:label")) iGaLabel = j;
                if(tableData.getModel().getColumnName(j).equals("YM:code")) iYmCode = j;
            }
            int countRowsInTableData = tableData.getModel().getRowCount();
            for(int k = 0; k < countRowsInTableData; k++){
                String title = tableData.getModel().getValueAt(k, iTitle).toString();
                String url = tableData.getModel().getValueAt(k, iUrl).toString();
                String gaCategory = tableData.getModel().getValueAt(k, iGaCategory).toString();
                String gaAction = tableData.getModel().getValueAt(k, iGaAction).toString();
                String gaLabel = tableData.getModel().getValueAt(k, iGaLabel).toString();
                String ymCode = tableData.getModel().getValueAt(k, iYmCode).toString();
                json += System.getProperty("line.separator") + "{";
                json += System.getProperty("line.separator") + "\"title\":\"" + title + "\",";
                json += System.getProperty("line.separator") + "\"url\":\"" + url + "\",";
                json += System.getProperty("line.separator") + "\"ga_category\":\"" + gaCategory + "\",";
                json += System.getProperty("line.separator") + "\"ga_action\":\"" + gaAction + "\",";
                json += System.getProperty("line.separator") + "\"ga_label\":\"" + gaLabel + "\",";
                json += System.getProperty("line.separator") + "\"ym_code\":\"" + ymCode + "\"";
                if (k != countRowsInTableData - 1) json += System.getProperty("line.separator") + "},";
                else json += System.getProperty("line.separator") + "}";
            }
        }
        json += System.getProperty("line.separator") + "],";
        json += System.getProperty("line.separator") + "\"steps\":[";
        if(tableSteps.getModel().getColumnCount() > -1){
            int countColumnsInTableSteps = tableSteps.getModel().getColumnCount();
            int iDescription = 0;
            int iType = 0;
            int iLocator = 0;
            int iValue = 0;
            int iTimeout = 0;
            for(int m = 0; m < countColumnsInTableSteps; m++){
                if(tableSteps.getModel().getColumnName(m).equals("Описание")) iDescription = m;
                if(tableSteps.getModel().getColumnName(m).equals("Тип действия")) iType = m;
                if(tableSteps.getModel().getColumnName(m).equals("Локатор / Протокол / Условие")) iLocator = m;
                if(tableSteps.getModel().getColumnName(m).equals("Значение")) iValue = m;
                if(tableSteps.getModel().getColumnName(m).equals("Время ожидания")) iTimeout = m;
            }
            int countRowsInTableSteps = tableSteps.getModel().getRowCount();
            for(int n = 0; n < countRowsInTableSteps; n++){
                String description = tableSteps.getModel().getValueAt(n, iDescription).toString();
                String type = tableSteps.getModel().getValueAt(n, iType).toString();
                String locator = tableSteps.getModel().getValueAt(n, iLocator).toString();
                String value = tableSteps.getModel().getValueAt(n, iValue).toString();
                String timeout = tableSteps.getModel().getValueAt(n, iTimeout).toString();
                json += System.getProperty("line.separator") + "{";
                json += System.getProperty("line.separator") + "\"description\":\"" + description + "\",";
                json += System.getProperty("line.separator") + "\"type\":\"" + type + "\",";
                json += System.getProperty("line.separator") + "\"locator\":\"" + locator + "\",";
                json += System.getProperty("line.separator") + "\"value\":\"" + value + "\",";
                json += System.getProperty("line.separator") + "\"timeout\":" + timeout;
                if (n != countRowsInTableSteps - 1) json += System.getProperty("line.separator") + "},";
                else json += System.getProperty("line.separator") + "}";
            }
        }
        json += System.getProperty("line.separator") + "]";
        json += System.getProperty("line.separator") + "}";
        saveFileInEncoding(filename, encoding, json);
        showMessage("Файл сохранен!");
    }

    /* Консоле Linux */
    public static Process executeConsoleLinux(String filename) throws IOException {
        String folderBin= getProgramFolder();
        folderBin = folderBin.substring(0, folderBin.length() - "editor".length());
        folderBin = folderBin + "bin";

        File file = new File(folderBin+"/run-test.sh");
        file.createNewFile();

        String context = "#!/bin/bash";
        context += System.getProperty("line.separator") + "cd " + folderBin;
        context += System.getProperty("line.separator") + "java -jar detect-gaym.jar "+filename;

        FileWriter writer = new FileWriter(file);
        writer.write(context);
        writer.flush();
        writer.close();

        ProcessBuilder pb = new ProcessBuilder(file.getAbsolutePath());
        Process p = pb.start();
        return p;
    }

    /* Консоль Windows */
    public static Process executeConsoleWindows(String filename) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "start", "dir", "cd..", "java -jar detect-gaym.jar "+filename);
        Process p = pb.start();
        return p;
    }

    /* Выполнение файла автотеста */
    public static void executeFile(String filename, JPanel panel, JTextArea console) throws IOException {
        Process p = null;
        String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf("win") >= 0) p = executeConsoleWindows(filename);
        if(os.indexOf("linux") >= 0) p = executeConsoleLinux(filename);

        String messages = "";
        int countMessages = 0;
        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = reader.readLine()) != null)
        {
            messages += line + System.getProperty("line.separator");
            console.append(line + System.getProperty("line.separator"));
            console.setCaretPosition(console.getText().length() - 1);
            console.update(console.getGraphics());
            console.validate();
            if(countMessages > 10) {
                console.setText("");
                countMessages = 0;
            }else{
                countMessages++;
            }
            panel.update(panel.getGraphics());
            panel.validate();
            System.out.println(line);
        }
        console.setText(messages);
    }

    /* Выполнить группу автотестов */
    public static void executeGroup(String folder, JPanel panel, JTextArea console) throws IOException {
        Process p = null;
        String os = System.getProperty("os.name").toLowerCase();
        if(os.indexOf("win") >= 0) p = executeConsoleWindows(folder);
        if(os.indexOf("linux") >= 0) p = executeConsoleLinux(folder);

        String messages = "";
        int countMessages = 0;
        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while ((line = reader.readLine()) != null)
        {
            messages += line + System.getProperty("line.separator");
            console.append(line + System.getProperty("line.separator"));
            console.setCaretPosition(console.getText().length() - 1);
            console.update(console.getGraphics());
            console.validate();
            if(countMessages > 10) {
                console.setText("");
                countMessages = 0;
            }else{
                countMessages++;
            }
            panel.update(panel.getGraphics());
            panel.validate();
            System.out.println(line);
        }
        console.setText(messages);
    }

    /* Валидатор Json */
    public static void validatorJson(String filename) throws FileNotFoundException {
        FileReader reader = new FileReader(filename);
        try {
            Object obj = new JSONParser().parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ParseException e) {
            showMessage("ОШИБКА: Нарущена структура json файла.\n"+e.toString());
            e.printStackTrace();
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        showMessage("Файл: " + filename+ "\nПроверка структуры json файла "+" - успешно!");
    }
}
