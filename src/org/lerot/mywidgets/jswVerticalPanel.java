package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.Border;

public class jswVerticalPanel extends jswContainer
{

	private static final long serialVersionUID = 1L;

	public jswVerticalPanel()
	{
		super("VP:");
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new jswVerticalLayout());
		// setBorder(setLineBorder(1));
	}

	public jswVerticalPanel(String title, boolean border, boolean titledborder)
	{
		super(title);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new jswVerticalLayout());
		setName(title);	
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
		
		applyStyle();
	}

	/*	public void addComponent(jswPanel c)
	{
		super.add(" ",c);
		int h = c.jswGetHeight();
		cheight += h;
		if (c.jswGetWidth() > cwidth) cwidth = c.jswGetWidth();
		c.setAlignmentX(Component.LEFT_ALIGNMENT);
		c.setPreferredSize(new Dimension(cwidth, cheight));
	}*/

	
	 void applyStyle(jswStyle astyle)
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
	
	

}
