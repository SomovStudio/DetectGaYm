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
            string folderBin = Directory.GetCurrentDirectory();
            folderBin = folderBin.Substring(0, folderBin.Length - "editor".Length);
            folderBin = folderBin + "bin";

            string path;
            string bat;
            path = Directory.GetCurrentDirectory();
            bat = "cd " + folderBin;
            bat += System.Environment.NewLine + "java -jar detect-gaym.jar " + parentForm.toolStripStatusLabelFileEncoding + " " + parentForm.toolStripStatusLabelFileName.Text;
            textBox1.Text = bat;

            path = Directory.GetCurrentDirectory();
            bat = "cd " + folderBin;
            bat += System.Environment.NewLine + "java -jar detect-gaym.jar " + parentForm.toolStripStatusLabelFileEncoding + " " + getFolderName();
            textBox3.Text = bat;
        }

        private string getFolderName()
        {
            

            string path = parentForm.toolStripStatusLabelFileName.Text;
            if (path != "...")
            {
                path = path.Substring(0, path.Length - this.fileName.Length - 1);
                return path;
            }
            return "tests";
        }
    }
}
