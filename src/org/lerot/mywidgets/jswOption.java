package org.lerot.mywidgets;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

public class jswOption extends jswPanel
{

	private static final long serialVersionUID = 1L;
	JPanel box;
	private JRadioButton button;
	int compheight = 0;
	boolean vertical = true;
	private int mwidth;
	private int nwidth;
	private int nheight;


	public jswOption(ActionListener al,String text, boolean vertical)
	{
		super(text);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		button = new JRadioButton(text);
		button.setSelected(false);
		add(button);
		button.addActionListener(this);
		actionlistener = al;
		this.vertical = vertical;
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
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
		int nc = box.getComponentCount();
		for (int i = 0; i < nc; i++)
		{
			Component c = box.getComponent(i);
			c.setEnabled(e);
		}
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
            mess = "option ON";
	    }
        else  
        {
           // button.setText("ON");  
            mess = "option OFF";
        }	 
	    HashMap<String, String> am = jswPanel.createActionMap(this, e);
	    am.put( "command" , button.getActionCommand());
	    am.put( "value" ,mess);
	    System.out.println(am.toString());
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId,am.toString());
		actionlistener.actionPerformed(event);

	}
	
	@Override
	public Dimension getMinimumSize()
	{
		//return button.getMinimumSize();
		return new Dimension(nwidth+padding.left+padding.right,nheight+padding.top+padding.bottom);
	}
	}


