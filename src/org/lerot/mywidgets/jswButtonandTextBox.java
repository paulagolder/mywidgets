package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class jswButtonandTextBox extends jswHorizontalPanel
{

	private static final long serialVersionUID = 1L;

	jswButton actionButton;
	String command;
	jswTextField datafield;

	public jswButtonandTextBox(ActionListener al, String label)
	{
		command = label + " button";
		actionButton = new jswButton(this, label, command);
		actionButton.setPreferredSize(new Dimension(200, 30));
		add("  ", actionButton);
		actionlistener = al;
		setPreferredSize(new Dimension(600, 40));
		datafield = new jswTextField(this, "a field for entry ");
		datafield.getStyle().setBackgroundcolor("red");
		datafield.setPreferredSize(new Dimension(400, 30));
		datafield.setBorder(jswStyle.makeLineBorder());
		datafield.setFont(new Font("SansSerif", Font.BOLD, 11));
		datafield.setForeground(Color.black);
		datafield.setBackground(Color.LIGHT_GRAY);
		add(" FILLW ", datafield);
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
