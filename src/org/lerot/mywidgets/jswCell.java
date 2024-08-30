package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Font;

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

	
/*	public void applyStyle(JComponent label, jswStyle usestyles)
	{
		jswStyle styles = usestyles;
		Font sfont = styles.getFont();
		if (label instanceof jswLabel)
		{
			JLabel innerlabel = ((jswLabel) label).getLabel();
			innerlabel.setFont(sfont);
			innerlabel.setForeground(styles.getColor("foregroundColor",
					Color.BLACK));
			innerlabel.setBackground(styles.getColor("backgroundColor",
					new Color(0, 0, 0, 0)));
		} else if (label instanceof JLabel)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswButton)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof JButton)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswContainer)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswTextBox)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));

			label.setBackground(styles.getColor("backgroundColor", Color.white));
			JTextField innerlabel = ((jswTextBox) label).getTextField();
			innerlabel.setFont(sfont);
			innerlabel.setForeground(styles.getColor("foregroundColor",
					Color.BLACK));
			innerlabel.setBackground(Color.pink);
		} else if (label instanceof JTextField)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(Color.pink);
			label.setPreferredSize(new Dimension(300, 20));
		} else if (label instanceof jswTable)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.green));

		} else if (label instanceof jswTextField)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(Color.green);

		} else if (label instanceof JToggleButton)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(Color.green);

		} else if (label instanceof jswCheckbox)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.green));

		} else
		{
			System.out.println(" not setting styles for "
					+ label.getClass().getSimpleName());
		}

	}*/

	@Override
	public void applyStyle()
	{
		//jswStyle styles = usestyles;
		//this.setBorder(styles.getCellBorder());
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
