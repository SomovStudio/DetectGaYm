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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void openFolderToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(folderBrowserDialog1.ShowDialog() == DialogResult.OK)
            {
                ListViewItem item;
                ListViewItem.ListViewSubItem subitem;
                string[] fileEntries = Directory.GetFiles(folderBrowserDialog1.SelectedPath);
                foreach (string fileName in fileEntries)
                {
                    item = new ListViewItem();
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = fileName;
                    item.SubItems.Add(subitem);
                    listView1.Items.Add(item);
                }
            }
        }

        private void toolStripButton2_Click(object sender, EventArgs e)
        {
            if (folderBrowserDialog1.ShowDialog() == DialogResult.OK)
            {
                ListViewItem item;
                ListViewItem.ListViewSubItem subitem;
                string[] fileEntries = Directory.GetFiles(folderBrowserDialog1.SelectedPath);
                foreach (string fileName in fileEntries)
                {
                    item = new ListViewItem();
                    subitem = new ListViewItem.ListViewSubItem();
                    subitem.Text = fileName;
                    item.SubItems.Add(subitem);
                    listView1.Items.Add(item);
                }
            }
        }

        private void openFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            if(openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                ListViewItem item = new ListViewItem();
                ListViewItem.ListViewSubItem subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = openFileDialog1.FileName;
                item.SubItems.Add(subitem);
                listView1.Items.Add(item);
            }
        }

        private void toolStripButton3_Click(object sender, EventArgs e)
        {
            if (openFileDialog1.ShowDialog() == DialogResult.OK)
            {
                ListViewItem item = new ListViewItem();
                ListViewItem.ListViewSubItem subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = openFileDialog1.FileName;
                item.SubItems.Add(subitem);
                listView1.Items.Add(item);
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void toolStripButton6_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
