package app.forms;

import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
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
    private JTextField textFieldOpdion;
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
                    showMessage(description);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}