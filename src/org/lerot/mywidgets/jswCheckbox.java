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

public class jswCheckbox extends jswPanel
{

	private static final long serialVersionUID = 1L;
	JCheckBox check;
	int bh=30;
	int bl=30;
	


	public jswCheckbox(ActionListener al, String label)
	{
		super(label);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//int width = label.length() * 12 + 30;
		check = new JCheckBox(label);
		check.setSelected(false);
		check.setAlignmentX(Component.LEFT_ALIGNMENT);
		check.addActionListener(this);
		actionlistener = al;
		check.setActionCommand(label);
		add(check);
		//this.setBorder(jswStyle.makeLineBorder(Color.red, 3));
		//setMinimumSize(new Dimension(width, 40));
		applyStyle();
	}


    public void applyStyle(jswStyle style)
	{		
		check.setFont(style.getFont());
		check.setBackground(jswStyle.TRANSPARENT);
		check.setForeground(style.getForegroundcolor());
		check.setBorder(style.getBorder());		
	/*	FontMetrics fm = style.getFontMetrics();
		int fw = fm.charsWidth(check.getText().toCharArray(),0,check.getText().length());
		bl= fw +40;
		int wd =style.getIntegerStyle("mywidth",bl);
		if(wd > bl) bl= wd;
		int ht =style.getIntegerStyle( "myheight",bh);
		if(ht > bh ) bh=ht;
		Dimension d = new Dimension(bl, bh);
		check.setPreferredSize(d);
		check.setMaximumSize(d);
		check.setMinimumSize(d);
		this.setPanelBorder(style);		
		Border aborder = getBorder();
		if (aborder != null)
			padding = aborder.getBorderInsets(this);
		setBackground(jswStyle.TRANSPARENT);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d); */
		setBorder(style.getBorder());
	//	Border aborder = getBorder();
	//	if (aborder != null)
	//		padding = aborder.getBorderInsets(this);
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
		HashMap<String,String> am = jswPanel.createActionMap(this, e) ;
		am.put("status", mess);
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, am.toString());
		actionlistener.actionPerformed(event);
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