package org.lerot.mywidgets;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
//import org.lerot.mywidgets.jswLayout.settings;

public class jswOptionset extends jswPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	ButtonGroup bg;
	int no = 0;
	jswOption[] options;
	String commandroot;

	private boolean isvertical;


	public jswOptionset(ActionListener parentListener,String name, boolean isvertical,boolean border, boolean titledborder)
	{
		super(name);
		commandroot = name;
		actionlistener = parentListener;
        this.isvertical = isvertical;
		if (!isvertical)
		{
			setLayout(new jswHorizontalLayout());
			setStyleAttribute("verticallayoutstyle", jswLayout.MIDDLE);
		} else
		{
			setLayout(new jswVerticalLayout());
			setStyleAttribute("horizontallayoutstyle", jswLayout.MIDDLE);
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
		setStyleAttribute("gap",2);		
		setBackground(style.getBackgroundcolor());
		style.setBorderWidth(1);	
	    padding = new Insets(10, 20, 10, 10);   
		Font sfont = style.getTitleFont();
		this.setFont(sfont);
		setPanelBorder(style);
		this.setForeground(style.getColor("foregroundColor", Color.blue));
		this.setBackground(style.getColor("backgroundColor", Color.red));	
		setMinimumSize(getMinimumSize());
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
	
	
	@Override
	public Dimension getMinimumSize()
	{
		int width = 0;
		int height = 0;		
		Integer vgap = getStyle().getIntegerStyle("gap", 1);
		Integer hgap = getStyle().getIntegerStyle("gap", 1);
		//LayoutManager lm = this.getLayout();
		//settings s = ((jswLayout) lm).getSettings(this);
		jswStyle s = this.getStyle();
		int minwidth = 0;
			int mywidth=0;
			if (s.hasAttribute("WIDTH"))
			{
				mywidth = s.getInteger("WIDTH");
			}
			if (s.hasAttribute("MINWIDTH"))
			{
				minwidth = s.getInteger("MINWIDTH");
			}
			if (minwidth > mywidth ) mywidth = minwidth;
			int minheight = 0;
			int myheight=0;
			if (s.hasAttribute("HEIGHT"))
			{
				myheight = s.getInteger("HEIGHT");
			}
			if (s.hasAttribute("MINHEIGHT"))
			{
				minheight = s.getInteger("MINHEIGHT");
			}
			if (minheight > myheight ) myheight= minheight;
		if (isvertical)
		{
			for (int i = 0; i < no; i++)
			{
				jswOption on = options[i];
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
				jswOption on = options[i];
				Dimension d = on.getMinimumSize();
				width += d.width + hgap;
				if (height < d.height ) height = d.height;			
			}
			width = width + padding.left+padding.right ;	
			height = height + padding.top + padding.bottom;;
		}
		if (width < mywidth )width = mywidth;
		if(height <myheight) height=myheight;
		Dimension d = new Dimension(width,height);
		return d;		
	}
		
	
	@Override
	public Dimension getPreferredSize()
	{
		System.out.println("xy "+getMinimumSize());
		return getMinimumSize();
	}


}