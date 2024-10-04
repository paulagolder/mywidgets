/*
 * Created on 09-Jan-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.lerot.mywidgets;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class jswThumbwheel extends jswPanel implements ChangeListener
{

	private static final long serialVersionUID = 1L;
	//int mheight = 60;
	JSpinner value;
	int currentvalue=-1;
	ActionListener al=null;
	private JLabel label;

	public jswThumbwheel(ActionListener cl,String text, int inmin, int inmax)
	{
		super(text);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		label = new JLabel( text);
		add(label);
	//	int width = text.length() * 12 + 60;
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
	//	setMinimumSize(new Dimension(width, 42));
		applyStyle();
	}
	

	@Override
	void applyStyle(jswStyle style)
	{	
		label.setFont(style.getFont());
		label.setBackground(jswStyle.defaultwidgetcolor);
		label.setForeground(style.getForegroundcolor());
		value.setBackground(jswStyle.defaultwidgetcolor);
		value.setForeground(style.getForegroundcolor());
		setBorder(style.getBorder());
		setBackground(jswStyle.TRANSPARENT);
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

		Map am = jswPanel.createActionMap(this, e);
	     am.put("name",  ((javax.swing.JSpinner ) e.getSource()).getName());
	     am.put("quality", "value");
	     am.put("value", value.getValue());
		Long t = System.currentTimeMillis() / 10000;
	    int uniqueId = t.intValue();
        ActionEvent event = new ActionEvent(this, uniqueId,am.toString());
        al.actionPerformed(event);
    }


}