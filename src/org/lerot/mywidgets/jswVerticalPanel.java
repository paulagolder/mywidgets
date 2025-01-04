package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.border.Border;

public class jswVerticalPanel extends jswContainer
{

	private static final long serialVersionUID = 1L;

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

}
