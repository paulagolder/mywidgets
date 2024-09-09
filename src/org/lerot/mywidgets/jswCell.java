package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class jswCell extends jswHorizontalPanel
{

	private static final long serialVersionUID = 1L;
	int col;
	int colspan = 1;
	int row;
	int width = 0;

	public jswCell(int irow, int icol)
	{
		row = irow;
		col = icol;
	}

	
	public void applyContentStyle(jswStyle style)
	{
		Component content = this.getComponent(0);
		jswPanel label=null;
		if( content instanceof jswPanel)
		{
			label = (jswPanel) content;
		}
		Font sfont = style.getFont();
		if (label instanceof jswLabel)
		{
			JLabel innerlabel = ((jswLabel) label).getLabel();
			innerlabel.setFont(sfont);
			innerlabel.setForeground(style.getColor("foregroundColor",
					Color.BLACK));
			innerlabel.setBackground(style.getColor("backgroundColor",
					new Color(0, 0, 0, 0)));
		}  else if (label instanceof jswButton)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswContainer)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswTextBox)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));

			label.setBackground(style.getColor("backgroundColor", Color.white));
			JTextField innerlabel = ((jswTextBox) label).getTextField();
			innerlabel.setFont(sfont);
			innerlabel.setForeground(style.getColor("foregroundColor",
					Color.BLACK));
			innerlabel.setBackground(Color.pink);
		}  else if (label instanceof jswTable)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", Color.green));

		}  else if (label instanceof jswCheckbox)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", Color.green));

		} else
		{
			System.out.println(" not setting styles for "
					+ label.getClass().getSimpleName());
		}

	}

	@Override
	public void applyStyle()
	{
		
		this.setBorder(style.getBorder());
		Font afont = style.getFont();
		this.setFont(afont);
		this.setForeground(style.getColor("foregroundColor", Color.BLACK));
		this.setBackground(style.getColor("backgroundColor", new Color(0, 0,
				0, 0)));
	}
	
	public void applyStyle(jswStyle style)
	{
		this.setBorder(style.getBorder());
		Font afont = style.getFont();
		this.setFont(afont);
		this.setForeground(style.getColor("foregroundColor", Color.BLACK));
		this.setBackground(style.getColor("backgroundColor", new Color(0, 0,
				0, 0)));
	}


	@Override
	public String toString()
	{
		return "CELL " + row + " " + col;
	}
}
