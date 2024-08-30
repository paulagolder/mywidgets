package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

public class jswButtonandField extends jswHorizontalPanel
{

	private static final long serialVersionUID = 1L;
	jswButton actionButton;
	String command;

	jswLabel datafield;

	public jswButtonandField(ActionListener apanel, String label)
	{
		command = label + " button";
		actionButton = new jswButton(apanel, label, command);
		actionButton.setPreferredSize(new Dimension(200, 30));
		add(" MINWIDTH ",actionButton);
		setPreferredSize(new Dimension(600, 40));
		datafield = new jswLabel(" ");
		datafield.setPreferredSize(new Dimension(400, 30));
		datafield.setBorder(jswStyle.makeLineBorder());
		datafield.setFont(new Font("SansSerif", Font.BOLD, 11));
		datafield.setForeground(Color.green);
		datafield.setBackground(Color.blue);
		// datafield.setText(jstatGui.projectroot);
		add("FILLW", datafield);
		applyStyle();
	}
	
	void applyStyle()
	{
		actionButton.applyStyle(); 
		datafield.applyStyle()	;
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
