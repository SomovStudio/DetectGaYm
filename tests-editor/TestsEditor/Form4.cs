using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace TestsEditor
{
    public partial class Form4 : Form
    {
        public Form4()
        {
            InitializeComponent();
        }

        public Form1 parentForm;

        private void Form4_Load(object sender, EventArgs e)
        {
            string path = Directory.GetCurrentDirectory();
            string bat = "cd " + path;
            bat += System.Environment.NewLine + "detect.bat \\tests\\" + parentForm.fileName;
            textBox1.Text = bat;

            bat = "cd " + path+"\\bin";
            bat += System.Environment.NewLine + "java -jar detect-gaym.jar \\tests\\" + parentForm.fileName;
            textBox2.Text = bat;

            path = Directory.GetCurrentDirectory();
            bat = "cd " + path;
            bat += System.Environment.NewLine + "detect.bat \\tests";
            bat += System.Environment.NewLine;
            bat += System.Environment.NewLine + "или прямой вызов";
            bat += System.Environment.NewLine;
            bat += System.Environment.NewLine + "cd " + path + "\\bin";
            bat += System.Environment.NewLine + "java -jar detect-gaym.jar \\tests";
            textBox3.Text = bat;
        }
    }
}
