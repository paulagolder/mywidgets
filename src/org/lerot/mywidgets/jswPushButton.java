package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;

public class jswPushButton extends jswPanel
{
	private static final long serialVersionUID = 1L;
	JToggleButton button;
	int bh=30;
	int bl=30;
	
	public jswPushButton(ActionListener al, String label)
	{
		this(al, label, label);
	}

	public jswPushButton(ActionListener al, String label, String command)
	{
		super(label);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		button = new JToggleButton(label);
		button.addActionListener(this);
		actionlistener = al;
		button.setActionCommand(command);
		add(button);
		applyStyle();
		button.setVisible(true);
	}


	

	@Override
	public void actionPerformed(ActionEvent e)
	{
			 
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, "value = buttonpressed ");
		actionlistener.actionPerformed(event);
		
	}



	public void setSelected()
	{
		// button.t

	}

	public void setSelected(boolean b)
	{
		button.setSelected(b);

	}

	public void applyStyle(jswStyle style)
	{	
		int l = button.getText().length() * 8 + 30;
		if(l>bl)bl=l;	
		button.setFont(style.getFont());
		button.setBackground(jswStyle.defaultwidgetcolor);
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
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);		
	}

	

	public String getLabel()
	{
		return button.getText();
	}

	


}
