package org.lerot.mywidgets;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class jswDropDownBox extends jswHorizontalPanel
{

	private static final long serialVersionUID = 1L;
	JComboBox<String> datalist;
	JLabel label;
	DefaultComboBoxModel<String> listModel;

	public jswDropDownBox(String inLabel, boolean haslabel, boolean hasborder)
	{
		if (haslabel)
		{
			label = new JLabel();
			label.setText(inLabel);
			add("LEFT", label);
			label.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		listModel = new DefaultComboBoxModel<>();
		datalist = new JComboBox<>(listModel);
		datalist.setFont(new Font("SansSerif", Font.PLAIN, 12));
		datalist.setPreferredSize(new Dimension(100, 30));
		setName(inLabel);
		if (hasborder)
		{
			if (haslabel) setBorder(jswStyle.makeLineBorder());
			else
				setBorder(jswStyle.makecborder(inLabel));
		} else
			setBorder(jswStyle.makeborder());
		add("FILLW", datalist);
		// applyStyles(datalist);
		// applyStyles(label);

	}

	public jswDropDownBox(ActionListener c,String inlabel)
	{
		this(inlabel,false,false);
		this.addActionListener(c,inlabel);
	}

	public jswDropDownBox(ActionListener c,String inlabel, String action)
	{
		this(inlabel,true,true);
		this.addActionListener(c,action);
	}


	public int getItemCount()
	{
		return datalist.getItemCount();
	}

	public void addActionListener(ActionListener c)
	{
		datalist.addActionListener(c);
	}

	public void removeActionListener(ActionListener c)
	{
		datalist.removeActionListener(c);
		datalist.removeActionListener(c);
		int n = datalist.getActionListeners().length;
		
	}

	public void addActionListener(ActionListener c, String actionlabel)
	{
		datalist.addActionListener(c);
		datalist.setActionCommand(actionlabel);
	}

	public void addList(Vector<String> list)
	{
		ActionListener[] al = datalist.getActionListeners();
		//String ac = datalist.getActionCommand();
		if(al.length>0)datalist.removeActionListener(al[0]);
		if (list.size() > 0)
		{
			for (String element : list) {
				listModel.addElement(element);
			}
			datalist.setSelectedIndex(0);
		}
		if(al.length>0)datalist.addActionListener(al[0]);
		
	}
	

	public void addList(ArrayList<String> list)
	{
		if (list.size() > 0)
		{
			for (String element : list) {
				listModel.addElement(element);
			}
			datalist.setSelectedIndex(0);
		}
		
	}
	
	public void addItem(String listelement)
	{
		listModel.addElement(listelement);
	}

	private void addElement(String listelement)
	{
		listModel.addElement(listelement);
		datalist.setSelectedIndex(0);
	}

	@Override
	public Dimension getPreferredSize()
	{
		Dimension d1 = datalist.getPreferredSize();
		Dimension d2 = new Dimension(0, 0);
		if (label != null)
		{
			d2 = label.getPreferredSize();
		}
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
		} else
			return null;
	}

	@Override
	public boolean isSelected()
	{
		return false;
	}

	@Override
	public void setEnabled(boolean e)
	{
		label.setEnabled(e);
		datalist.setEnabled(e);
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
			for (String element : list) {
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
			for (String element : list) {
				listModel.addElement(element);
			}
			datalist.setSelectedIndex(0);
		}

	}

	public void addList(Map<String,Integer> list)
	{


		if (list.size() > 0)
		{
			for ( Entry<String, Integer> entry : list.entrySet())
			{
				listModel.addElement(entry.getKey());
			}
			datalist.setSelectedIndex(0);
		}

	}

	public void setList(Map<String,Integer> list)
	{
		listModel.removeAllElements();

		if (list.size() > 0)
		{
			for ( Entry<String, Integer> entry : list.entrySet())
			{
				listModel.addElement(entry.getKey());
			}
			datalist.setSelectedIndex(0);
		}

	}

	public void setSelected(String selitem)
	{
		datalist.setSelectedItem(selitem);
	}

	public void setPreferredize(Dimension dim)
	{

		datalist.setPreferredSize(dim);
	}

}
