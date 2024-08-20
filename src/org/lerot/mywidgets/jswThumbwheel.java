/*
 * Created on 09-Jan-2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.lerot.mywidgets;

<<<<<<< HEAD
import java.awt.AWTEvent;
import java.awt.Dimension;
//import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
=======
import java.awt.Dimension;

>>>>>>> master
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
<<<<<<< HEAD
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class jswThumbwheel extends jswPanel implements ChangeListener
=======

public class jswThumbwheel extends jswPanel
>>>>>>> master
{

	private static final long serialVersionUID = 1L;
	int mheight = 60;
	// JCheckBox check;
	JSpinner value;
	int currentvalue=-1;
	ActionListener al=null;

	public jswThumbwheel(String text, int inmin, int inmax)
	{
		super(text);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		// check = new JCheckBox(text);
		// check.setSelected(false);
		// add(check);
		JLabel label = new JLabel("    " + text);
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
	    value.addChangeListener(this);
		add(value);
		setMinimumSize(new Dimension(width, 42));
	}
	
	public jswThumbwheel(ActionListener cl,String text, int inmin, int inmax)
	{
		super(text);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		// check = new JCheckBox(text);
		// check.setSelected(false);
		// add(check);
		JLabel label = new JLabel("    " + text);
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
		value.setValue(new Integer(v));
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