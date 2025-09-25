package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class jswThumbwheel extends jswWidget implements ChangeListener
{
	private static final long serialVersionUID = 1L;
	JSpinner value;
	int currentvalue=-1;
	ActionListener al=null;
	private final JLabel label;

	public jswThumbwheel(ActionListener cl,String text, int inmin, int inmax)
	{
		this(cl, text, inmin, inmin, text);
	}

	public jswThumbwheel(ActionListener cl,String text, int inmin, int inmax, String actioncommand)
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
		setActionCommand(actioncommand);
		al = cl;
	//	setMinimumSize(new Dimension(width, 42));
		applyStyle();
	}

	@Override
    public void applyStyle(jswStyle style)
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
		setSelection(value.getValue().toString());
		Long t = System.currentTimeMillis() / 10000;
	    int uniqueId = t.intValue();
	//	//System.out.println(" in thumbwheel :"+getActionCommand());
        jswActionEvent event = new jswActionEvent(this, uniqueId,getActionCommand());
        al.actionPerformed(event);
    }


	@Override
	public void actionPerformed(ActionEvent e)
	{

	}
}