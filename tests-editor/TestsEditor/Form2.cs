using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace TestsEditor
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        public Form1 parentForm;
        public int index;

        private void button2_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(this.Text == "Add")
            {
                ListViewItem item;
                ListViewItem.ListViewSubItem subitem;
                item = new ListViewItem();
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = textBoxTitle.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = textBoxUrl.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = textBoxGaCategory.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = textBoxGaAction.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = textBoxGaLabel.Text;
                item.SubItems.Add(subitem);
                subitem = new ListViewItem.ListViewSubItem();
                subitem.Text = textBoxYmCode.Text;
                item.SubItems.Add(subitem);
                item.ImageIndex = 0;
                parentForm.listView1.Items.Add(item);
            }
            else
            {
                parentForm.listView1.Items[index].SubItems[1].Text = textBoxTitle.Text;
                parentForm.listView1.Items[index].SubItems[2].Text = textBoxUrl.Text;
                parentForm.listView1.Items[index].SubItems[3].Text = textBoxGaCategory.Text;
                parentForm.listView1.Items[index].SubItems[4].Text = textBoxGaAction.Text;
                parentForm.listView1.Items[index].SubItems[5].Text = textBoxGaLabel.Text;
                parentForm.listView1.Items[index].SubItems[6].Text = textBoxYmCode.Text;
            }
            
            Close();
        }
    }
}
