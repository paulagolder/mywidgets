package org.lerot.mywidgets;

import java.awt.Dimension;
import java.awt.LayoutManager;
//import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.border.Border;
import org.lerot.mywidgets.jswLayout.settings;

public class jswPushButtonset extends jswPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	ActionListener actionlistener;
	ButtonGroup bg;
	int no = 0;
	jswPushButton[] buttons;
	String commandroot;

	private boolean isvertical;

	public jswPushButtonset(ActionListener parentListener, String name,
			boolean isvertical, boolean border, boolean titledborder)
	{
		super( name);
		commandroot = name;
		this.isvertical =isvertical;
		actionlistener = parentListener;
		if (!isvertical)
		{
			setLayout(new jswHorizontalLayout());
		} else
		{
			setLayout(new jswVerticalLayout());
		}
		style.putAttribute("verticallayoutstyle" , jswLayout.MIDDLE);
		if(titledborder)
		{
			style.setBorderStyle(jswStyle.TITLEDBORDER);
			style.setBorderWidth(1);
		}else if (border)
		{
			style.setBorderStyle(jswStyle.LINEBORDER);
			style.setBorderWidth(1);
		}
		else
		{
			style.setBorderStyle(jswStyle.NOBORDER);
		}
		bg = new ButtonGroup();
		buttons = new jswPushButton[10];
		setName(name);
		applyStyle();
	}
	

	public jswPushButtonset(ActionListener parentListener, String name,
			boolean isvertical, boolean hasborder)
	{
		this(parentListener,name,isvertical,hasborder,false);
	}

	public void applyStyle(jswStyle style)
	{			
		setBackground(style.getBackgroundcolor());
		setPanelBorder(style);
		setBorder(style.getBorder());;			
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < no; i++)
		{
			jswPushButton on = buttons[i];
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

	public jswPushButton addNewButton(String text)
	{
		jswPushButton on = new jswPushButton(this, text);
		on.button.addActionListener(this);
		on.button.setActionCommand(commandroot + ":" + text);
		bg.add(on.button);
		buttons[no] = on;
		add(on);		
		no = no + 1;
		return on;
	}

	public String getSelected()
	{
		for (int i = 0; i < no; i++)
		{
			jswPushButton on = buttons[i];
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
			jswPanel on = buttons[i];
			on.setEnabled(e);
		}
	}

	public void setSelected(String value)
	{
		for (int i = 0; i < no; i++)
		{
			jswPushButton on = buttons[i];

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
	
	@Override
	public Dimension getMinimumSize()
	{
		int width = 0;
		int height = 0;		
		Integer vgap = getStyle().getIntegerStyle("gap", 1);
		Integer hgap = getStyle().getIntegerStyle("gap", 1);
		LayoutManager lm = this.getLayout();
		settings s = ((jswLayout) lm).getSettings(this);
		int minwidth = 0;
			int mywidth=0;
			if (s.containsKey("WIDTH"))
			{
				mywidth = s.getInteger("WIDTH");
			}
			if (s.containsKey("MINWIDTH"))
			{
				minwidth = s.getInteger("MINWIDTH");
			}
			if (minwidth > mywidth ) mywidth = minwidth;
			int minheight = 0;
			int myheight=0;
			if (s.containsKey("HEIGHT"))
			{
				myheight = s.getInteger("HEIGHT");
			}
			if (s.containsKey("MINHEIGHT"))
			{
				minheight = s.getInteger("MINHEIGHT");
			}
			if (minheight > myheight ) myheight= minheight;
		if (isvertical)
		{
			for (int i = 0; i < no; i++)
			{
				
				jswPushButton on =  buttons[i];
				Dimension d = on.getMinimumSize();
				height += d.height + vgap;
				if (width < d.width ) width = d.width;			
			}
			width = width + padding.left+padding.right;
			height = height + padding.top + padding.bottom;
		} else
		{
			for (int i = 0; i < no; i++)
			{
				jswPushButton on =  buttons[i];
				Dimension d = on.getMinimumSize();
				width += d.width + hgap;
				if (height < d.height ) height = d.height;			
			}
			width = width + padding.left+padding.right ;		
			height = height + padding.top + padding.bottom;
		}
		if (width < mywidth )width = mywidth;
		if(height <myheight) height=myheight;
		Dimension d = new Dimension(width,height);
		return d;		
	}
		
	
	@Override
	public Dimension getPreferredSize()
	{
		return getMinimumSize();
	}



}