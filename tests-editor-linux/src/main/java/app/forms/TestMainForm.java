package app.forms;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TestMainForm {
    private JPanel panelMain;
    private JButton createNewTestButton;
    private JButton openTestButton;
    private JButton saveTestButton;
    private JButton saveTestAsButton;
    private JButton executeTestButton;
    private JButton executeGroupTestsButton;
    private JButton createCommandButton;
    private JButton validationJsonButton;
    private JButton updateWebDriverButton;
    private JButton aboutButton;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField a9091TextField;
    private JTextField textField2;
    private JButton button1;
    private JList list1;
    private JButton button2;
    private JButton uaButton;
    private JButton icButton1;
    private JTextField testHarTextField;
    private JSpinner spinner1;
    private JButton новаяЗаписьButton;
    private JButton редактироватьЗаписьButton;
    private JButton удалитьЗаписьButton;
    private JButton копироватьЗаписьButton;
    private JButton вверхButton;
    private JButton внизButton;
    private JTable table1;
    private JButton новаяЗаписьButton1;
    private JButton редактироватьЗаписьButton1;
    private JButton удалитьЗаписьButton1;
    private JButton копироватьЗаписьButton1;
    private JButton вверхButton1;
    private JButton внизButton1;
    private JTable table2;
    private JMenuBar MenuBar;
    private JMenu MenuFile;
    private JMenu MenuExecute;
    private JMenuItem MenuCreateNewTest;
    private JMenuItem MenuOpenTest;

    public static void main(String[] args){
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new TestMainForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Editor tests for DetectGaYm");
        frame.pack();
        frame.setVisible(true);
    }

    public TestMainForm() {
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Editor for DetectGaYm");
            }
        });
        openTestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JSONObject jo = Editor.readJsonFile("");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
