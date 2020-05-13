using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using Newtonsoft.Json; // https://www.newtonsoft.com/json

namespace TestsEditor
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void openFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFile();
        }

        private void toolStripButton2_Click(object sender, EventArgs e)
        {
            openFile();
        }

        private void openFile()
        {
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                StreamReader sr = new StreamReader(openFileDialog1.FileName, Encoding.Default); // Encoding.UTF8
                string jsonText = sr.ReadToEnd();
                sr.Close();

                TestJson test = JsonConvert.DeserializeObject<TestJson>(jsonText);
                textBoxDescription.Text = test.description;
                textBoxPort.Text = test.port.ToString();
                listBoxArguments.Items.Clear();
                foreach (string argument in test.arguments)
                {
                    listBoxArguments.Items.Add(argument);
                }
                textBoxHar.Text = test.har;

                listView1.Items.Clear();
                ListViewItem item;
                ListViewItem.ListViewSubItem subitem;
                foreach (TestJsonData data in test.data)
                {
                    item = new ListViewItem();
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = data.title;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = data.url;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = data.ga_category;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = data.ga_action;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = data.ga_label;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = data.ym_code;
                    item.SubItems.Add(subitem);
                    item.ImageIndex = 0;
                    listView1.Items.Add(item);
                }

                listView2.Items.Clear();
                foreach (TestJsonSteps step in test.steps)
                {
                    item = new ListViewItem();
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = step.description;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = step.type;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = step.locator;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = step.value;
                    item.SubItems.Add(subitem);
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = step.timeout.ToString();
                    item.SubItems.Add(subitem);
                    item.ImageIndex = 0;
                    listView2.Items.Add(item);
                }

                toolStripStatusLabelFileName.Text = openFileDialog1.FileName;
            }
        }

        private void toolStripButton1_Click(object sender, EventArgs e)
        {
            newFile();
        }

        private void newFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            newFile();
        }

        private void newFile()
        {
            Form1 newform = new Form1();
            newform.Show();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            listBoxArguments.Items.Add(textBoxArguments.Text);
            textBoxArguments.Text = "";
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if(listBoxArguments.SelectedIndex > -1) listBoxArguments.Items.RemoveAt(listBoxArguments.SelectedIndex);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            textBoxArguments.Text = "--ignore-certificate-errors";
        }

        private void button4_Click(object sender, EventArgs e)
        {
            textBoxArguments.Text = "user-agent=node1";
        }

        private void toolStripButton5_Click(object sender, EventArgs e)
        {
            Form2 form = new Form2();
            form.Text = "Add";
            form.parentForm = this;
            form.Show();
        }

        private void toolStripButton6_Click(object sender, EventArgs e)
        {
            if(listView1.FocusedItem != null)
            {
                int index = listView1.FocusedItem.Index;
                Form2 form = new Form2();
                form.parentForm = this;
                form.index = index;
                form.Text = "Edit";
                form.parentForm = this;
                form.textBoxTitle.Text = listView1.Items[index].SubItems[1].Text;
                form.textBoxUrl.Text = listView1.Items[index].SubItems[2].Text;
                form.textBoxGaCategory.Text = listView1.Items[index].SubItems[3].Text;
                form.textBoxGaAction.Text = listView1.Items[index].SubItems[4].Text;
                form.textBoxGaLabel.Text = listView1.Items[index].SubItems[5].Text;
                form.textBoxYmCode.Text = listView1.Items[index].SubItems[6].Text;
                form.Show();
            }
        }

        private void toolStripButton7_Click(object sender, EventArgs e)
        {
            if (listView1.FocusedItem != null)
            {
                int index = listView1.FocusedItem.Index;
                listView1.Items[index].Remove();
            }
        }

        private void toolStripButton8_Click(object sender, EventArgs e)
        {
            Form3 form = new Form3();
            form.Text = "Add";
            form.parentForm = this;
            form.Show();
        }

        private void toolStripButton9_Click(object sender, EventArgs e)
        {
            if (listView2.FocusedItem != null)
            {
                int index = listView2.FocusedItem.Index;
                Form3 form = new Form3();
                form.parentForm = this;
                form.index = index;
                form.Text = "Edit";
                form.parentForm = this;
                form.textBoxDescription.Text = listView2.Items[index].SubItems[1].Text;
                form.comboBoxType.Text = listView2.Items[index].SubItems[2].Text;
                form.textBoxLocator.Text = listView2.Items[index].SubItems[3].Text;
                form.textBoxValue.Text = listView2.Items[index].SubItems[4].Text;
                form.numericUpDownTimeout.Text = listView2.Items[index].SubItems[5].Text;
                form.Show();
            }
        }

        private void toolStripButton10_Click(object sender, EventArgs e)
        {
            if (listView2.FocusedItem != null)
            {
                int index = listView2.FocusedItem.Index;
                listView2.Items[index].Remove();
            }
        }
    }
}
