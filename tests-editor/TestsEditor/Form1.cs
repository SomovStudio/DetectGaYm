using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

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
    }
}
