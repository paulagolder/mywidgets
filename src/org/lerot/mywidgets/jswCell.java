package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class jswCell extends jswWidget implements  MouseListener
{

	private static final long serialVersionUID = 1L;
	private final ActionListener actionlistener;
	private int col;
	int colspan = 1;
	private int row;
	int width = 0;
	int height = 0;
	private Object actionSource;

	public jswCell(ActionListener al,int irow, int icol)
	{
		super("acell");
		setRow(irow);
		setCol(icol);
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
			innerlabel.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));
		}  else if (label instanceof jswButton)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));
		} else if (label instanceof jswContainer)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));
		} else if (label instanceof jswTextBox)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));
			JTextField innerlabel = ((jswTextBox) label).getTextField();
			innerlabel.setFont(sfont);
			innerlabel.setForeground(style.getColor("foregroundColor",Color.BLACK));
			innerlabel.setBackground(jswStyle.defaulttextboxcolor);
		}  else if (label instanceof jswTable)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));

		}  else if (label instanceof jswCheckbox)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));

		} else if (label instanceof jswDropDownBox)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));
		}
		else if (label instanceof jswOption)
		{
			label.setFont(sfont);
			label.setForeground(style.getColor("foregroundColor", Color.BLACK));
			label.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));
		}
		else
		{
			System.out.println(" not setting styles for "+ label.getClass().getSimpleName());
		}		
	}


	public void actionPerformed(ActionEvent e)
	{
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		setSelection(((jswWidget)e.getSource()).getSelection());
		ActionEvent event = new ActionEvent(this, uniqueId,e.getActionCommand());
		actionlistener.actionPerformed(event);
	}

	// space between a cell's border and its content
	public static final int LEFTPADDING = 3;

	// every cell border, however it is set, gets the padding inside it
	@Override
	public void setBorder(javax.swing.border.Border border)
	{
		javax.swing.border.Border padding = BorderFactory.createEmptyBorder(0, LEFTPADDING, 0, 0);
		super.setBorder(border == null ? padding : BorderFactory.createCompoundBorder(border, padding));
	}

	public void applyStyle(jswStyle style)
	{
		this.setBorder(style.getBorder());
		Font afont = style.getFont();
		this.setFont(afont);
		this.setForeground(style.getColor("foregroundColor", Color.BLACK));
		//this.setForeground(Color.red);
		this.setBackground(style.getColor("backgroundColor", jswStyle.defaultbackgroundcolor));
	}

	@Override
	public String toString()
	{
		return "CELL " + getRow() + " " + getCol() + "=" + this.getComponent(0).getClass().getSimpleName();
	}

	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getCol()
	{
		return col;
	}

	public void setCol(int col)
	{
		this.col = col;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		Object source = e.getSource();
		if (source instanceof jswPanel)
		{
			jswPanel panel = ((jswPanel) e.getSource());
			String pname = panel.getPanelname();
			Long t = System.currentTimeMillis() / 10000;
			int uniqueId = t.intValue();
            String command = "cellclick:"+this.getCol()+":"+this.getRow();
			ActionEvent event = new ActionEvent(this, uniqueId,command);
			actionlistener.actionPerformed(event);
		}
	}

	@Override
	public void mousePressed(MouseEvent e)
	{

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	public Object getActionSource()
	{
		return actionSource;
	}

	public void setActionSource(Object actionSource)
	{
		this.actionSource = actionSource;
	}
}
