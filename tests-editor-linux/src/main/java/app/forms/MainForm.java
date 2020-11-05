package app.forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel panelMain;
    private JButton создатьТестButton;
    private JButton открытьТестButton;
    private JButton сохранитьТестButton;
    private JButton сохранитьТестКакButton;
    private JButton выполнитьТестButton;
    private JButton выполнитьГруппуТестовButton;
    private JButton сформироватьКомандуButton;
    private JButton валидаторJsonButton;
    private JButton обновитьWebDriverButton;
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

    public static void main(String[] args){
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Editor tests for DetectGaYm");
        frame.pack();
        frame.setVisible(true);
    }

    public MainForm() {
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Editor for DetectGaYm");
            }
        });
    }


}
