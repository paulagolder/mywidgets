package org.lerot.mywidgets;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
//import org.json.JSONObject;

public class jswDropDownBox extends jswWidget
{

    private static final long serialVersionUID = 1L;
    JComboBox<String> datalist;
    DefaultComboBoxModel<String> listModel;
    boolean hasborder;
    int bl = 100;
    int bh = 30;

    public jswDropDownBox(ActionListener al, String inLabel, boolean haslabel, boolean ahasborder, String actioncommand)
    {
        super(inLabel);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        hasborder = ahasborder;
        listModel = new DefaultComboBoxModel<>();
        datalist = new JComboBox<>(listModel);
        datalist.addActionListener(this);
        datalist.setActionCommand(actioncommand);
        setName(inLabel);
        setActionListener(al);
        setActionCommand(actioncommand);
        applyStyle();
        add(datalist);
        listModel.addListDataListener(new MyListDataListener());
    }

    public jswDropDownBox(ActionListener c, String inlabel)
    {
        this(c, inlabel, false, false,inlabel);
    }

    public jswDropDownBox(ActionListener c, String inlabel, String action)
    {
        this(c, inlabel, true, true,action);

    }

    public void addActionListener(ActionListener al, String actionmessage)
    {
        datalist.addActionListener(al);
        datalist.setActionCommand(actionmessage);
    }

    public void removeActionListener(ActionListener al)
    {
        datalist.removeActionListener(al);
    }

    public void applyStyle(jswStyle style)
    {
        datalist.setFont(style.getFont());
        int wd = style.getIntegerStyle("mywidth", bl);
        if (wd > bl) bl = wd;
        int ht = style.getIntegerStyle("myheight", bh);
        if (ht > bh) bh = ht;
        Dimension d = new Dimension(bl, bh);
        datalist.setPreferredSize(d);
        datalist.setMaximumSize(d);
        datalist.setMinimumSize(d);
        setBackground(jswStyle.TRANSPARENT);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
    }

    public int getItemCount()
    {
        return datalist.getItemCount();
    }

    /*public void addActionListener(ActionListener c)
    {
        actionlistener = c;
    }*/

    public void actionPerformed(ActionEvent e)
    {
        HashMap<String, String> am = jswPanel.createActionMap(this, e);
        am.put("quality", "selected");
        am.put("value", getSelectedValue());
        am.put("command", this.getTag());
        setSelection(getSelectedValue());
        Long t = System.currentTimeMillis() / 10000;
        int uniqueId = t.intValue();
        ActionEvent event = new ActionEvent(this, uniqueId, e.getActionCommand());
        if (getActionlistener() != null) getActionlistener().actionPerformed(event);
    }

    public void addList(Vector<String> list)
    {
        ActionListener[] al = datalist.getActionListeners();
        // String ac = datalist.getActionCommand();
        if (al.length > 0) datalist.removeActionListener(al[0]);
        if (list.size() > 0)
        {
            for (String element : list)
            {
                listModel.addElement(element);
            }
            datalist.setSelectedIndex(0);
        }

        if (al.length > 0) datalist.addActionListener(al[0]);
    }

    public void addList(ArrayList<String> list)
    {
        if (list.size() > 0)
        {
            for (String element : list)
            {
                listModel.addElement(element);
            }
            datalist.setSelectedIndex(0);

        }

    }

    public void addItem(String listelement)
    {
        listModel.addElement(listelement);
    }

    @Override
    public Dimension getPreferredSize()
    {
        Dimension d1 = datalist.getPreferredSize();
        Dimension d2 = new Dimension(0, 0);

        int width = d1.width + d2.width;
        int height = d1.height;
        if (d2.height > height) height = d2.height;
        return new Dimension(width, height);
    }

    public String getSelectedValue()
    {
        if (datalist.getSelectedItem() != null)
        {
            return (String) datalist.getSelectedItem();
        } else return null;
    }

    @Override
    public boolean isSelected()
    {
        return false;
    }

    public void setSelected(String selitem)
    {
        datalist.setSelectedItem(selitem);
    }

    @Override
    public void setEnabled(boolean e)
    {
        // label.setEnabled(e);
        if (e)
        {
            datalist.addActionListener(this);
        } else datalist.removeActionListener(this);
    }

    public void setList(String str)
    {
        listModel.removeAllElements();
        if (str != null)
        {
            listModel.addElement(str);
            datalist.setSelectedIndex(0);
        }
    }

    public void setList(String[] list)
    {
        listModel.removeAllElements();
        if (list.length > 0)
        {
            for (String element : list)
            {
                listModel.addElement(element);
            }
            datalist.setSelectedIndex(0);
        }
    }

    public void setList(Vector<String> list)
    {
        listModel.removeAllElements();
        if (list.size() > 0)
        {
            for (String element : list)
            {
                listModel.addElement(element);
            }
            datalist.setSelectedIndex(0);
        }
    }

    public void addList(Map<String, Integer> list)
    {
        if (list.size() > 0)
        {
            for (Entry<String, Integer> entry : list.entrySet())
            {
                listModel.addElement(entry.getKey());
            }
            datalist.setSelectedIndex(0);
        }
    }

    public void setList(Map<String, Integer> list)
    {
        listModel.removeAllElements();
		listModel.removeAllElements();
        if (list.size() > 0)
        {
            for (Entry<String, Integer> entry : list.entrySet())
            {
                listModel.addElement(entry.getKey());
            }
            datalist.setSelectedIndex(0);
        }
    }

    public void setPreferredize(Dimension dim)
    {
        datalist.setPreferredSize(dim);
    }

    class MyListDataListener implements ListDataListener
    {
        public void contentsChanged(ListDataEvent e)
        {
            // System.out.println("contentsChanged: " + e.getIndex0() + ", " + e.getIndex1()
            // );
        }

        public void intervalAdded(ListDataEvent e)
        {
            // System.out.println("intervalAdded: " + e.getIndex0() + ", " + e.getIndex1()
            // );
        }

        public void intervalRemoved(ListDataEvent e)
        {
            // System.out.println("intervalRemoved: " + e.getIndex0() + ", " + e.getIndex1()
            // );
        }
    }

}
