using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace TestsEditor
{
    public partial class Form6 : Form
    {
        public Form6()
        {
            InitializeComponent();
        }

        public Form1 parentForm;

        private void Form6_Load(object sender, EventArgs e)
        {
            parentForm.form6Close = false;
        }

        private void Form6_FormClosed(object sender, FormClosedEventArgs e)
        {
            parentForm.form6Close = true;
        }
    }
}
