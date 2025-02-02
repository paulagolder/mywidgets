package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class jswCell extends jswPanel implements MouseListener
{

	private static final long serialVersionUID = 1L;
	private int col;
	int colspan = 1;
	private int row;
	int width = 0;
	int height = 0;

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
			innerlabel.setForeground(style.getColor("foregroundColor",Color.BLACK));
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
			System.out.println(" not setting styles for "+ label.getClass().getSimpleName());
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		HashMap<String,String> am = jswPanel.createActionMap(this, e);
		am.put("source","jswCell");
		am.put("panelname",this.getPanelname());
		am.put("column", " " + this.getCol());
		am.put("row"," " + this.getRow());
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
		return "CELL " + getRow() + " " + getCol() + "=" + this.getComponent(0).toString();
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
			HashMap<String, String> am = jswPanel.createActionMap(this, e);
			am.put("column", (" " + this.getCol()));
			am.put("row", (" " + this.getRow()));
			am.put("command", "mouseclick");
			if (source instanceof jswLabel)
			{
				String text = ((jswLabel) source).getText();
				am.put("cellcontent", text);
				am.put("jswLabel",((jswPanel) source).getPanelname());
			}
			else if (source instanceof jswTextBox)
			{
				String text = ((jswTextBox) source).getText();
				am.put("cellcontent", text);
				am.put("jswTextbox",((jswPanel) source).getPanelname());
			}
			else
			{
				am.put(panel.getClass().getSimpleName(), ((jswPanel)panel).getPanelname());
			}
			Long t = System.currentTimeMillis() / 10000;
			int uniqueId = t.intValue();
			//System.out.println("+++"+am.toString());
			ActionEvent event = new ActionEvent(this, uniqueId, am.toString());
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
}
