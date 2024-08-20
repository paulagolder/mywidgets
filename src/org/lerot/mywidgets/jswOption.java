package org.lerot.mywidgets;

<<<<<<< HEAD
import java.awt.Color;
//import java.awt.Component;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
=======
//import java.awt.Component;
import java.awt.Component;
import java.awt.Dimension;
>>>>>>> master
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class jswOption extends jswPanel
{

	private static final long serialVersionUID = 1L;
	JPanel box;
	private JRadioButton button;
	int compheight = 0;
	boolean vertical = true;

<<<<<<< HEAD
	public jswOption(ActionListener al,String text, boolean vertical)
=======
	public jswOption(String text, boolean vertical)
>>>>>>> master
	{
		super(text);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
<<<<<<< HEAD
		button = new JRadioButton(text);
		button.setSelected(false);
		add(button);
		button.addActionListener(this);
		actionlistener = al;
		this.vertical = vertical;
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.getStyle().setBackgroundcolor("transparent");		

	}



	
=======
		setButton(new JRadioButton(text));
		getButton().setSelected(false);
		add(getButton());
		this.vertical = vertical;
		getButton().setAlignmentX(Component.LEFT_ALIGNMENT);
		box = new JPanel();
		box.setAlignmentX(Component.LEFT_ALIGNMENT);
		if (vertical) box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		else
			box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
		add(box);
	}

	public void addComponent(jswPanel c)
	{
		box.add(c);
		c.setAlignmentX(Component.LEFT_ALIGNMENT);
		if (vertical)
		{
			compheight += c.jswGetHeight();
			setPreferredSize(new Dimension(0, compheight));
		} else
		{
			if (c.jswGetHeight() > compheight) compheight = c.jswGetHeight();
			setPreferredSize(new Dimension(0, compheight));
		}
	}

	public void addActionListener(ActionListener al, String command)
	{
		getButton().addActionListener(al);
		getButton().setActionCommand(command);
	}

>>>>>>> master
	public String getText()
	{
		return getButton().getText();
	}

	@Override
	public boolean isSelected()
	{
		return getButton().isSelected();
	}

	@Override
	public void setEnabled(boolean e)
	{
		int nc = box.getComponentCount();
		for (int i = 0; i < nc; i++)
		{
			Component c = box.getComponent(i);
			c.setEnabled(e);
		}
		getButton().setEnabled(e);
	}

	public void setSelected()
	{
		setSelected(true);

	}

	public void setSelected(boolean sel)
	{
		getButton().setSelected(sel);
	}

	public void setText(String string)
	{
		getButton().setText(string);

	}

	public JRadioButton getButton() {
		return button;
	}

	public void setButton(JRadioButton button) {
		this.button = button;
	}

<<<<<<< HEAD
	public void doStyling(jswStyle style)
	{	
			Font sfont = style.getFont();
			button.setFont(sfont);
			button.setBorder(style.getBorder());
			button.setForeground(style.getColor("foregroundColor", Color.blue));
			button.setBackground(style.getColor("backgroundColor", Color.red));			
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String mess ="";
	    if (button.isSelected())  
	    {
           // button.setText("OFF");  
            mess = "option ON";
	    }
        else  
        {
           // button.setText("ON");  
            mess = "option OFF";
        }	 
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, "value =" + button.getActionCommand()+mess);
		actionlistener.actionPerformed(event);

	}

=======
>>>>>>> master
}