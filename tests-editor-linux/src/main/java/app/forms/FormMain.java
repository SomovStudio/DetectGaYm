package app.forms;

import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JMenuItem MenuSaveTestFile;
    private JMenuItem MenuSaveAsTestFile;
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
    private JButton buttonDataEdit;
    private JButton buttonDataDelete;
    private JButton buttonDataCopy;
    private JButton buttonDataUp;
    private JButton buttonDataDown;
    private JTable tableData;
    private JButton buttonStepAdd;
    private JButton buttonStepEdit;
    private JButton buttonStepDelete;
    private JButton buttonStepCopy;
    private JButton buttonStepUp;
    private JButton buttonStepDown;
    private JTable tableSteps;
    private JLabel labelPathFile;

    public static void main(String[] args){
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new FormMain().PanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Editor tests for DetectGaYm");
        frame.pack();
        frame.setVisible(true);
    }

    public FormMain() {
        MenuAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                showMessage("Editor for DetectGaYm");
            }
        });
        MenuOpenTestFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String path = Editor.dialogOpenFile(PanelMain);
                labelPathFile.setText(path);
                try {
                    readJsonFile(path);
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
                        dataObj[i][0] = dataJson.get("title");
                        dataObj[i][1] = dataJson.get("url");
                        dataObj[i][2] = dataJson.get("ga_category");
                        dataObj[i][3] = dataJson.get("ga_action");
                        dataObj[i][4] = dataJson.get("ga_label");
                        dataObj[i][5] = dataJson.get("ym_code");
                    }
                    tableData.setModel(new DefaultTableModel(
                            dataObj,
                            new String[]{"title", "url", "ga_category", "ga_action", "ga_label", "ym_code"}
                    ));


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
