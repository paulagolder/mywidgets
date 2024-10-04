package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class jswCell extends jswPanel //jswHorizontalPanel
{

	private static final long serialVersionUID = 1L;
	int col;
	int colspan = 1;
	int row;
	int width = 0;

	public jswCell(ActionListener al,int irow, int icol)
	{
		super("acell");
		row = irow;
		col = icol;
		actionlistener = al;
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}

	
	@Override
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

		} else if (label instanceof jswDropDownBox)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", Color.green));
		}
		else if (label instanceof jswOption)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", Color.green));
		}
		else
		{
			System.out.println(" not setting styles for "
					+ label.getClass().getSimpleName());
		}		
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		HashMap<String,String> am = jswPanel.createActionMap(this, e);
		am.put("column", (" "+this.col));
		am.put("row", (" "+this.row));
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId,am.toString());
		actionlistener.actionPerformed(event);

	}

	public void applyStyle(jswStyle style)
	{
		this.setBorder(style.getBorder());
		Font afont = style.getFont();
		this.setFont(afont);
		this.setForeground(style.getColor("foregroundColor", Color.BLACK));
		//this.setForeground(Color.red);
		this.setBackground(style.getColor("backgroundColor", new Color(0, 0,
				0, 0)));
	}


	@Override
	public String toString()
	{
		return "CELL " + row + " " + col + "="+ this.getComponent(0).toString();
	}
}
