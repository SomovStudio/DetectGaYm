using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Diagnostics;
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

        public string fileName;
        public delegate void AddConsoleItem(String message);
        public AddConsoleItem myDelegate;
        Process P;

        public void addConsoleItemMethod(String message)
        {
            consoleMessage(message);
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            myDelegate = new AddConsoleItem(addConsoleItemMethod);
        }

        public void consoleMessage(String message)
        {
            consoleRichTextBox.Text = consoleRichTextBox.Text + message + Environment.NewLine;
            consoleRichTextBox.Select(consoleRichTextBox.Text.Length, consoleRichTextBox.Text.Length);
            consoleRichTextBox.ScrollToCaret();
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
                this.fileName = openFileDialog1.SafeFileName;
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

        private void toolStripButton4_Click(object sender, EventArgs e)
        {
            saveFileAs();
        }
                

        private void saveFileAsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFileAs();
        }
        private void saveFileAs()
        {
            // https://codebeautify.org/jsonvalidator
            // https://jsonformatter.curiousconcept.com/

            if (saveFileDialog1.ShowDialog() == DialogResult.OK)
            {
                string json = "{";
                json += System.Environment.NewLine + "\"description\":\""+ this.textBoxDescription.Text +"\",";
                json += System.Environment.NewLine + "\"port\":" + this.textBoxPort.Text + ",";
                json += System.Environment.NewLine + "\"arguments\":[";
                int count = this.listBoxArguments.Items.Count;
                for (int i = 0; i < count; i++)
                {
                    if(i != count - 1) json += System.Environment.NewLine + "\"" + this.listBoxArguments.Items[i].ToString() + "\",";
                    else json += System.Environment.NewLine + "\"" + this.listBoxArguments.Items[i].ToString() + "\"";
                }
                json += System.Environment.NewLine + "],";
                json += System.Environment.NewLine + "\"har\":\"" + this.textBoxHar.Text + "\",";
                json += System.Environment.NewLine + "\"data\":[";
                count = this.listView1.Items.Count;
                for (int j = 0; j < count; j++)
                {
                    json += System.Environment.NewLine + "{";
                    json += System.Environment.NewLine + "\"title\":\"" + this.listView1.Items[j].SubItems[1].Text + "\",";
                    json += System.Environment.NewLine + "\"url\":\"" + this.listView1.Items[j].SubItems[2].Text + "\",";
                    json += System.Environment.NewLine + "\"ga_category\":\"" + this.listView1.Items[j].SubItems[3].Text + "\",";
                    json += System.Environment.NewLine + "\"ga_action\":\"" + this.listView1.Items[j].SubItems[4].Text + "\",";
                    json += System.Environment.NewLine + "\"ga_label\":\"" + this.listView1.Items[j].SubItems[5].Text + "\",";
                    json += System.Environment.NewLine + "\"ym_code\":\"" + this.listView1.Items[j].SubItems[6].Text + "\"";
                    if (j != count - 1) json += System.Environment.NewLine + "},";
                    else json += System.Environment.NewLine + "}";
                }
                json += System.Environment.NewLine + "],";
                json += System.Environment.NewLine + "\"steps\":[";
                count = this.listView2.Items.Count;
                for (int k = 0; k < count; k++)
                {
                    json += System.Environment.NewLine + "{";
                    json += System.Environment.NewLine + "\"description\":\"" + this.listView2.Items[k].SubItems[1].Text + "\",";
                    json += System.Environment.NewLine + "\"type\":\"" + this.listView2.Items[k].SubItems[2].Text + "\",";
                    json += System.Environment.NewLine + "\"value\":\"" + this.listView2.Items[k].SubItems[4].Text + "\",";
                    json += System.Environment.NewLine + "\"locator\":\"" + this.listView2.Items[k].SubItems[3].Text + "\",";
                    json += System.Environment.NewLine + "\"timeout\":" + this.listView2.Items[k].SubItems[5].Text;
                    if (k != count - 1) json += System.Environment.NewLine + "},";
                    else json += System.Environment.NewLine + "}";
                }
                json += System.Environment.NewLine + "]";
                json += System.Environment.NewLine + "}";

                try
                {
                    using (StreamWriter writer = new StreamWriter(saveFileDialog1.FileName))
                    {
                        writer.Write(json);
                    }
                    this.fileName = Path.GetFileName(saveFileDialog1.FileName);
                    this.toolStripStatusLabelFileName.Text = saveFileDialog1.FileName;
                    MessageBox.Show("File " + this.fileName + " - saved successfully!");
                }
                catch (Exception exp)
                {
                    MessageBox.Show(exp.Message, "Error");
                }
                
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void saveFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFile();
        }

        private void toolStripButton3_Click(object sender, EventArgs e)
        {
            saveFile();
        }

        private void saveFile()
        {
            string json = "{";
            json += System.Environment.NewLine + "\"description\":\"" + this.textBoxDescription.Text + "\",";
            json += System.Environment.NewLine + "\"port\":" + this.textBoxPort.Text + ",";
            json += System.Environment.NewLine + "\"arguments\":[";
            int count = this.listBoxArguments.Items.Count;
            for (int i = 0; i < count; i++)
            {
                if (i != count - 1) json += System.Environment.NewLine + "\"" + this.listBoxArguments.Items[i].ToString() + "\",";
                else json += System.Environment.NewLine + "\"" + this.listBoxArguments.Items[i].ToString() + "\"";
            }
            json += System.Environment.NewLine + "],";
            json += System.Environment.NewLine + "\"har\":\"" + this.textBoxHar.Text + "\",";
            json += System.Environment.NewLine + "\"data\":[";
            count = this.listView1.Items.Count;
            for (int j = 0; j < count; j++)
            {
                json += System.Environment.NewLine + "{";
                json += System.Environment.NewLine + "\"title\":\"" + this.listView1.Items[j].SubItems[1].Text + "\",";
                json += System.Environment.NewLine + "\"url\":\"" + this.listView1.Items[j].SubItems[2].Text + "\",";
                json += System.Environment.NewLine + "\"ga_category\":\"" + this.listView1.Items[j].SubItems[3].Text + "\",";
                json += System.Environment.NewLine + "\"ga_action\":\"" + this.listView1.Items[j].SubItems[4].Text + "\",";
                json += System.Environment.NewLine + "\"ga_label\":\"" + this.listView1.Items[j].SubItems[5].Text + "\",";
                json += System.Environment.NewLine + "\"ym_code\":\"" + this.listView1.Items[j].SubItems[6].Text + "\"";
                if (j != count - 1) json += System.Environment.NewLine + "},";
                else json += System.Environment.NewLine + "}";
            }
            json += System.Environment.NewLine + "],";
            json += System.Environment.NewLine + "\"steps\":[";
            count = this.listView2.Items.Count;
            for (int k = 0; k < count; k++)
            {
                json += System.Environment.NewLine + "{";
                json += System.Environment.NewLine + "\"description\":\"" + this.listView2.Items[k].SubItems[1].Text + "\",";
                json += System.Environment.NewLine + "\"type\":\"" + this.listView2.Items[k].SubItems[2].Text + "\",";
                json += System.Environment.NewLine + "\"value\":\"" + this.listView2.Items[k].SubItems[4].Text + "\",";
                json += System.Environment.NewLine + "\"locator\":\"" + this.listView2.Items[k].SubItems[3].Text + "\",";
                json += System.Environment.NewLine + "\"timeout\":" + this.listView2.Items[k].SubItems[5].Text;
                if (k != count - 1) json += System.Environment.NewLine + "},";
                else json += System.Environment.NewLine + "}";
            }
            json += System.Environment.NewLine + "]";
            json += System.Environment.NewLine + "}";

            try
            {
                using (StreamWriter writer = new StreamWriter(this.toolStripStatusLabelFileName.Text))
                {
                    writer.Write(json);
                }
                MessageBox.Show("File saved successfully!");
            }
            catch (Exception exp)
            {
                MessageBox.Show(exp.Message, "Error");
            }
        }

        private void executeTestToolStripMenuItem_Click(object sender, EventArgs e)
        {
            executeTest();
        }

        private void executeTest()
        {
            try
            {
                string path = Directory.GetCurrentDirectory();
                string bat = "cd " + path;
                bat += System.Environment.NewLine + "detect.bat \\tests\\" + this.fileName;
                using (StreamWriter writer = new StreamWriter("run.bat"))
                {
                    writer.Write(bat);
                }

                P = new Process();
                P.StartInfo.FileName = "run.bat";
                P.StartInfo.Arguments = "/k";
                P.StartInfo.RedirectStandardError = true;
                P.StartInfo.RedirectStandardInput = true;
                P.StartInfo.RedirectStandardOutput = true;
                P.StartInfo.CreateNoWindow = true;
                P.StartInfo.UseShellExecute = false;
                P.ErrorDataReceived += P_ErrorDataReceived;
                P.OutputDataReceived += P_OutputDataReceived;
                P.EnableRaisingEvents = true;
                P.Exited += P_Exited;
                P.Start();
                P.BeginErrorReadLine();
                P.BeginOutputReadLine();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error");
                if (File.Exists("run.bat")) File.Delete("run.bat");
            }
        }

        void P_ErrorDataReceived(object sender, DataReceivedEventArgs e)
        {
            try
            {
                if (this.detailedInformationInTheConsoleToolStripMenuItem.Checked)
                {
                    this.Invoke(this.myDelegate, new object[] { e.Data.ToString() });
                }
            }
            catch (Exception ex)
            {

            }
        }
        void P_OutputDataReceived(object sender, DataReceivedEventArgs e)
        {
            try
            {
                this.Invoke(this.myDelegate, new object[] { e.Data.ToString() });
            }
            catch (Exception ex)
            {

            }
        }

        void P_Exited(object sender, EventArgs e)
        {
            if (File.Exists("run.bat")) File.Delete("run.bat");
        }

        private void detailedInformationInTheConsoleToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (this.detailedInformationInTheConsoleToolStripMenuItem.Checked)
            {
                this.detailedInformationInTheConsoleToolStripMenuItem.Checked = false;
            }
            else
            {
                this.detailedInformationInTheConsoleToolStripMenuItem.Checked = true;
            }
        }

        private void clearToolStripMenuItem_Click(object sender, EventArgs e)
        {
            consoleRichTextBox.Clear();
        }

        private void openSystemConsoleToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Process.Start("cmd.exe");
        }

        private void toolStripButton12_Click(object sender, EventArgs e)
        {
            Process.Start("cmd.exe");
        }

        private void toolStripButton11_Click(object sender, EventArgs e)
        {
            executeTest();
        }

        private void stopToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                P.Kill();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error");
            }
        }

        private void getStartCommandToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Form4 form = new Form4();
            form.parentForm = this;
            form.ShowDialog();
        }
    }
}
