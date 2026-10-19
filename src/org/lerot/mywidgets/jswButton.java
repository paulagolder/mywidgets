package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.Border;

public class jswButton extends jswWidget
{

	private static final long serialVersionUID = 1L;
	JButton button;
	int bh = 30;
	int bl = 30;

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
	    setActionListener(al);
		button.setActionCommand(command);
		add(button);
		setTag(command);
		applyStyle();
		button.setVisible(true);
		setSize(400,400);  //sets the width and height of the frame
	}

	@Override
    public void applyStyle(jswStyle style)
	{
		styleButton(button, style);
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

	public void setInternalActionListener(ActionListener al)
	{
		ActionListener[] listeners = button.getActionListeners();
		for (ActionListener listener : listeners) {
			button.removeActionListener(listener);
		}
		listeners = button.getActionListeners();
		button.addActionListener(al);
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
		setSelection(getTag());
		int uniqueId = t.intValue();
		//System.out.println(" in button");
		String command = e.getActionCommand();
		jswActionEvent event = new jswActionEvent(this, uniqueId, command);
		getActionlistener().actionPerformed(event);
	}


	public void listActionListeners()
	{
		//System.out.println(" jswbutton :"+this.getActionlistener());
		for ( ActionListener al : button.getActionListeners())
		{
			//System.out.println(" +button :"+al);
		}
	}
}
