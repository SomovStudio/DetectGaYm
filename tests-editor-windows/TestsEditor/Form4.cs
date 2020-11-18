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
            string path;
            string bat;
            path = Directory.GetCurrentDirectory();
            bat = "cd " + path;
            bat += System.Environment.NewLine + "cd..";
            bat += System.Environment.NewLine + "cd bin";
            bat += System.Environment.NewLine + "java -jar detect-gaym.jar " + parentForm.toolStripStatusLabelFileName.Text;
            textBox1.Text = bat;

            path = Directory.GetCurrentDirectory();
            bat = "cd " + path;
            bat += System.Environment.NewLine + "cd..";
            bat += System.Environment.NewLine + "cd bin";
            bat += System.Environment.NewLine + "java -jar detect-gaym.jar " + folder;
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
