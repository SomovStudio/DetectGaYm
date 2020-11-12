package app.forms;

import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import static app.forms.Editor.*;

public class FormMain {
    private JPanel PanelMain;
    private JMenuBar MenuBar;
    private JMenu MenuFile;
    private JMenu MenuExecute;
    private JMenu MenuHelp;
    private JMenu MenuReference;
    private JMenuItem MenuCreateNewTest;
    private JMenuItem MenuOpenTestFile;
    private JMenuItem MenuExecuteTest;
    private JMenuItem MenuExecuteGroupTest;
    private JMenuItem MenuCreateCommand;
    private JMenuItem MenuValidatorJson;
    private JMenuItem MenuUpdateWedDriver;
    private JMenuItem MenuAbout;
    private JTabbedPane tabbedPane1;
    private JTextField textFieldDescription;
    private JTextField textFieldPort;
    private JTextField textFieldOption;
    private JButton buttonOptionAdd;
    private JList listOptions;
    private JButton buttonOptionRemove;
    private JButton buttonUA;
    private JButton buttonIC;
    private JTextField textFieldHar;
    private JSpinner spinnerWaitLimit;
    private JButton buttonDataAdd;
    private JButton buttonDataDelete;
    private JButton buttonDataCopy;
    private JButton buttonDataUp;
    private JButton buttonDataDown;
    private JTable tableData;
    private JButton buttonStepAdd;
    private JButton buttonStepDelete;
    private JButton buttonStepCopy;
    private JButton buttonStepUp;
    private JButton buttonStepDown;
    private JTable tableSteps;
    private JLabel labelPathFile;
    private JMenu MenuOpenAsTestFile;
    private JMenuItem MenuOpenAsDefault;
    private JMenuItem MenuOpenAsUtf8;
    private JMenuItem MenuOpenAsUtf8Bom;
    private JMenuItem MenuSaveTestFile;
    private JMenu MenuSaveAsTestFile;
    private JMenuItem MenuSaveAsDefault;
    private JMenuItem MenuSaveAsUtf8;
    private JMenuItem MenuSaveAsUtf8Bom;
    private JMenuItem MenuExit;
    private JMenuItem MenuOpenAsWindows1251;
    private JMenuItem MenuSaveAsWindows1251;
    private JLabel labelEncoding;

    public static void main(String[] args){
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new FormMain().PanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Editor tests for DetectGaYm");
        frame.pack();
        frame.setVisible(true);
     }

    private void initDataTable(Object[][] dataObj){
        tableData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableData.setModel(new DefaultTableModel(
                dataObj,
                new String[]{"Заголовок", "URL", "GA:category", "GA:action", "GA:label", "YM:code"}
        ));
    }

    private void initStepsTable(Object[][] stepsObj){
        tableSteps.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableSteps.setModel(new DefaultTableModel(
                stepsObj,
                new String[]{"Описание", "Тип действия", "Локатор (xpath) / Протокол", "Значение", "Время ожидания"}
        ));
        JComboBox comboboxType = new JComboBox(new String[] { "open_page", "open_default_page", "refresh_page",
                "input_value", "click_element", "find_element", "wait_text", "wait_element",
                "wait_element_not_visible", "sleep", "get_har", "get_har_ga", "get_har_ym", "clear_har",
                "test_defaults_ga", "test_optionally_ga", "test_defaults_ym", "test_optionally_ym"});
        comboboxType.setEditable(false);
        DefaultCellEditor editorType = new DefaultCellEditor(comboboxType);
        tableSteps.getColumnModel().getColumn(1).setCellEditor(editorType);

        JComboBox comboboxLocator = new JComboBox(new String[] {
                "google-analytics.com/collect", "mc.yandex.ru/watch"});
        comboboxLocator.setEditable(true);
        DefaultCellEditor editorLocator = new DefaultCellEditor(comboboxLocator);
        tableSteps.getColumnModel().getColumn(2).setCellEditor(editorLocator);

        JComboBox comboboxValue = new JComboBox(new String[] { "get_text", "get_attribute_",
                "get_attribute_id", "get_attribute_name", "get_attribute_class", "get_attribute_value",
                "get_attribute_href"});
        comboboxValue.setEditable(true);
        DefaultCellEditor editorValue = new DefaultCellEditor(comboboxValue);
        tableSteps.getColumnModel().getColumn(3).setCellEditor(editorValue);
    }

