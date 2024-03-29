package org.lerot.mywidgets;

import java.awt.Component;
//import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class jswOptionset extends jswPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	ButtonGroup bg;
	int no = 0;
	jswOption[] options;
	ActionListener al;



	public jswOptionset(String name, boolean isvertical, ActionListener al)
	{
		super(name);
		this.al = al;
		if (!isvertical)
		{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setAlignmentY(Component.TOP_ALIGNMENT);
			// setMaximumSize(new Dimension(0, 45));
		} else
		{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		this.setBorder(jswStyle.makeLineBorder());
		setBorder(jswStyle.makecborder(name));
		bg = new ButtonGroup();
		options = new jswOption[10];
		setName(name);
	}

	public jswOptionset(String name, boolean isvertical, boolean border)
	{
		super(name);
		if (!isvertical)
		{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setAlignmentY(Component.TOP_ALIGNMENT);
			// setMaximumSize(new Dimension(0, 45));
		} else
		{
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setAlignmentX(Component.LEFT_ALIGNMENT);
		}
		if (border)
		{
			this.setBorder(jswStyle.makeLineBorder());
			setBorder(jswStyle.makecborder(name));
		} else
		{

		}
		bg = new ButtonGroup();
		options = new jswOption[10];
		setName(name);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Object action = e.getSource();
		for (int i = 0; i < no; i++)
		{
			jswOption on = options[i];
			if (on.isSelected())
			{
				on.setEnabled(true);
			} else
			{
				on.setEnabled(false);
			}
		}
	}

	public jswOption addNewOption(String text, boolean vertical)
	{
		jswOption on = new jswOption(text, vertical);
		on.getButton().addActionListener(al);
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

}