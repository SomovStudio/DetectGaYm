using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace TestsEditor
{
    public partial class Form3 : Form
    {
        public Form3()
        {
            InitializeComponent();
        }

        public Form1 parentForm;
        public int index;

        private void button2_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (this.Text == "Add")
            {
                ListViewItem item;
                ListViewItem.ListViewSubItem subitem;
                item = new ListViewItem();
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = textBoxDescription.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = comboBoxType.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = textBoxLocator.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = comboBoxValue.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = numericUpDownTimeout.Value.ToString();
                item.SubItems.Add(subitem);
                item.ImageIndex = 0;
                parentForm.listView2.Items.Add(item);
            }
            else
            {
                parentForm.listView2.Items[index].SubItems[1].Text = textBoxDescription.Text;
                parentForm.listView2.Items[index].SubItems[2].Text = comboBoxType.Text;
                parentForm.listView2.Items[index].SubItems[3].Text = textBoxLocator.Text;
                parentForm.listView2.Items[index].SubItems[4].Text = comboBoxValue.Text;
                parentForm.listView2.Items[index].SubItems[5].Text = numericUpDownTimeout.Value.ToString();
            }

            Close();
        }

        private void comboBoxType_SelectedIndexChanged(object sender, EventArgs e)
        {
            label9.Text = "Описание:";
            textBoxDescription.ReadOnly = false;
            label3.Text = "Тип действия:";
            label5.Text = "Локатор (XPath):";
            textBoxLocator.ReadOnly = false;
            textBoxLocator.Text = "";
            label4.Text = "Значение:";
            comboBoxValue.Enabled = false;
            comboBoxValue.Text = "";
            label6.Text = "Время ожидания:";
            numericUpDownTimeout.ReadOnly = false;
            numericUpDownTimeout.Value = 0;

            switch (comboBoxType.Text)
            {
                case "open_page":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = true;
                    numericUpDownTimeout.ReadOnly = true;
                    break;
                case "open_default_page":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = true;
                    break;
                case "refresh_page":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = true;
                    break;
                case "input_value":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = false;
                    comboBoxValue.Enabled = true;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "click_element":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = false;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "find_element":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = false;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "wait_text":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = false;
                    comboBoxValue.Enabled = true;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "wait_element":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = false;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "wait_element_not_visible":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = false;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "sleep":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "get_har":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = true;
                    break;
                case "get_har_ga":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = true;
                    break;
                case "get_har_ym":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = true;
                    break;
                case "clear_har":
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = true;
                    break;
                case "test_defaults_ga":
                    label5.Text = "Протокол:";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "test_optionally_ga":
                    label5.Text = "Протокол:";
                    textBoxLocator.Text = "google-analytics.com/collect";
                    textBoxLocator.ReadOnly = false;
                    comboBoxValue.Enabled = true;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "test_defaults_ym":
                    label5.Text = "Протокол:";
                    textBoxLocator.ReadOnly = true;
                    comboBoxValue.Enabled = false;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                case "test_optionally_ym":
                    label5.Text = "Протокол:";
                    textBoxLocator.Text = "mc.yandex.ru/watch";
                    textBoxLocator.ReadOnly = false;
                    comboBoxValue.Enabled = true;
                    numericUpDownTimeout.ReadOnly = false;
                    break;
                default:
                    label9.Text = "Описание:";
                    textBoxDescription.ReadOnly = false;
                    label3.Text = "Тип действия:";
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = false;
                    textBoxLocator.Text = "";
                    label4.Text = "Значение:";
                    comboBoxValue.Enabled = true;
                    label6.Text = "Время ожидания:";
                    numericUpDownTimeout.ReadOnly = false;
                    break;
            }
            
        }

        private void Form3_Load(object sender, EventArgs e)
        {
            richTextBox1.Rtf = richTextBox1.Text;
        }
    }
}
