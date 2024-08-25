/*
 * Created on 28 Apr 2007
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class jswTextBox extends jswPanel
{

	private static final long serialVersionUID = 1L;
	JLabel label;
	public JTextField textbox;
	Color oldbgcolor;
	private Color backgroundcolor;
	private Color alertcolor = Color.red;

	public jswTextBox(String inLabel)
	{
		super(inLabel);
		setLayout(new jswHorizontalLayout_b());
		if (inLabel != null && inLabel.length() > 1)
		{
			setName(inLabel);
			label = new JLabel(inLabel);
			add("LEFT", label);
			//label.setFont(new Font("SansSerif", Font.BOLD, 12));
			//setBorder(jswStyle.makeborder());
			applyStyle();
		}
		textbox = new JTextField();
		add("FILLW", textbox);
		// applyStyles(textbox);

	}

	void applyStyle()
	{
		setBorder(style.getBorder());
		label.setFont(style.getFont());
		label.setForeground(style.getColor("foregroundColor", Color.BLACK));

		label.setBackground(style.getColor("backgroundColor", Color.white));
		//JTextField innerlabel = getTextField();
		textbox.setFont(style.getFont());
		textbox.setForeground(style.getColor("foregroundColor",
				Color.BLACK));
		textbox.setBackground(Color.pink);
	}
	
	
	@Override
	public void addFocusListener(FocusListener fl)
	{
		textbox.addFocusListener(fl);
	}


	
	public void addActionListener(ActionListener al)
	{
		textbox.addActionListener(al);
		
	}
	
	

	@Override
	public void addKeyListener(KeyListener kl)
	{
		textbox.addKeyListener(kl);
	}

	public void clear()
	{
		textbox.setText("");
	}

	public int getInteger()
	{
		return Integer.parseInt(textbox.getText());
	}

	public String getText()
	{
		return textbox.getText();
	}

	public JTextField getTextField()
	{
		// TODO Auto-generated method stub
		return textbox;
	}

	@Override
	public boolean isSelected()
	{
		return false;
	}

	@Override
	public void repaint()
	{
		if (textbox != null) textbox.repaint();
	}

	@Override
	public void setEnabled(boolean e)
	{
		textbox.setEnabled(e);
	}

	public void setLabel(String t)
	{
		label.setText(t);
	}

	@Override
	public void setBackground(Color c)
	{
		backgroundcolor = c;
		if (label != null) label.setBackground(c);
		if (textbox != null) textbox.setBackground(c);
	}

	public void setAlertColor(Color ac)
	{
		alertcolor = ac;
	}

	public void setAlert(boolean b)
	{
		if (b)
		{
			if (oldbgcolor == null) oldbgcolor = backgroundcolor;
			if (label != null) label.setBackground(alertcolor);
			if (textbox != null) textbox.setBackground(alertcolor);
		} else
		{
			if (oldbgcolor != null)
			{
				if (label != null) label.setBackground(oldbgcolor);
				if (textbox != null) textbox.setBackground(oldbgcolor);
			}
		}
	}

	public void setSelected(boolean b)
	{

	}

	public void setText(int t)
	{
		textbox.setText(Integer.toString(t));
	}

	public void setText(String t)
	{
		textbox.setText(t);
	}

}
