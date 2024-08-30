/*
 * Created on 09-Jan-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.lerot.mywidgets;


import java.awt.Dimension;
//import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class jswThumbwheel extends jswPanel implements ChangeListener
{

	private static final long serialVersionUID = 1L;
	int mheight = 60;
	JSpinner value;
	int currentvalue=-1;
	ActionListener al=null;
	private JLabel label;
	int bh=30;
	int bl=30;
	
	
	public jswThumbwheel(ActionListener cl,String text, int inmin, int inmax)
	{
		super(text);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		label = new JLabel("    " + text);
		add(label);
		int width = text.length() * 12 + 60;
		value = new JSpinner();
		Integer current = inmin;
		Integer min = inmin;
		Integer max = inmax;
		Integer step =1;
		SpinnerNumberModel m_numberSpinnerModel = new SpinnerNumberModel(
				current, min, max, step);
		value = new JSpinner(m_numberSpinnerModel);
		value.setMaximumSize(new Dimension(60, 30));
		value.setValue(inmax);
		add(value);
		value.addChangeListener(this);
		al = cl;
		setMinimumSize(new Dimension(width, 42));
		applyStyle();
	}
	

	@Override
	void applyStyle(jswStyle style)
	{	
		//button.setFont(new Font("SansSerif", Font.BOLD, 9));
		//int l = label.getText().length() * 8 + 30;
		int l = label.getText().length() * 12 ;
		if(l>bl)bl=l;	
		label.setFont(style.getFont());
		label.setBackground(jswStyle.defaultwidgetcolor);
		label.setForeground(style.getForegroundcolor());
		value.setBackground(jswStyle.defaultwidgetcolor);
		value.setForeground(style.getForegroundcolor());

		setBorder(style.getBorder());
		setBackground(jswStyle.TRANSPARENT);
		Dimension d = new Dimension(bl,42);
		//setBackground(style.getBackgroundcolor());
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
		//setBorder(jswStyle.makeLineBorder(Color.green, 4));
	}


	public void addChangeListener(ChangeListener cl)
	{
		value.addChangeListener(cl);

	}


	public int getValue()
	{
		return ((Integer) value.getValue()).intValue();
	}

	@Override
	public boolean isSelected()
	{
		return true;
	}

	@Override
	public void setEnabled(boolean e)
	{
		// check.setEnabled(e);
		value.setEnabled(e);
	}

	public void setValue(int v)
	{
		value.setValue(String.valueOf( v));
	}

	@Override
	 public void stateChanged(ChangeEvent e)
    {
		Long t = System.currentTimeMillis() / 10000;
	    int uniqueId = t.intValue();
        System.out.println(value.getValue() + " ++" );
        ActionEvent event = new ActionEvent(this, uniqueId,"value ="+value.getValue());
        al.actionPerformed(event);
    }


}