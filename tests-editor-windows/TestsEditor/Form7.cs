using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace TestsEditor
{
    public partial class Form7 : Form
    {
        public Form7()
        {
            InitializeComponent();
        }

        public Form1 parentForm;

        private void Form7_Load(object sender, EventArgs e)
        {
            parentForm.form7Close = false;
            richTextBox1.LoadFile("help.rtf");
        }

        private void Form7_FormClosed(object sender, FormClosedEventArgs e)
        {
            parentForm.form7Close = true;
        }
    }
}
