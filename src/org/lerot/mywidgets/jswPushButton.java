package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
<<<<<<< HEAD
import java.awt.event.ActionEvent;
=======
>>>>>>> master
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

public class jswPushButton extends jswPanel
{
	private static final long serialVersionUID = 1L;
	JToggleButton button;

	public jswPushButton(ActionListener al, String label)
	{
		this(al, label, label);
	}

	public jswPushButton(ActionListener al, String label, String command)
	{
		super(label);
		button = new JToggleButton(label);
		button.setFont(new Font("SansSerif", Font.BOLD, 11));
		int l = label.length() * 8 + 30;
		Dimension d = new Dimension(l, 24);
		button.setPreferredSize(d);
		button.setMaximumSize(d);
		button.setMinimumSize(d);
<<<<<<< HEAD
		button.addActionListener(this);
		actionlistener = al;
=======
		button.addActionListener(al);
>>>>>>> master
		button.setActionCommand(command);
		add(button);
		setStyle();
		button.setVisible(true);
	}

<<<<<<< HEAD
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
			 
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, "value = buttonpressed ");
		actionlistener.actionPerformed(event);
		
	}

=======
	public void addActionListener(ActionListener al)
	{
		button.addActionListener(al);

	}

	public void addActionListener(ActionListener al, String actionmessage)
	{
		button.addActionListener(al);
		button.setActionCommand(actionmessage);
	}

	public String getText()
	{
		// TODO Auto-generated method stub
		return button.getText();
	}

	public void setActionCommand(String command)
	{
		button.setActionCommand(command);

	}
>>>>>>> master

	public void setSelected()
	{
		// button.t

	}

	public void setSelected(boolean b)
	{
		button.setSelected(b);

	}

	public void setStyle()
	{

<<<<<<< HEAD
		Font sfont = getStyles().getFont();
		button.setFont(sfont);
		button.setBorder(getStyles().getBorder());
		button.setForeground(getStyles().getColor("foregroundColor", Color.blue));
		button.setBackground(getStyles().getColor("backgroundColor", Color.red));

	}

	public void doStyling(jswStyle style)
	{
		Font sfont = style.getFont();
		button.setFont(sfont);
		button.setBorder(style.getBorder());
		button.setForeground(style.getColor("foregroundColor", Color.blue));
		button.setBackground(style.getColor("backgroundColor", Color.red));

		
	}

	public String getLabel()
	{
		return button.getText();
	}

	

=======
		Font sfont = styles.getFont();
		button.setFont(sfont);
		button.setBorder(styles.getBorder());
		button.setForeground(styles.getColor("foregroundColor", Color.blue));
		button.setBackground(styles.getColor("backgroundColor", Color.red));

	}

>>>>>>> master
}
