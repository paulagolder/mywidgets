package org.lerot.mywidgets;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class jswButton extends jswPanel
{
	private static final long serialVersionUID = 1L;
	JButton button;
	int bh=16;
	int bl=30;

	public jswButton(ActionListener al, String label)
	{
		this(al, label, label);
	}

	public jswButton(ActionListener al, String label, String command)
	{
		super(label);
		button = new JButton(label);
		button.setFont(new Font("SansSerif", Font.BOLD, 9));
		int l = label.length() * 8 + 30;
		if(l>bl)bl=l;
		Dimension d = new Dimension(l, 16);
		button.setPreferredSize(d);
		button.setMaximumSize(d);
		button.setMinimumSize(d);
		button.addActionListener(al);
		button.setActionCommand(command);
		add(button);
		applyStyles(button);
		button.setVisible(true);
	}

	public void addActionListener(ActionListener al)
	{
		button.addActionListener(al);

	}

	public void addActionListener(ActionListener al, String actionmessage)
	{
		button.addActionListener(al);
		button.setActionCommand(actionmessage);
	}

	public void setActionCommand(String command)
	{
		button.setActionCommand(command);
	}

	@Override
	public void setEnabled(boolean b)
	{
		button.setEnabled(b);
		super.setEnabled(b);
	}

	public void doStyling(jswStyle style)
	{
		button.setFont(style.getFont());
		button.setBackground(style.getBackgroundcolor());
		button.setForeground(style.getForegroundcolor());
		int wd =style.getIntegerStyle("mywidth",bl);
		if(wd > bl) bl= wd;
		int ht =style.getIntegerStyle( "myheight",bh);
		if(ht > bh ) bh=ht;
		Dimension d = new Dimension(bl, bh);
		button.setPreferredSize(d);
		button.setMaximumSize(d);
		button.setMinimumSize(d);
		button.setPreferredSize(d);
	}

}
