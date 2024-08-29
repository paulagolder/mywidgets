package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
//import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class jswPushButtonset extends jswPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	ActionListener actionlistener;
	ButtonGroup bg;
	int no = 0;
	int padding = 10;
	jswPushButton[] options;
	String commandroot;

	private boolean vertical;

	public jswPushButtonset(ActionListener parentListener, String name,
			boolean isvertical, boolean border)
	{
		super("PBS:" + name);
		commandroot = name;
		vertical =isvertical;
		actionlistener = parentListener;
		if (!isvertical)
		{
			//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			//setAlignmentY(Component.TOP_ALIGNMENT);
			setLayout(new jswHorizontalLayout());
			// setMaximumSize(new Dimension(0, 45));
		} else
		{
			//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			//setAlignmentX(Component.CENTER_ALIGNMENT);
			setLayout(new jswVerticalLayout());
		}

		style.setBorderWidth(1);
		bg = new ButtonGroup();
		options = new jswPushButton[10];
		setName(name);
		applyStyle();
	}

	public void applyStyle(jswStyle style)
	{			
		padding = style.getIntegerStyle("padding",padding);
		padding = 20;
		setBackground(style.getBackgroundcolor());
		setBorder(style.getBorder());
			
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Object action = e.getSource();
		for (int i = 0; i < no; i++)
		{
			jswPushButton on = options[i];
			if (on.isSelected())
			{
				// on.setEnabled(true);
			} else
			{
				// on.setEnabled(false);
			}
		}
		actionlistener.actionPerformed(e);
	}

	public jswPushButton addNewOption(String text)
	{
		int pad = padding;
		
		/*	if(no==0)
		{
			pad = padding/2;
		}
	
			if(vertical)
			     add(Box.createVerticalStrut(padding));
			else
				add(Box.createHorizontalStrut(padding));	
		*/
		jswPushButton on = new jswPushButton(this, text);
		on.button.addActionListener(this);
		on.button.setActionCommand(commandroot + ":" + text);
		bg.add(on.button);
		options[no] = on;
		add(on);
		
		no = no + 1;
		return on;
	}

	public String getSelected()
	{
		for (int i = 0; i < no; i++)
		{
			jswPushButton on = options[i];
			if (on.isSelected()) return on.getLabel();
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

	public void setSelected(String value)
	{
		for (int i = 0; i < no; i++)
		{
			jswPushButton on = options[i];

			if (on.getLabel().equalsIgnoreCase(value))

			{
				on.setSelected(true);
				on.setEnabled(true);
			} else
			{
				on.setEnabled(false);
				on.setSelected(false);
			}

		}

	}

}