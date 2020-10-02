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
        public string fileName;
        public string folder;

        private void Form4_Load(object sender, EventArgs e)
        {
            string path = Directory.GetCurrentDirectory();
            string bat = "cd " + path;
            string fileTest = getFolderName() + "\\" + parentForm.fileName;
            if (File.Exists(fileTest))
            {
                bat += System.Environment.NewLine + "detect.bat \\" + fileTest;
            }
            else
            {
                bat += System.Environment.NewLine + "detect.bat " + parentForm.toolStripStatusLabelFileName.Text;
            }


            //string path = Directory.GetCurrentDirectory();
            //string bat = "cd " + path;
            //bat += System.Environment.NewLine + "detect.bat \\" + folder + "\\" + fileName;
            textBox1.Text = bat;

            bat = "cd " + path+"\\bin";
            //bat += System.Environment.NewLine + "java -jar detect-gaym.jar \\" + folder + "\\" + fileName;
            if (File.Exists(fileTest))bat += System.Environment.NewLine + "detect.bat \\" + fileTest;
            else bat += System.Environment.NewLine + "detect.bat " + parentForm.toolStripStatusLabelFileName.Text;
            textBox2.Text = bat;

            path = Directory.GetCurrentDirectory();
            bat = "cd " + path;
            bat += System.Environment.NewLine + "detect.bat \\" + folder;
            bat += System.Environment.NewLine;
            bat += System.Environment.NewLine + "или прямой вызов";
            bat += System.Environment.NewLine;
            bat += System.Environment.NewLine + "cd " + path + "\\bin";
            bat += System.Environment.NewLine + "java -jar detect-gaym.jar \\" + folder;
            textBox3.Text = bat;
        }

        private string getFolderName()
        {
            string path = parentForm.toolStripStatusLabelFileName.Text;
            if (path != "...")
            {
                path = path.Substring(0, path.Length - this.fileName.Length - 1);
                path = path.Substring(path.LastIndexOf("\\") + 1);
                return path;
            }
            return "tests";
        }
    }
}
