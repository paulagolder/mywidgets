/*
 * Created on 09-Jan-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Dimension;
<<<<<<< HEAD
import java.awt.event.ActionEvent;
=======
>>>>>>> master
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;

public class jswCheckbox extends jswPanel
{

	private static final long serialVersionUID = 1L;
	JCheckBox check;

	public jswCheckbox(String label)
	{
		super(label);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		int width = label.length() * 12 + 30;
		check = new JCheckBox(label);
		check.setSelected(false);
		check.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(check);
		setMinimumSize(new Dimension(width, 40));
	}

<<<<<<< HEAD
	public jswCheckbox(ActionListener al, String label)
=======
	public jswCheckbox(ActionListener c, String label)
>>>>>>> master
	{
		super(label);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		int width = label.length() * 12 + 30;
		check = new JCheckBox(label);
		check.setSelected(false);
		check.setAlignmentX(Component.LEFT_ALIGNMENT);
<<<<<<< HEAD
		check.addActionListener(this);
		actionlistener = al;
=======
		check.addActionListener(c);
>>>>>>> master
		check.setActionCommand(label);
		add(check);
		setMinimumSize(new Dimension(width, 40));
	}

<<<<<<< HEAD
	public void actionPerformed(ActionEvent e)
	{
		String mess ="";
	    if (check.isSelected())  
	    {
            mess = "checked";
	    }
        else  
        {  
            mess = "unchecked";
        }	 
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, getPanelname() + ":"+mess);
		actionlistener.actionPerformed(event);

=======
	public void addActionListener(ActionListener c, String actionlabel)
	{
		check.addActionListener(c);
		check.setActionCommand(actionlabel);
>>>>>>> master
	}

	@Override
	public boolean isSelected()
	{
		return check.isSelected();
	}

	@Override
	public void setEnabled(boolean e)
	{
		check.setEnabled(e);
	}

	public void setSelected(boolean b)
	{
		check.setSelected(b);
	}

	public String getLabel()
	{
		return check.getText();
	}
}