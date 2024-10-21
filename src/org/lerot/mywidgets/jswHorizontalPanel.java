package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.border.Border;

public class jswHorizontalPanel extends jswContainer
{

	private static final long serialVersionUID = 1L;

	public jswHorizontalPanel()
	{
		this("HP:",true,false);
	}

	public jswHorizontalPanel(String title, boolean border)
	{
		this(title,border,false);
	}

	
	public jswHorizontalPanel(String title, boolean border, boolean titledborder)
	{
		super(title);
		setLayout(new jswHorizontalLayout());
		setPanelname(title);
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
	    padding = new Insets(10, 10, 10, 10); 
		applyStyle();
	}


	public void applyStyle(jswStyle astyle)
	{
		
		Font sfont = astyle.getFont();
		this.setFont(sfont);
		this.setPanelBorder(astyle);
		Border aborder = getBorder();
		if (aborder != null)
			padding = aborder.getBorderInsets(this);

		this.setForeground(astyle.getColor("foregroundColor", Color.BLACK));
		this.setBackground(astyle.getColor("backgroundColor", Color.green));
		
	}
	

/*	public void addComponent(jswPanel c)
	{
		super.add(" ",c);
		int w = c.jswGetWidth();
		cwidth += w;
		if (c.jswGetHeight() > cheight) cheight = c.jswGetHeight();
		c.setAlignmentX(Component.LEFT_ALIGNMENT);
		c.setPreferredSize(new Dimension(cwidth, cheight));
	}*/

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

