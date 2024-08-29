package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;


public class jswButton extends jswPanel implements ActionListener
{

	private static final long serialVersionUID = 1L;
	JButton button;
	int bh=30;
	int bl=30;
	public jswButton(ActionListener al, String label)
	{
		this(al, label, label);
	}

	public jswButton(ActionListener al, String label, String command)
	{
		super(label);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		button = new JButton(label);
		button.addActionListener(this);
		actionlistener = al;
		button.setActionCommand(command);
		add(button);
		applyStyle();
		button.setVisible(true);
	}

	@Override
	void applyStyle(jswStyle style)
	{	
		//button.setFont(new Font("SansSerif", Font.BOLD, 9));
		int l = button.getText().length() * 8 + 30;
		if(l>bl)bl=l;	
		button.setFont(style.getFont());
	//	button.setBackground(style.getBackgroundcolor());
		button.setBackground(style.defaultwidgetcolor);
		button.setForeground(style.getForegroundcolor());
		button.setBorder(style.getBorder());
		int wd =style.getIntegerStyle("mywidth",bl);
		if(wd > bl) bl= wd;
		int ht =style.getIntegerStyle( "myheight",bh);
		if(ht > bh ) bh=ht;
		Dimension d = new Dimension(bl, bh);
		button.setPreferredSize(d);
		button.setMaximumSize(d);
		button.setMinimumSize(d);
		setBackground(jswStyle.TRANSPARENT);
		//setBackground(style.getBackgroundcolor());
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		//setBorder(jswStyle.makeLineBorder(Color.green, 4));
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

	
	@Override
	public void actionPerformed(ActionEvent e)
	{
			 
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, "value = buttonpressed ");
		actionlistener.actionPerformed(event);
		
	}


}
