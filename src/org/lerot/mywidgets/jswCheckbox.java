/*
 * Created on 09-Jan-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;

public class jswCheckbox extends jswWidget
{

	private static final long serialVersionUID = 1L;
	JCheckBox check;
	int bh=30;
	int bl=30;


	public jswCheckbox(ActionListener al, String label)
	{
		this(al,label,label);

	}

	public jswCheckbox(ActionListener al, String label, String actioncommand)
	{
		super(label);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//int width = label.length() * 12 + 30;
		check = new JCheckBox(label);
		check.setSelected(false);
		check.setAlignmentX(Component.LEFT_ALIGNMENT);
		check.addActionListener(this);
		setActionListener(al);
		setActionCommand(actioncommand);
		check.setActionCommand(label);
		add(check);
		applyStyle();
	}


    public void applyStyle(jswStyle style)
	{		
		check.setFont(style.getFont());
		check.setBackground(jswStyle.TRANSPARENT);
		check.setForeground(style.getForegroundcolor());
		check.setBorder(style.getBorder());
		setBorder(style.getBorder());
		setBackground(jswStyle.TRANSPARENT);
	}
	
	

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
		setSelection(mess);
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		jswActionEvent event = new jswActionEvent(this, uniqueId, getActionCommand());
		getActionlistener().actionPerformed(event);
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