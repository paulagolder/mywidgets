package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class jswButtonandTextBox  extends jswHorizontalPanel implements ActionListener //extends 
{

	private static final long serialVersionUID = 1L;

	jswButton actionButton;
	String command;
	jswTextField datafield;
	String label;
	int bh=30;
	int bl=300;
	int hgap=10;
	
	

	public jswButtonandTextBox(ActionListener al, String alabel)
	{
		super(alabel,true, true);
		((jswLayout) this.getLayout()).setHgap(hgap);
		//setAlignmentX(Component.LEFT_ALIGNMENT);
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		command = alabel + " button";
		actionButton = new jswButton(this, alabel, command);
        label = alabel;
		add("  ",actionButton);
		actionlistener = al;
		datafield = new jswTextField(this,"textbox_1");
		//add(" FILLW MINWIDTH=50 ", datafield);
		add(" MINWIDTH=300 ",datafield);
		applyStyle();
	}
	
	 void applyStyle()
	  {
		
			
			datafield.setPreferredSize(new Dimension(600, 30));
			datafield.setMinimumSize(new Dimension(600, 30));
			datafield.setMaximumSize(new Dimension(600, 30));
			datafield.setBorder(jswStyle.makeLineBorder());
			datafield.setFont(style.getFont());
			datafield.setForeground(Color.black);
			datafield.setBackground(Color.LIGHT_GRAY);
			datafield.setBorder(jswStyle.makeLineBorder(Color.green,3));
		    int bl = label.length() * 8 + 30;
			actionButton.setFont(style.getFont());
			actionButton.setBackground(style.getColor("widgetcolor",jswStyle.defaultwidgetcolor));
			actionButton.setForeground(style.getForegroundcolor());
			actionButton.setBorder(style.getBorder());
			actionButton.setBorder(jswStyle.makeLineBorder(Color.red,3));
			int wd =style.getIntegerStyle("mywidth",bl);
			if(wd > bl) bl= wd;
			int ht =style.getIntegerStyle( "myheight",bh);
			if(ht > bh ) bh=ht;
			Dimension d = new Dimension(bl, bh);

			actionButton.setPreferredSize(d);
			actionButton.setMaximumSize(d);
			actionButton.setMinimumSize(d);
			setBorder(jswStyle.makeLineBorder(Color.green,3));
			//setPreferredSize(d);
		//	setPreferredSize(new Dimension(600+bl,bh));
			setPreferredSize(new Dimension(600, 40));	
			setMaximumSize(new Dimension(600, 40));
			setMinimumSize(new Dimension(600, 40));
	  }

	public String getCommand()
	{
		return command;
	}

	public String getText()
	{
		return datafield.getText();
	}

	public boolean isCommand(String com)
	{
		return command.equals(com);
	}

	public void setText(String text)
	{
		datafield.setText(text);
	}

}
