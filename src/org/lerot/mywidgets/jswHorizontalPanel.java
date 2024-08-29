package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class jswHorizontalPanel extends jswContainer
{

	private static final long serialVersionUID = 1L;

	public jswHorizontalPanel()
	{
		this("HP:",true,false);
	}

	public jswHorizontalPanel(String title, boolean border, boolean titledborder)
	{
		super(title);
		setLayout(new jswHorizontalLayout());
		setPanelname(title);
		if(titledborder)
		{
			setBorder(jswStyle.makecborder(title));
			//Insets pad = getBorderOffset();
			this.setPadding(20,5,5,5);
			//setBorder(BorderFactory.createEmptyBorder(pad.top, pad.left, pad.bottom, pad.right));	
			//setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));	
		}else if (border)
		{
			setBorder(jswStyle.makeLineBorder(Color.pink, 2));
			Border thisborder = getBorder();
			int thk = ((LineBorder)thisborder).getThickness();
			this.setPadding(((LineBorder)thisborder).getThickness()+1);
		}
		else
		{
			setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
	}

	

	public void addComponent(jswPanel c)
	{
		super.add(c);
		int w = c.jswGetWidth();
		cwidth += w;
		if (c.jswGetHeight() > cheight) cheight = c.jswGetHeight();
		c.setAlignmentX(Component.LEFT_ALIGNMENT);
		c.setPreferredSize(new Dimension(cwidth, cheight));
	}

	@Override
	public void setEnabled(boolean e)
	{
		int nc = getComponentCount();
		for (int i = 0; i < nc; i++)
		{
			Component c = getComponent(i);
			c.setEnabled(e);
		}

	}

	
}

