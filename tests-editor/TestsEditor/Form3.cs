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
                subitem.Text = textBoxValue.Text;
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
                parentForm.listView2.Items[index].SubItems[4].Text = textBoxValue.Text;
                parentForm.listView2.Items[index].SubItems[5].Text = numericUpDownTimeout.Value.ToString();
            }

            Close();
        }

        private void comboBoxType_SelectedIndexChanged(object sender, EventArgs e)
        {
            switch (comboBoxType.Text)
            {
                case "test_optionally_ga":
                    label5.Text = "Протокол:";
                    textBoxLocator.Text = "google-analytics.com/collect";
                    textBoxLocator.ReadOnly = false;
                    textBoxValue.ReadOnly = false;
                    break;
                case "test_optionally_ym":
                    label5.Text = "Протокол:";
                    textBoxLocator.Text = "mc.yandex.ru/watch";
                    textBoxLocator.ReadOnly = false;
                    textBoxValue.ReadOnly = false;
                    break;
                case "sleep":
                    label5.Text = "Локатор (XPath):";
                    textBoxValue.ReadOnly = true;
                    break;
                default:
                    label9.Text = "Описание:";
                    textBoxDescription.ReadOnly = false;
                    label3.Text = "Тип действия:";
                    label5.Text = "Локатор (XPath):";
                    textBoxLocator.ReadOnly = false;
                    textBoxLocator.Text = "";
                    label4.Text = "Значение:";
                    textBoxValue.ReadOnly = false;
                    label6.Text = "Время ожидания:";
                    numericUpDownTimeout.ReadOnly = false;
                    break;
            }
            
        }
    }
}
