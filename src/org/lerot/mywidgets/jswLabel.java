package org.lerot.mywidgets;

import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class jswLabel extends jswPanel
{

	private static final long serialVersionUID = 1L;
	JLabel label;

	public jswLabel(String inLabel)
	{
		super(inLabel);
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		setLayout(layout);
		label = new JLabel();
		label.setText(inLabel);
		label.setBackground(Color.blue);
		add(label, BorderLayout.CENTER);
		setStyleAttribute("borderwidth",0);
		setStyleAttribute("bordercolor","green");
		applyStyle();
		this.setEnabled(true);
	}

	@Override
	public void applyStyle(jswStyle style)
	{
		label.setFont(style.getFont());
		label.setForeground(style.getColor("foregroundColor", Color.BLACK));
		label.setBackground(style.getColor("backgroundColor", jswStyle.TRANSPARENT));
		setBorder(style.getBorder());
		setBackground(jswStyle.TRANSPARENT);
	}

	public jswLabel(int count)
	{
		this(String.valueOf(count));
	}

	public JLabel getLabel()
	{
		return label;
	}

	public String getText()
	{
		return label.getText();
	}

	@Override
	public boolean isSelected()
	{
		return false;
	}

	/*@Override
public int jswGetWidth()
	{
		System.out.println(" setin width  " + label.getText());
		int setwidth = style.getIntegerStyle("mywidth", -1);
		if (setwidth > 0)
			return setwidth;
		System.out.println(" setwidth " + setwidth);
		Dimension d = label.getPreferredSize();
		System.out.println(" prefered size w=" + d.width + " h=" + d.height);
		if (d.width > 0)
			return d.width;
		else
		{
			d = label.getMaximumSize();
			System.out.println(" maximum size w=" + d.width + " h=" + d.height);
			if (d.width > 0)
				return d.width;
			else
			{
				d = label.getMinimumSize();
				System.out.println(" minimum size w=" + d.width + " h=" + d.height);
				return d.width;
			}
		}
	}*/

	@Override
	public void setEnabled(boolean e)
	{
		label.setEnabled(e);
	}

	public void setIcon(ImageIcon icon)
	{
		label.setIcon(icon);
	}

	public void setSelected(boolean b)
	{

	}

	public void setText(String t)
	{
		label.setText(t);
	}
	
	public String toString()
	{
		return label.getText();
	}

}