    private void loadJsonData() {
        textFieldDescription.setText(description);
        textFieldPort.setText(String.valueOf(port));
        textFieldHar.setText(nameHar);
        listOptions.setListData(arguments.toArray());
        spinnerWaitLimit.setValue(pageLoadTimeout);

        Object[][] dataObj = new Object[data.toArray().length][];
        for (int i = 0; i < data.toArray().length; i++)
        {
            JSONObject dataJson = (JSONObject) data.get(i);
            dataObj[i] = new String[6];
            dataObj[i][0] = dataJson.get("title").toString();
            dataObj[i][1] = dataJson.get("url").toString();
            dataObj[i][2] = dataJson.get("ga_category").toString();
            dataObj[i][3] = dataJson.get("ga_action").toString();
            dataObj[i][4] = dataJson.get("ga_label").toString();
            dataObj[i][5] = dataJson.get("ym_code").toString();
        }
        initDataTable(dataObj);

        Object[][] stepsObj = new Object[steps.toArray().length][];
        for (int i = 0; i < steps.toArray().length; i++){
            JSONObject dataJson = (JSONObject) steps.get(i);
            stepsObj[i] = new String[5];
            stepsObj[i][0] = dataJson.get("description").toString();
            stepsObj[i][1] = dataJson.get("type").toString();
            stepsObj[i][2] = dataJson.get("locator").toString();
            stepsObj[i][3] = dataJson.get("value").toString();
            stepsObj[i][4] = dataJson.get("timeout").toString();
        }
        initStepsTable(stepsObj);
    }

