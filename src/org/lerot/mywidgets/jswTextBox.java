package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class jswTextBox extends jswPanel implements ComponentListener
{

	private static final long serialVersionUID = 1L;
	//JLabel label;
	public JTextField textbox;
	Color oldbgcolor;
	private Color backgroundcolor;
	private Color alertcolor = Color.red;
	int bh=30;
	int bl=30;
	
	public jswTextBox(ActionListener al, String inLabel)
	{
		super(inLabel);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		addComponentListener(this);
		actionlistener = al;
		textbox = new JTextField();
		textbox.addActionListener( al);
		add( textbox);
		 applyStyle();

	}

	void applyStyle()
	{

		setBorder(style.getBorder());

		textbox.setFont(style.getFont());
		textbox.setForeground(style.getColor("foregroundColor",	Color.BLACK));
		textbox.setBackground(Color.pink);
		int wd =style.getIntegerStyle("mywidth",bl);
		if(wd > bl) bl= wd;
		int ht =style.getIntegerStyle( "myheight",bh);
		if(ht > bh ) bh=ht;
		Dimension d = new Dimension(bl+300, bh);
		textbox.setPreferredSize(d);
		textbox.setMaximumSize(d);
		textbox.setMinimumSize(d);
		setBackground(jswStyle.TRANSPARENT);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);		
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

/*	public void setLabel(String t)
	{
		label.setText(t);
	}*/

	@Override
	public void setBackground(Color c)
	{
		backgroundcolor = c;
	//	if (label != null) label.setBackground(c);
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
		//	if (label != null) label.setBackground(alertcolor);
			if (textbox != null) textbox.setBackground(alertcolor);
		} else
		{
			if (oldbgcolor != null)
			{
		//		if (label != null) label.setBackground(oldbgcolor);
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

	@Override
	public void componentResized(ComponentEvent e)
	{
	//	Dimension d = this.getSize();
	//	textbox.setSize(d);
		
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	
	
}
