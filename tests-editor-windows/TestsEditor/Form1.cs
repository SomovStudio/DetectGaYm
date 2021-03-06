﻿using System;
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

        public const string DEFAULT = "DEFAULT";
        public const string UTF_8 = "UTF-8";
        public const string UTF_8_BOM = "UTF-8-BOM";
        public const string WINDOWS_1251 = "WINDOWS-1251";

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
            toolStripStatusLabelFileEncoding.Text = DEFAULT;
        }

        public void consoleMessage(String message)
        {
            consoleRichTextBox.Text = consoleRichTextBox.Text + message + Environment.NewLine;
            consoleRichTextBox.Select(consoleRichTextBox.Text.Length, consoleRichTextBox.Text.Length);
            consoleRichTextBox.ScrollToCaret();
        }


        private void openFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFile(DEFAULT);
        }


        private void openFile(string encoding)
        {
            try
            {
                if (openFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    StreamReader sr;
                    if (encoding == DEFAULT)
                    {
                        sr = new StreamReader(openFileDialog1.FileName, Encoding.Default);
                    }
                    else if (encoding == UTF_8)
                    {
                        sr = new StreamReader(openFileDialog1.FileName, new UTF8Encoding(false));
                    }
                    else if (encoding == UTF_8_BOM)
                    {
                        sr = new StreamReader(openFileDialog1.FileName, new UTF8Encoding(true));
                    }
                    else if (encoding == WINDOWS_1251)
                    {
                        sr = new StreamReader(openFileDialog1.FileName, Encoding.GetEncoding("Windows-1251"));
                    }
                    else
                    {
                        sr = new StreamReader(openFileDialog1.FileName, Encoding.Default);
                    }
                    string jsonText = sr.ReadToEnd();
                    sr.Close();

                    TestJson test = JsonConvert.DeserializeObject<TestJson>(jsonText);
                    textBoxDescription.Text = test.description.Replace("\"", "\\\"");
                    textBoxPort.Text = test.port.ToString().Replace("\"", "\\\"");
                    listBoxArguments.Items.Clear();
                    foreach (string argument in test.arguments)
                    {
                        listBoxArguments.Items.Add(argument.Replace("\"", "\\\""));
                    }
                    textBoxHar.Text = test.har.Replace("\"", "\\\"");
                    numericUpDownPageLoadTimeout.Value = test.timeout;

                    listView1.Items.Clear();
                    ListViewItem item;
                    ListViewItem.ListViewSubItem subitem;
                    foreach (TestJsonData data in test.data)
                    {
                        item = new ListViewItem();
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = data.title.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = data.url.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = data.ga_category.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = data.ga_action.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = data.ga_label.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = data.ym_code.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        item.ImageIndex = 0;
                        listView1.Items.Add(item);
                    }

                    listView2.Items.Clear();
                    foreach (TestJsonSteps step in test.steps)
                    {
                        item = new ListViewItem();
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = step.description.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = step.type.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = step.locator.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = step.value.Replace("\"", "\\\"");
                        item.SubItems.Add(subitem);
                        subitem = new ListViewItem.ListViewSubItem();
                        subitem.Text = step.timeout.ToString();
                        item.SubItems.Add(subitem);
                        item.ImageIndex = 0;
                        listView2.Items.Add(item);
                    }

                    this.toolStripStatusLabelFileEncoding.Text = encoding;
                    this.toolStripStatusLabelFileName.Text = openFileDialog1.FileName;
                    this.fileName = openFileDialog1.SafeFileName;
                    consoleMessage(DateTime.Now + " Сообщение: Открыт файл " + fileName);
                }
            }
            catch (Exception ex)
            {
                consoleMessage(DateTime.Now + " Ошибка: " + ex.Message);
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
            //Form1 newform = new Form1();
            //newform.Show();
            textBoxDescription.Text = "";
            textBoxPort.Text = "9091";
            textBoxArguments.Text = "";
            listBoxArguments.Items.Clear();
            listBoxArguments.Items.Add("--ignore-certificate-errors");
            numericUpDownPageLoadTimeout.Value = 0;
            listView1.Items.Clear();
            listView2.Items.Clear();
            fileName = "";
            toolStripStatusLabelFileEncoding.Text = DEFAULT;
            toolStripStatusLabelFileName.Text = "...";
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
                form.comboBoxValue.Text = listView2.Items[index].SubItems[4].Text;
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

        private void saveFileAsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            
        }
        private void saveFileAs(string encoding)
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
                json += System.Environment.NewLine + "\"timeout\":" + this.numericUpDownPageLoadTimeout.Value.ToString() + ",";
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
                    StreamWriter writer;
                    if (encoding == DEFAULT)
                    {
                        writer = new StreamWriter(saveFileDialog1.FileName, false, Encoding.Default);
                    }
                    else if (encoding == UTF_8)
                    {
                        writer = new StreamWriter(saveFileDialog1.FileName, false, new UTF8Encoding(false));
                    }
                    else if (encoding == UTF_8_BOM)
                    {
                        writer = new StreamWriter(saveFileDialog1.FileName, false, new UTF8Encoding(true));
                    }
                    else if (encoding == WINDOWS_1251)
                    {
                        writer = new StreamWriter(saveFileDialog1.FileName, false, Encoding.GetEncoding("Windows-1251"));
                    }
                    else
                    {
                        writer = new StreamWriter(saveFileDialog1.FileName, false, Encoding.Default);
                    }
                    writer.Write(json);
                    writer.Close();
                    this.fileName = Path.GetFileName(saveFileDialog1.FileName);
                    this.toolStripStatusLabelFileName.Text = saveFileDialog1.FileName;
                    this.toolStripStatusLabelFileEncoding.Text = encoding;
                    consoleMessage(DateTime.Now + " Сообщение: Файл " + fileName + " успешно сохранён!");
                    //MessageBox.Show("Файл: " + this.fileName + " - успешно сохранён!" , "Сообщение");
                }
                catch (Exception ex)
                {
                    consoleMessage(DateTime.Now + " Ошибка: " + ex.Message);
                }
                validatorJson();
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void saveFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (this.toolStripStatusLabelFileName.Text != "...") saveFile();
            else saveFileAs(toolStripStatusLabelFileEncoding.Text);
        }

        private void toolStripButton3_Click(object sender, EventArgs e)
        {
            if (this.toolStripStatusLabelFileName.Text != "...") saveFile();
            else saveFileAs(toolStripStatusLabelFileEncoding.Text);
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
            json += System.Environment.NewLine + "\"timeout\":" + this.numericUpDownPageLoadTimeout.Value.ToString() + ",";
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
                StreamWriter writer;
                if (toolStripStatusLabelFileEncoding.Text == DEFAULT)
                {
                    writer = new StreamWriter(this.toolStripStatusLabelFileName.Text, false, Encoding.Default);
                }
                else if (toolStripStatusLabelFileEncoding.Text == UTF_8)
                {
                    writer = new StreamWriter(this.toolStripStatusLabelFileName.Text, false, new UTF8Encoding(false));
                }
                else if (toolStripStatusLabelFileEncoding.Text == UTF_8_BOM)
                {
                    writer = new StreamWriter(this.toolStripStatusLabelFileName.Text, false, new UTF8Encoding(true));
                }
                else if (toolStripStatusLabelFileEncoding.Text == WINDOWS_1251)
                {
                    writer = new StreamWriter(this.toolStripStatusLabelFileName.Text, false, Encoding.GetEncoding("Windows-1251"));
                }
                else
                {
                    writer = new StreamWriter(this.toolStripStatusLabelFileName.Text, false, Encoding.Default);
                }
                writer.Write(json);
                writer.Close();
                consoleMessage(DateTime.Now + " Сообщение: Файл " + fileName +" успешно сохранён!");
                //MessageBox.Show("Файл успешно сохранён!", "Сообщение");
            }
            catch (Exception ex)
            {
                consoleMessage(DateTime.Now + " Ошибка: " + ex.Message);
            }

            validatorJson();
        }

        private string getFolderName()
        {
            string path = toolStripStatusLabelFileName.Text;
            if (path != "...") {
                path = path.Substring(0, path.Length - this.fileName.Length-1);
                path = path.Substring(path.LastIndexOf("\\")+1);
                return path;
            }
            return "tests";
        }

        private void executeTestToolStripMenuItem_Click(object sender, EventArgs e)
        {
            executeTest();
        }

        private void executeTest()
        {
            try
            {
                string folderBin = Directory.GetCurrentDirectory();
                folderBin = folderBin.Substring(0, folderBin.Length - "editor".Length);
                folderBin = folderBin + "bin";

                string bat = "cd " + folderBin;
                string filename = toolStripStatusLabelFileName.Text;
                
                if (File.Exists(filename)) {
                    if(checkEncodingToolStripMenuItem.Checked) bat += System.Environment.NewLine + "java -jar detect-gaym.jar " + toolStripStatusLabelFileEncoding.Text + " " + filename;
                    else bat += System.Environment.NewLine + "java -jar detect-gaym.jar " + filename;
                }
                else{
                    consoleMessage(DateTime.Now + " Сообщение: Невозможно запустить тест! Файл не существует.");
                    //MessageBox.Show("Невозможно запустить тест! Файл не существует.");
                    return;
                }

                string batFile = folderBin + "\\run-test.bat";
                StreamWriter writer = new StreamWriter(batFile, false, new UTF8Encoding(false));
                writer.Write(bat);
                writer.Close();

                P = new Process();
                P.StartInfo.FileName = batFile;
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
                consoleMessage(DateTime.Now + " Ошибка: " + ex.Message);
                //MessageBox.Show(ex.Message, "Ошибка");
                //if (File.Exists("run.bat")) File.Delete("run.bat");
            }
        }

        private void executePackTest(string folderName)
        {
            try
            {
                string folderBin = Directory.GetCurrentDirectory();
                folderBin = folderBin.Substring(0, folderBin.Length - "editor".Length);
                folderBin = folderBin + "bin";

                string bat = "cd " + folderBin;
                if (checkEncodingToolStripMenuItem.Checked) bat += System.Environment.NewLine + "java -jar detect-gaym.jar " + toolStripStatusLabelFileEncoding.Text + " " + folderName;
                else bat += System.Environment.NewLine + "java -jar detect-gaym.jar " + folderName;

                string batFile = folderBin + "\\run-test.bat";
                StreamWriter writer = new StreamWriter(batFile, false, new UTF8Encoding(false));
                writer.Write(bat);
                writer.Close();

                P = new Process();
                P.StartInfo.FileName = batFile;
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
                consoleMessage(DateTime.Now + " Ошибка: " + ex.Message);
                //MessageBox.Show(ex.Message, "Ошибка");
                //if (File.Exists("run.bat")) File.Delete("run.bat");
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
            //if (File.Exists("run.bat")) File.Delete("run.bat");
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
            foreach (Process p in Process.GetProcesses())
            {
                if (p.ProcessName.ToString() == "chromedriver" || p.ProcessName.ToString() == "java")
                {
                    try
                    {
                        p.Kill();
                        consoleMessage("Удаление процесса: " + p.ProcessName + " | ID:" + p.Id.ToString());
                    }
                    catch (Exception ex)
                    {
                        //consoleMessage("Удаление процесса: " + p.ProcessName + " | ID:" + p.Id.ToString() + " | Ошибка: " + ex.Message);
                    }
                }
            }
        }

        private void getStartCommandToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Form4 form = new Form4();
            form.parentForm = this;
            form.fileName = this.fileName;
            form.folder = getFolderName();
            form.ShowDialog();
        }

        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Form5 form = new Form5();
            form.ShowDialog();
        }

        private void toolStripButton13_Click(object sender, EventArgs e)
        {
            if (listView1.FocusedItem != null)
            {
                int index = listView1.FocusedItem.Index;
                ListViewItem item;
                ListViewItem.ListViewSubItem subitem;
                item = new ListViewItem();
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView1.Items[index].SubItems[1].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView1.Items[index].SubItems[2].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView1.Items[index].SubItems[3].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView1.Items[index].SubItems[4].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView1.Items[index].SubItems[5].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView1.Items[index].SubItems[6].Text;
                item.SubItems.Add(subitem);
                item.ImageIndex = 0;
                this.listView1.Items.Add(item);
            }
        }

        private void toolStripButton14_Click(object sender, EventArgs e)
        {
            if (listView2.FocusedItem != null)
            {
                int index = listView2.FocusedItem.Index;
                ListViewItem item;
                ListViewItem.ListViewSubItem subitem;
                item = new ListViewItem();
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView2.Items[index].SubItems[1].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView2.Items[index].SubItems[2].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView2.Items[index].SubItems[3].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView2.Items[index].SubItems[4].Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = listView2.Items[index].SubItems[5].Text;
                item.SubItems.Add(subitem);
                item.ImageIndex = 0;
                this.listView2.Items.Add(item);
            }

            
        }

        

        public bool form6Close = true;
        private void howToCloseThePortToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(form6Close == true)
            {
                Form6 form = new Form6();
                form.parentForm = this;
                form.Show();
            }
        }

        private void httpscodebeautifyorgjsonvalidatorToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                System.Diagnostics.Process.Start(@"https://codebeautify.org/jsonvalidator");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Ошибка");
            }
        }

        private void httpsjsonformattercuriousconceptcomToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                System.Diagnostics.Process.Start(@"https://jsonformatter.curiousconcept.com/");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Ошибка");
            }
        }

        private void toolStripButton15_Click(object sender, EventArgs e)
        {
            // UP
            if (listView1.FocusedItem != null)
            {
                int index = listView1.FocusedItem.Index;
                if(index > 0)
                {
                    string itemSelectText1 = listView1.Items[index].SubItems[1].Text;
                    string itemSelectText2 = listView1.Items[index].SubItems[2].Text;
                    string itemSelectText3 = listView1.Items[index].SubItems[3].Text;
                    string itemSelectText4 = listView1.Items[index].SubItems[4].Text;
                    string itemSelectText5 = listView1.Items[index].SubItems[5].Text;
                    string itemSelectText6 = listView1.Items[index].SubItems[6].Text;
                    
                    string itemMoveText1 = listView1.Items[index-1].SubItems[1].Text;
                    string itemMoveText2 = listView1.Items[index-1].SubItems[2].Text;
                    string itemMoveText3 = listView1.Items[index-1].SubItems[3].Text;
                    string itemMoveText4 = listView1.Items[index-1].SubItems[4].Text;
                    string itemMoveText5 = listView1.Items[index-1].SubItems[5].Text;
                    string itemMoveText6 = listView1.Items[index-1].SubItems[6].Text;

                    listView1.Items[index].SubItems[1].Text = itemMoveText1;
                    listView1.Items[index].SubItems[2].Text = itemMoveText2;
                    listView1.Items[index].SubItems[3].Text = itemMoveText3;
                    listView1.Items[index].SubItems[4].Text = itemMoveText4;
                    listView1.Items[index].SubItems[5].Text = itemMoveText5;
                    listView1.Items[index].SubItems[6].Text = itemMoveText6;

                    listView1.Items[index - 1].SubItems[1].Text = itemSelectText1;
                    listView1.Items[index - 1].SubItems[2].Text = itemSelectText2;
                    listView1.Items[index - 1].SubItems[3].Text = itemSelectText3;
                    listView1.Items[index - 1].SubItems[4].Text = itemSelectText4;
                    listView1.Items[index - 1].SubItems[5].Text = itemSelectText5;
                    listView1.Items[index - 1].SubItems[6].Text = itemSelectText6;
                    index--;
                    listView1.Items[index].Selected = true;
                    listView1.Items[index].Focused = true;
                }
            }
        }

        private void toolStripButton16_Click(object sender, EventArgs e)
        {
            // DOWN
            if (listView1.FocusedItem != null)
            {
                int index = listView1.FocusedItem.Index;
                if (index < listView1.Items.Count-1)
                {
                    string itemSelectText1 = listView1.Items[index].SubItems[1].Text;
                    string itemSelectText2 = listView1.Items[index].SubItems[2].Text;
                    string itemSelectText3 = listView1.Items[index].SubItems[3].Text;
                    string itemSelectText4 = listView1.Items[index].SubItems[4].Text;
                    string itemSelectText5 = listView1.Items[index].SubItems[5].Text;
                    string itemSelectText6 = listView1.Items[index].SubItems[6].Text;

                    string itemMoveText1 = listView1.Items[index + 1].SubItems[1].Text;
                    string itemMoveText2 = listView1.Items[index + 1].SubItems[2].Text;
                    string itemMoveText3 = listView1.Items[index + 1].SubItems[3].Text;
                    string itemMoveText4 = listView1.Items[index + 1].SubItems[4].Text;
                    string itemMoveText5 = listView1.Items[index + 1].SubItems[5].Text;
                    string itemMoveText6 = listView1.Items[index + 1].SubItems[6].Text;

                    listView1.Items[index].SubItems[1].Text = itemMoveText1;
                    listView1.Items[index].SubItems[2].Text = itemMoveText2;
                    listView1.Items[index].SubItems[3].Text = itemMoveText3;
                    listView1.Items[index].SubItems[4].Text = itemMoveText4;
                    listView1.Items[index].SubItems[5].Text = itemMoveText5;
                    listView1.Items[index].SubItems[6].Text = itemMoveText6;

                    listView1.Items[index + 1].SubItems[1].Text = itemSelectText1;
                    listView1.Items[index + 1].SubItems[2].Text = itemSelectText2;
                    listView1.Items[index + 1].SubItems[3].Text = itemSelectText3;
                    listView1.Items[index + 1].SubItems[4].Text = itemSelectText4;
                    listView1.Items[index + 1].SubItems[5].Text = itemSelectText5;
                    listView1.Items[index + 1].SubItems[6].Text = itemSelectText6;
                    index++;
                    listView1.Items[index].Selected = true;
                    listView1.Items[index].Focused = true;
                }
            }
        }

        private void toolStripButton17_Click(object sender, EventArgs e)
        {
            // UP
            if (listView2.FocusedItem != null)
            {
                int index = listView2.FocusedItem.Index;
                if (index > 0)
                {
                    string itemSelectText1 = listView2.Items[index].SubItems[1].Text;
                    string itemSelectText2 = listView2.Items[index].SubItems[2].Text;
                    string itemSelectText3 = listView2.Items[index].SubItems[3].Text;
                    string itemSelectText4 = listView2.Items[index].SubItems[4].Text;
                    string itemSelectText5 = listView2.Items[index].SubItems[5].Text;
                    
                    string itemMoveText1 = listView2.Items[index - 1].SubItems[1].Text;
                    string itemMoveText2 = listView2.Items[index - 1].SubItems[2].Text;
                    string itemMoveText3 = listView2.Items[index - 1].SubItems[3].Text;
                    string itemMoveText4 = listView2.Items[index - 1].SubItems[4].Text;
                    string itemMoveText5 = listView2.Items[index - 1].SubItems[5].Text;

                    listView2.Items[index].SubItems[1].Text = itemMoveText1;
                    listView2.Items[index].SubItems[2].Text = itemMoveText2;
                    listView2.Items[index].SubItems[3].Text = itemMoveText3;
                    listView2.Items[index].SubItems[4].Text = itemMoveText4;
                    listView2.Items[index].SubItems[5].Text = itemMoveText5;
                    
                    listView2.Items[index - 1].SubItems[1].Text = itemSelectText1;
                    listView2.Items[index - 1].SubItems[2].Text = itemSelectText2;
                    listView2.Items[index - 1].SubItems[3].Text = itemSelectText3;
                    listView2.Items[index - 1].SubItems[4].Text = itemSelectText4;
                    listView2.Items[index - 1].SubItems[5].Text = itemSelectText5;
                    index--;
                    listView2.Items[index].Selected = true;
                    listView2.Items[index].Focused = true;
                }
            }
        }

        private void toolStripButton18_Click(object sender, EventArgs e)
        {
            // DOWN
            if (listView2.FocusedItem != null)
            {
                int index = listView2.FocusedItem.Index;
                if (index < listView2.Items.Count - 1)
                {
                    string itemSelectText1 = listView2.Items[index].SubItems[1].Text;
                    string itemSelectText2 = listView2.Items[index].SubItems[2].Text;
                    string itemSelectText3 = listView2.Items[index].SubItems[3].Text;
                    string itemSelectText4 = listView2.Items[index].SubItems[4].Text;
                    string itemSelectText5 = listView2.Items[index].SubItems[5].Text;

                    string itemMoveText1 = listView2.Items[index + 1].SubItems[1].Text;
                    string itemMoveText2 = listView2.Items[index + 1].SubItems[2].Text;
                    string itemMoveText3 = listView2.Items[index + 1].SubItems[3].Text;
                    string itemMoveText4 = listView2.Items[index + 1].SubItems[4].Text;
                    string itemMoveText5 = listView2.Items[index + 1].SubItems[5].Text;

                    listView2.Items[index].SubItems[1].Text = itemMoveText1;
                    listView2.Items[index].SubItems[2].Text = itemMoveText2;
                    listView2.Items[index].SubItems[3].Text = itemMoveText3;
                    listView2.Items[index].SubItems[4].Text = itemMoveText4;
                    listView2.Items[index].SubItems[5].Text = itemMoveText5;

                    listView2.Items[index + 1].SubItems[1].Text = itemSelectText1;
                    listView2.Items[index + 1].SubItems[2].Text = itemSelectText2;
                    listView2.Items[index + 1].SubItems[3].Text = itemSelectText3;
                    listView2.Items[index + 1].SubItems[4].Text = itemSelectText4;
                    listView2.Items[index + 1].SubItems[5].Text = itemSelectText5;
                    index++;
                    listView2.Items[index].Selected = true;
                    listView2.Items[index].Focused = true;
                }
            }
        }

        private void валидаторJsonФайлаToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void обновитьChromeDriverToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                System.Diagnostics.Process.Start(@"https://chromedriver.chromium.org/");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Ошибка");
            }
        }

        private void выполнитьВсеТестыИзПапкиToolStripMenuItem_Click(object sender, EventArgs e)
        {
            folderBrowserDialog1.SelectedPath = Directory.GetCurrentDirectory();
            if (folderBrowserDialog1.ShowDialog() == DialogResult.OK)
            {
                //string folderName = Path.GetFileName(folderBrowserDialog1.SelectedPath);
                //executePackTest(folderName);
                executePackTest(folderBrowserDialog1.SelectedPath);
            }
        }

        private void toolStripButton19_Click(object sender, EventArgs e)
        {
            folderBrowserDialog1.SelectedPath = Directory.GetCurrentDirectory();
            if (folderBrowserDialog1.ShowDialog() == DialogResult.OK)
            {
                //string folderName = Path.GetFileName(folderBrowserDialog1.SelectedPath);
                //executePackTest(folderName);
                executePackTest(folderBrowserDialog1.SelectedPath);
            }
        }

        private void открытьПапкуErrorsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            string folderErrors = Directory.GetCurrentDirectory();
            folderErrors = folderErrors.Substring(0, folderErrors.Length - "editor".Length);
            folderErrors = folderErrors + "errors";

            try
            {
                Process.Start(folderErrors);
            }
            catch (Exception ex)
            {
                //MessageBox.Show("Не удалось открыть папку " + path, "Ошибка");
                consoleMessage(DateTime.Now + " Сообщение: Не удалось открыть папку " + folderErrors);
            }
        }

        public bool form7Close = true;
        private void инструкцияToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (form7Close == true)
            {
                Form7 form = new Form7();
                form.parentForm = this;
                form.Show();
            }
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            foreach (Process p in Process.GetProcesses())
            {
                if (p.ProcessName.ToString() == "chromedriver" || p.ProcessName.ToString() == "java")
                {
                    try
                    {
                        p.Kill();
                    }
                    catch (Exception ex)
                    {

                    }
                }
            }
        }

        private void кодировкаUTF8ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFile(UTF_8);
        }

        private void кодировкаUTF8BOMToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFile(UTF_8_BOM);
        }

        private void кодировкаWindows1251ToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFile(WINDOWS_1251);
        }

        private void кодировкаПоУмолчаниюToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            openFile(DEFAULT);
        }

        private void открытьТестКакToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void кодировкаUTF8ToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            saveFileAs(UTF_8);
        }

        private void кодировкаUTF8BOMToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            saveFileAs(UTF_8_BOM);
        }

        private void кодировкаWindows1251ToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            saveFileAs(WINDOWS_1251);
        }

        private void кодировкаПоУмолчаниюToolStripMenuItem_Click(object sender, EventArgs e)
        {
            saveFileAs(DEFAULT);
        }

        private void кодировкаUTF8ToolStripMenuItem3_Click(object sender, EventArgs e)
        {
            openFile(UTF_8);
        }

        private void кодировкаUTF8BOMToolStripMenuItem3_Click(object sender, EventArgs e)
        {
            openFile(UTF_8_BOM);
        }

        private void кодировкаWindows1251ToolStripMenuItem3_Click(object sender, EventArgs e)
        {
            openFile(WINDOWS_1251);
        }

        private void кодировкаПоУмолчаниюToolStripMenuItem3_Click(object sender, EventArgs e)
        {
            openFile(DEFAULT);
        }

        private void кодировкаUTF8ToolStripMenuItem2_Click(object sender, EventArgs e)
        {
            saveFileAs(UTF_8);
        }

        private void кодировкаUTF8BOMToolStripMenuItem2_Click(object sender, EventArgs e)
        {
            saveFileAs(UTF_8_BOM);
        }

        private void кодировкаWindows1251ToolStripMenuItem2_Click(object sender, EventArgs e)
        {
            saveFileAs(WINDOWS_1251);
        }

        private void кодировкаПоУмолчаниюToolStripMenuItem2_Click(object sender, EventArgs e)
        {
            saveFileAs(DEFAULT);
        }

        private void checkEncodingToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if (checkEncodingToolStripMenuItem.Checked) checkEncodingToolStripMenuItem.Checked = false;
            else checkEncodingToolStripMenuItem.Checked = true;
        }

        private void validatorJson()
        {
            try
            {
                if (toolStripStatusLabelFileName.Text != "...")
                {
                    string encoding = toolStripStatusLabelFileEncoding.Text;
                    StreamReader sr;
                    if (encoding == DEFAULT)
                    {
                        sr = new StreamReader(toolStripStatusLabelFileName.Text, Encoding.Default);
                    }
                    else if (encoding == UTF_8)
                    {
                        sr = new StreamReader(toolStripStatusLabelFileName.Text, new UTF8Encoding(false));
                    }
                    else if (encoding == UTF_8_BOM)
                    {
                        sr = new StreamReader(toolStripStatusLabelFileName.Text, new UTF8Encoding(true));
                    }
                    else if (encoding == WINDOWS_1251)
                    {
                        sr = new StreamReader(toolStripStatusLabelFileName.Text, Encoding.GetEncoding("Windows-1251"));
                    }
                    else
                    {
                        sr = new StreamReader(toolStripStatusLabelFileName.Text, Encoding.Default);
                    }
                    string jsonText = sr.ReadToEnd();
                    sr.Close();
                    TestJson test = JsonConvert.DeserializeObject<TestJson>(jsonText);
                    consoleMessage(DateTime.Now + " Проверка: синтаксис JSON файла - корректный");
                }
                else
                {
                    consoleMessage(DateTime.Now + " Сообщение: Невозможно выполнить проверку, нет открытого файла.");
                }
            }
            catch (Exception ex)
            {
                consoleMessage(DateTime.Now + " Ошибка JSON синтаксис: " + ex.Message);
            }
        }

        private void валидаторJsonToolStripMenuItem_Click(object sender, EventArgs e)
        {
            validatorJson();
        }
    }
}
