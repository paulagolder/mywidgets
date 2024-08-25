package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;

public class jswToggleButton extends jswPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	JToggleButton button;
	private String label;


	public jswToggleButton(ActionListener al, String alabel)
	{
		this(al, alabel, alabel);
		
	}

	public jswToggleButton(ActionListener al, String alabel, String command)
	{
		super("TB" + alabel);
		label=alabel;
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		button = new JToggleButton(label);
		button.setFont(new Font("SansSerif", Font.BOLD, 11));
		int bl = label.length() * 8 + 30;
		Dimension d = new Dimension(bl, 24);
		button.setPreferredSize(d);
		button.setMaximumSize(d);
		button.setMinimumSize(d);
		button.addActionListener(this);
		actionlistener = al;
		button.setActionCommand(command);
		add(button);
		//this.setBorder(jswStyle.makeLineBorder(Color.red, 3));
		applyStyle();
		button.setVisible(true);
	}

  void applyStyle()
  {
	  int bl = label.length() * 8 + 30;
	  int bh= 5;
	  //to do fm
	  button.setFont(style.getFont());
		button.setBackground(style.getBackgroundcolor());
		button.setForeground(style.getForegroundcolor());
		button.setBorder(style.getBorder());
		int wd =style.getIntegerStyle("mywidth",bl);
		if(wd > bl) bl= wd;
		int ht =style.getIntegerStyle( "myheight",bh);
		if(ht > bh ) bh=ht;
		Dimension d = new Dimension(bl, bh);
		button.setPreferredSize(d);
		button.setMaximumSize(d);
		button.setMinimumSize(d);
		button.setPreferredSize(d);
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