    public FormMain() {
        MenuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        MenuAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                showMessage("Editor for DetectGaYm");
            }
        });
        MenuOpenTestFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String path = Editor.dialogOpenFile(PanelMain);
                labelPathFile.setText(path);
                labelEncoding.setText(DEFAULT + " | Файл: ");
                if(labelPathFile.getText().equals("...")) return;
                try {
                    readJsonFile(path, DEFAULT);
                    loadJsonData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        MenuOpenAsDefault.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String path = Editor.dialogOpenFile(PanelMain);
                labelPathFile.setText(path);
                labelEncoding.setText(DEFAULT + " | Файл: ");
                if(labelPathFile.getText().equals("...")) return;
                try {
                    readJsonFile(path, DEFAULT);
                    loadJsonData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        MenuOpenAsUtf8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String path = Editor.dialogOpenFile(PanelMain);
                labelPathFile.setText(path);
                labelEncoding.setText(UTF_8 + " | Файл: ");
                if(labelPathFile.getText().equals("...")) return;
                try {
                    readJsonFile(path, UTF_8);
                    loadJsonData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        MenuOpenAsUtf8Bom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String path = Editor.dialogOpenFile(PanelMain);
                labelPathFile.setText(path);
                labelEncoding.setText(UTF_8_BOM + " | Файл: ");
                if(labelPathFile.getText().equals("...")) return;
                try {
                    readJsonFile(path, UTF_8_BOM);
                    loadJsonData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        MenuOpenAsWindows1251.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String path = Editor.dialogOpenFile(PanelMain);
                labelPathFile.setText(path);
                labelEncoding.setText(WINDOWS_1251 + " | Файл: ");
                if(labelPathFile.getText().equals("...")) return;
                try {
                    readJsonFile(path, WINDOWS_1251);
                    loadJsonData();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonIC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                textFieldOption.setText("--ignore-certificate-errors");
            }
        });
        buttonUA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                textFieldOption.setText("user-agent=node1");
            }
        });
        buttonOptionAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                listOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                DefaultListModel listModel = new DefaultListModel();
                for(int i = 0; i < listOptions.getModel().getSize(); i++) {
                    listModel.addElement(listOptions.getModel().getElementAt(i).toString());
                }
                listModel.addElement(textFieldOption.getText());
                listOptions.setModel(listModel);
            }
        });
        buttonOptionRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                listOptions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                int index = listOptions.getSelectedIndex();
                DefaultListModel listModel = new DefaultListModel();
                for(int i = 0; i < listOptions.getModel().getSize(); i++) {
                    if(index == i)continue;
                    listModel.addElement(listOptions.getModel().getElementAt(i).toString());
                }
                listOptions.setModel(listModel);
            }
        });
        buttonDataAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableData.getColumnModel().getColumnCount() <= 0) initDataTable(null);
                DefaultTableModel model = (DefaultTableModel) tableData.getModel();
                model.addRow(new Object[]{"", "", "", "", "", ""});
            }
        });
        buttonDataDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableData.getColumnModel().getColumnCount() <= 0) initDataTable(null);
                int indexSelectRow = tableData.getSelectedRow();
                if(indexSelectRow > -1){
                    DefaultTableModel model = (DefaultTableModel) tableData.getModel();
                    model.removeRow(indexSelectRow);
                }
            }
        });
        buttonDataCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableData.getColumnModel().getColumnCount() <= 0) initDataTable(null);
                int indexSelectRow = tableData.getSelectedRow();
                if(indexSelectRow > -1){
                    String title = tableData.getModel().getValueAt(indexSelectRow, 0).toString();
                    String url = tableData.getModel().getValueAt(indexSelectRow, 1).toString();
                    String ga_category = tableData.getModel().getValueAt(indexSelectRow, 2).toString();
                    String ga_action = tableData.getModel().getValueAt(indexSelectRow, 3).toString();
                    String ga_label = tableData.getModel().getValueAt(indexSelectRow, 4).toString();
                    String ym_code = tableData.getModel().getValueAt(indexSelectRow, 5).toString();
                    DefaultTableModel model = (DefaultTableModel) tableData.getModel();
                    model.addRow(new Object[]{title, url, ga_category, ga_action, ga_label, ym_code});
                }
            }
        });
        buttonDataUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableData.getColumnModel().getColumnCount() <= 0) initDataTable(null);
                int indexSelectRow = tableData.getSelectedRow();
                int countRows = tableData.getModel().getRowCount();
                if(indexSelectRow > -1 && (indexSelectRow-1) > -1 && countRows > 0){
                    DefaultTableModel model = (DefaultTableModel) tableData.getModel();
                    String title = tableData.getModel().getValueAt(indexSelectRow, 0).toString();
                    String url = tableData.getModel().getValueAt(indexSelectRow, 1).toString();
                    String ga_category = tableData.getModel().getValueAt(indexSelectRow, 2).toString();
                    String ga_action = tableData.getModel().getValueAt(indexSelectRow, 3).toString();
                    String ga_label = tableData.getModel().getValueAt(indexSelectRow, 4).toString();
                    String ym_code = tableData.getModel().getValueAt(indexSelectRow, 5).toString();
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow-1, 0).toString(), indexSelectRow, 0);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow-1, 1).toString(), indexSelectRow, 1);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow-1, 2).toString(), indexSelectRow, 2);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow-1, 3).toString(), indexSelectRow, 3);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow-1, 4).toString(), indexSelectRow, 4);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow-1, 5).toString(), indexSelectRow, 5);
                    tableData.getModel().setValueAt(title, indexSelectRow-1, 0);
                    tableData.getModel().setValueAt(url, indexSelectRow-1, 1);
                    tableData.getModel().setValueAt(ga_category, indexSelectRow-1, 2);
                    tableData.getModel().setValueAt(ga_action, indexSelectRow-1, 3);
                    tableData.getModel().setValueAt(ga_label, indexSelectRow-1, 4);
                    tableData.getModel().setValueAt(ym_code, indexSelectRow-1, 5);
                }
            }
        });
        buttonDataDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableData.getColumnModel().getColumnCount() <= 0) initDataTable(null);
                int indexSelectRow = tableData.getSelectedRow();
                int countRows = tableData.getModel().getRowCount();
                if(indexSelectRow > -1 && (indexSelectRow+1) < countRows  && countRows > 0){
                    DefaultTableModel model = (DefaultTableModel) tableData.getModel();
                    String title = tableData.getModel().getValueAt(indexSelectRow, 0).toString();
                    String url = tableData.getModel().getValueAt(indexSelectRow, 1).toString();
                    String ga_category = tableData.getModel().getValueAt(indexSelectRow, 2).toString();
                    String ga_action = tableData.getModel().getValueAt(indexSelectRow, 3).toString();
                    String ga_label = tableData.getModel().getValueAt(indexSelectRow, 4).toString();
                    String ym_code = tableData.getModel().getValueAt(indexSelectRow, 5).toString();
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow+1, 0).toString(), indexSelectRow, 0);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow+1, 1).toString(), indexSelectRow, 1);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow+1, 2).toString(), indexSelectRow, 2);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow+1, 3).toString(), indexSelectRow, 3);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow+1, 4).toString(), indexSelectRow, 4);
                    tableData.getModel().setValueAt(tableData.getModel().getValueAt(indexSelectRow+1, 5).toString(), indexSelectRow, 5);
                    tableData.getModel().setValueAt(title, indexSelectRow+1, 0);
                    tableData.getModel().setValueAt(url, indexSelectRow+1, 1);
                    tableData.getModel().setValueAt(ga_category, indexSelectRow+1, 2);
                    tableData.getModel().setValueAt(ga_action, indexSelectRow+1, 3);
                    tableData.getModel().setValueAt(ga_label, indexSelectRow+1, 4);
                    tableData.getModel().setValueAt(ym_code, indexSelectRow+1, 5);
                }
            }
        });
        buttonStepAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableSteps.getColumnModel().getColumnCount() <= 0) initStepsTable(null);
                DefaultTableModel model = (DefaultTableModel) tableSteps.getModel();
                model.addRow(new Object[]{"", "", "", "", "0"});
            }
        });
        buttonStepDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableSteps.getColumnModel().getColumnCount() <= 0) initStepsTable(null);
                int indexSelectRow = tableSteps.getSelectedRow();
                if(indexSelectRow > -1){
                    DefaultTableModel model = (DefaultTableModel) tableSteps.getModel();
                    model.removeRow(indexSelectRow);
                }
            }
        });
        buttonStepCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableSteps.getColumnModel().getColumnCount() <= 0) initStepsTable(null);
                int indexSelectRow = tableSteps.getSelectedRow();
                if(indexSelectRow > -1){
                    String description = tableSteps.getModel().getValueAt(indexSelectRow, 0).toString();
                    String type = tableSteps.getModel().getValueAt(indexSelectRow, 1).toString();
                    String locator = tableSteps.getModel().getValueAt(indexSelectRow, 2).toString();
                    String value = tableSteps.getModel().getValueAt(indexSelectRow, 3).toString();
                    String timeout = tableSteps.getModel().getValueAt(indexSelectRow, 4).toString();
                    DefaultTableModel model = (DefaultTableModel) tableSteps.getModel();
                    model.addRow(new Object[]{description, type, locator, value, timeout});
                }
            }
        });
        buttonStepUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableSteps.getColumnModel().getColumnCount() <= 0) initDataTable(null);
                int indexSelectRow = tableSteps.getSelectedRow();
                int countRows = tableSteps.getModel().getRowCount();
                if(indexSelectRow > -1 && (indexSelectRow-1) > -1 && countRows > 0){
                    String description = tableSteps.getModel().getValueAt(indexSelectRow, 0).toString();
                    String type = tableSteps.getModel().getValueAt(indexSelectRow, 1).toString();
                    String locator = tableSteps.getModel().getValueAt(indexSelectRow, 2).toString();
                    String value = tableSteps.getModel().getValueAt(indexSelectRow, 3).toString();
                    String timeout = tableSteps.getModel().getValueAt(indexSelectRow, 4).toString();
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow-1, 0).toString(), indexSelectRow, 0);
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow-1, 1).toString(), indexSelectRow, 1);
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow-1, 2).toString(), indexSelectRow, 2);
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow-1, 3).toString(), indexSelectRow, 3);
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow-1, 4).toString(), indexSelectRow, 4);
                    tableSteps.getModel().setValueAt(description, indexSelectRow-1, 0);
                    tableSteps.getModel().setValueAt(type, indexSelectRow-1, 1);
                    tableSteps.getModel().setValueAt(locator, indexSelectRow-1, 2);
                    tableSteps.getModel().setValueAt(value, indexSelectRow-1, 3);
                    tableSteps.getModel().setValueAt(timeout, indexSelectRow-1, 4);
                }
            }
        });
        buttonStepDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(tableSteps.getColumnModel().getColumnCount() <= 0) initDataTable(null);
                int indexSelectRow = tableSteps.getSelectedRow();
                int countRows = tableSteps.getModel().getRowCount();
                if(indexSelectRow > -1 && (indexSelectRow+1) < countRows  && countRows > 0){
                    String description = tableSteps.getModel().getValueAt(indexSelectRow, 0).toString();
                    String type = tableSteps.getModel().getValueAt(indexSelectRow, 1).toString();
                    String locator = tableSteps.getModel().getValueAt(indexSelectRow, 2).toString();
                    String value = tableSteps.getModel().getValueAt(indexSelectRow, 3).toString();
                    String timeout = tableSteps.getModel().getValueAt(indexSelectRow, 4).toString();
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow+1, 0).toString(), indexSelectRow, 0);
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow+1, 1).toString(), indexSelectRow, 1);
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow+1, 2).toString(), indexSelectRow, 2);
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow+1, 3).toString(), indexSelectRow, 3);
                    tableSteps.getModel().setValueAt(tableSteps.getModel().getValueAt(indexSelectRow+1, 4).toString(), indexSelectRow, 4);
                    tableSteps.getModel().setValueAt(description, indexSelectRow+1, 0);
                    tableSteps.getModel().setValueAt(type, indexSelectRow+1, 1);
                    tableSteps.getModel().setValueAt(locator, indexSelectRow+1, 2);
                    tableSteps.getModel().setValueAt(value, indexSelectRow+1, 3);
                    tableSteps.getModel().setValueAt(timeout, indexSelectRow+1, 4);
                }
            }
        });
    }

}
