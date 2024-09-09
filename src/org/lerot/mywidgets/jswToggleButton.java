package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class jswToggleButton extends jswPanel implements ActionListener, ChangeListener
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
		 UIDefaults dflts = UIManager.getLookAndFeelDefaults();
	      dflts.put("ToggleButton.select", Color.GREEN);
		label=alabel;
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		button = new JToggleButton(label);
		button.addActionListener(this);
		button.addChangeListener(this);
		actionlistener = al;
		button.setActionCommand(command);
		add(button);
		applyStyle();
		button.setVisible(true);
	}

  void applyStyle()
  {
	 
    //  EventQueue.invokeLater(new TogglTst());
	  int bl = label.length() * 8 + 30;
	  int bh= 30;
	  //to do fm
	//	button.setFont(new Font("SansSerif", Font.BOLD, 11));
		
	
	    button.setFont(style.getFont());
		button.setBackground(jswStyle.defaultwidgetcolor);
		
		button.setForeground(style.getForegroundcolor());
		button.setBorder(style.getBorder());
		Border  bborder = BorderFactory.createRaisedBevelBorder();
		button.setBorder(bborder);
		int wd =style.getIntegerStyle("mywidth",bl);
		if(wd > bl) bl= wd;
		int ht =style.getIntegerStyle( "myheight",bh);
		if(ht > bh ) bh=ht;
		Dimension d = new Dimension(bl, bh);
		button.setPreferredSize(d);
		button.setMaximumSize(d);
		button.setMinimumSize(d);
		setBackground(jswStyle.TRANSPARENT);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
  }
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String mess ="";
	 /*   if (button.isSelected())  
	    {
            button.setText("OFF");  
            mess = "Button Off";
	    }
        else  
        {
        	button.setText("      ");
            button.setText(label);  
            mess = "Button On";
        }	 */
	    button.repaint();
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, "value =" + button.getActionCommand()+mess);
		actionlistener.actionPerformed(event);


	}
	
	
	        public void stateChanged(ChangeEvent event) {
	            if (button.isSelected()){
	                button.setText("ON");
	            } else {
	                button.setText("OFF");
	            }
	        }
	    

}

	
