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
                StreamReader sr = new StreamReader(openFileDialog1.FileName, Encoding.UTF8);
                string jsonText = sr.ReadToEnd();
                sr.Close();

                TestJson test = JsonConvert.DeserializeObject<TestJson>(jsonText);
                string description = test.description;
                int port = test.port;
                MessageBox.Show(description + " : "+port.ToString());

                
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
    }
}
