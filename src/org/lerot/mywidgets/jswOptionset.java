package org.lerot.mywidgets;


import java.awt.Color;
import java.awt.Font;
//import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

public class jswOptionset extends jswPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	ButtonGroup bg;
	int no = 0;
	jswOption[] options;
	int padding = 10;
	String commandroot;


	public jswOptionset(ActionListener parentListener,String name, boolean isvertical,boolean border, boolean titledborder)
	{
		super(name);
		commandroot = name;
		actionlistener = parentListener;

		if (!isvertical)
		{
			setLayout(new jswHorizontalLayout());
		} else
		{
			setLayout(new jswVerticalLayout());
		}
		
		if(titledborder)
		{
			style.setBorderStyle(jswStyle.TITLEDBORDER);
		}else if (border)
		{
			style.setBorderStyle(jswStyle.LINEBORDER);
		}
		else
		{
			style.setBorderStyle(jswStyle.NOBORDER);
		}
		bg = new ButtonGroup();
		options = new jswOption[10];
		setName(name);
		applyStyle();
	}
	
	public jswOptionset(ActionListener parentListener,String name, boolean isvertical,boolean border)
	{
	  this (parentListener,name,isvertical,border,false);
	}

	public void applyStyle(jswStyle style)
	{			
		padding = style.getIntegerStyle("padding",padding);
		padding = 20;
		setBackground(style.getBackgroundcolor());
		setPanelBorder();
			
	}




	@Override
	public void actionPerformed(ActionEvent e)
	{


		for (int i = 0; i < no; i++)
		{
			jswOption on = options[i];
			if (on.isSelected())
			{

				//on.setEnabled(true);
			} else
			{
				//on.setEnabled(false);
			}
		}
		actionlistener.actionPerformed(e);
	}
	
	public jswOption addNewOption(String text, boolean vertical)
	{
		jswOption on = new jswOption(this,text, vertical);		
		on.getButton().setActionCommand(commandroot + ":" + text);
		on.getButton().addActionListener(this);
		on.setBackground(getStyle().getColor("backgroundColor", Color.red));

		bg.add(on.getButton());
		options[no] = on;
		on.setTag(text);
		add(on);
		no = no + 1;
		return on;
	}

	public String getSelected()
	{
		for (int i = 0; i < no; i++)
		{
			jswOption on = options[i];
			if (on.isSelected()) return on.getText();
		}
		return "";
	}

	public String getSelectedTag()
	{

		for (int i = 0; i < no; i++)
		{
			jswOption on = options[i];
			if (on.isSelected()) return on.getTag();
		}
		return "";
	}

	@Override
	public boolean isSelected()
	{
		return true;
	}

	public boolean isSelected(String selvalue)
	{
		String selectedstring = getSelected();
		if (selectedstring.equalsIgnoreCase(selvalue)) return true;
		else
			return false;
	}

	@Override
	public void setEnabled(boolean e)
	{
		for (int i = 0; i < no; i++)
		{
			jswPanel on = options[i];
			on.setEnabled(e);
		}
	}

	public void setSelected(String j)
	{
		for (int i = 0; i < no; i++)
		{
			jswOption on = options[i];
			String ontext = on.getTag();
			if (ontext.equalsIgnoreCase(j)) on.setSelected();
		}
	}

	public void setSelected(int i)
	{
		jswOption on = options[i];
		on.setSelected();
	}


	public void doStyling(jswStyle style)
	{
		Font sfont = style.getFont();
		this.setFont(sfont);
		this.setBorder(style.getBorder());
		this.setForeground(style.getColor("foregroundColor", Color.blue));
		this.setBackground(style.getColor("backgroundColor", Color.red));	
	}


}