package org.lerot.mywidgets;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JToggleButton;
import javax.swing.JToggleButton.ToggleButtonModel;
import javax.swing.border.BevelBorder;

public class jswToggleButton extends jswPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JToggleButton button;


	public jswToggleButton(ActionListener al, String label)
	{
		this(al, label, label);
	}

	public jswToggleButton(ActionListener al, String label, String command)
	{
		super("TB" + label);
		button = new JToggleButton(label);
		button.setFont(new Font("SansSerif", Font.BOLD, 11));
		int l = label.length() * 8 + 30;
		Dimension d = new Dimension(l, 24);
		button.setPreferredSize(d);
		button.setMaximumSize(d);
		button.setMinimumSize(d);
		button.addActionListener(this);
		actionlistener = al;
		button.setActionCommand(command);
		add(button);
		applyStyles(button);
		button.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String mess ="";
	    if (button.isSelected())  
	    {
            button.setText("OFF");  
            mess = "Button Off";
	    }
        else  
        {
            button.setText("ON");  
            mess = "Button On";
        }	 
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, "value =" + button.getActionCommand()+mess);
		actionlistener.actionPerformed(event);

	}

}
