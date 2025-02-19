package org.lerot.mywidgets;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class jswOption extends jswWidget
{

	private static final long serialVersionUID = 1L;
	private JRadioButton button;
	boolean vertical = true;
	private int mwidth;
	private int nwidth;
	private int nheight;


	public jswOption(ActionListener al,String text, boolean vertical,String command)
	{
		super(text);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		button = new JRadioButton(text);
		button.setSelected(false);
		add(button);
		button.addActionListener(this);
		button.setActionCommand(command);
		setActionListener(al);
		this.vertical = vertical;
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		button.setAlignmentY(Component.TOP_ALIGNMENT);
		this.getStyle().setBackgroundcolor("transparent");	
		this.setStyleAttribute("borderwidth", 1);
		this.setStyleAttribute("bordercolor", "red");	
		setPanelBorder(style);
        applyStyle();
	}


	public void applyStyle(jswStyle style)
	{	  
			Font sfont = style.getFont();
			button.setFont(sfont);	
			Canvas c = new Canvas();
			FontMetrics fm = c.getFontMetrics(sfont);
			mwidth = fm.stringWidth(button.getText());
			nwidth = button.getMinimumSize().width;
			nheight = button.getMinimumSize().height;
			setPanelBorder(style);
			button.setBorder(style.getBorder());
			button.setForeground(style.getColor("foregroundColor", Color.blue));
			button.setBackground(style.getColor("backgroundColor", Color.red));	
	}
	
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


	
	public void actionPerformed(ActionEvent e)
	{
		String mess ="";
	    if (button.isSelected())  
	    {
           // button.setText("OFF");  
            mess = "ON";
	    }
        else  
        {
           // button.setText("ON");  
            mess = "OFF";
        }	 
	 //   HashMap<String, String> am = jswPanel.createActionMap(this, e);
	 //   am.put( "command" , "optionselected");
	 //   am.put( "value" ,mess);
		setSelection(mess);
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId,e.getActionCommand());
		getActionlistener().actionPerformed(event);
	}
	
	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension(nwidth+padding.left+padding.right+20,nheight+padding.top+padding.bottom);
	}
	
	}


