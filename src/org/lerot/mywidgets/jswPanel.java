package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public abstract class jswPanel extends JPanel  implements ActionListener

{
	private static final long serialVersionUID = 1L;
	private String panelname;
	private int setHeight = 0, setWidth = 0;
	protected jswStyle style;
	private String tag = "";
	private String marker = "";
	private Insets padding = new Insets(1,1,1,1);
	ActionListener actionlistener = null;

	public jswPanel(String name)
	{
		panelname = name;
		setStyle(this.getClass().getSimpleName());
	}

	public jswPanel(int count)
	{
		String name = String.valueOf(count);
		panelname = name;
		setStyle(this.getClass().getSimpleName());
	}



	
	public Insets getPadding() {
	     return padding;
	   }

	public void setPadding(int in)
	{
		padding = new Insets(in,in,in,in);
	}

	public void setPadding()
	{
		setPadding(5);
	}
	
	public void setPadding(int i, int j, int k, int l)
	{
		padding = new Insets(i,j,k,l);
		
	}

	public void addActionListener(ActionListener al)
	{
		actionlistener = al;

	}
	
	void applyStyle()
	{		
		applyStyle(style);		
	}
	
	 void applyStyle(jswStyle astyle)
	{
		
		Font sfont = astyle.getFont();
		this.setFont(sfont);
		this.setBorder(astyle.getBorder());
		this.setForeground(astyle.getColor("foregroundColor", Color.BLACK));
		this.setBackground(astyle.getColor("backgroundColor", Color.green));
		
	}
	
	
	/*
	public void applyStyles(JComponent label)
	{
		applyStyles(label, getStyles());
	}

	public void applyStyles(JComponent label, jswStyle usestyles)
	{
		if (label == null) return;
		jswStyle styles = usestyles;
		Font sfont = styles.getFont();
		label.setBorder(styles.getBorder());
		if (label instanceof jswLabel)
		{
			JLabel innerlabel = ((jswLabel) label).getLabel();
			innerlabel.setFont(sfont);
			innerlabel.setForeground(styles.getColor("foregroundColor",
					Color.BLACK));
			innerlabel.setBackground(styles.getColor("backgroundColor",
					Color.white));
			// innerlabel.setForeground(Color.red);
		} else if (label instanceof jswHorizontalPanel)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", new Color(0,
					0, 0, 0)));
		} else if (label instanceof JLabel)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswButton)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswToggleButton)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof JButton)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswContainer)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
		} else if (label instanceof jswTextBox)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.white));
			JTextField innerlabel = ((jswTextBox) label).getTextField();
			innerlabel.setFont(sfont);
			innerlabel.setForeground(styles.getColor("foregroundColor",
					Color.BLACK));
			innerlabel.setBackground(Color.yellow);
			System.out.println(" setting " + innerlabel.getText());

		} else if (label instanceof JTextField)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(Color.green);
			label.setPreferredSize(new Dimension(300, 20));
		} else if (label instanceof jswTable)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(Color.green);

		} else if (label instanceof JComboBox)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(new Color(0, 0, 0, 0));
		} else if (label instanceof JToggleButton)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.green));

		} else if (label instanceof jswCheckbox)
		{
			label.setFont(sfont);
			label.setForeground(styles.getColor("foregroundColor", Color.BLACK));
			label.setBackground(styles.getColor("backgroundColor", Color.green));

		} else
		{
			System.out.println(" not setting styles for "
					+ label.getClass().getSimpleName());
		}

	}

	public void applyStyles(jswStyle usestyles)
	{
		jswStyle styles = usestyles;
		Font sfont = styles.getFont();
		this.setFont(sfont);
		this.setBorder(styles.getBorder());
		this.setForeground(styles.getColor("foregroundColor", Color.BLACK));
		this.setBackground(styles.getColor("backgroundColor", new Color(0, 0,
				0, 0)));
	}

	public void applyStyles(jswStyles fromstyles,String stylename)
	{
		jswStyle styles = fromstyles.getStyle(stylename);
		applyStyles(this, styles);
	}

	public void applyStyles(String stylename)
	{
		jswStyle styles = jswStyles.getDefaultStyles().getStyle(stylename);
		applyStyles(this, styles);
	}
	
	public void applyStyles()
	{
		//jswStyle styles = jswStyles.getDefaultStyles().getStyle(stylename);
		applyStyles(this, style);
	}
*/

	public String getPanelname()
	{
		return panelname;
	}

	public String getTag()
	{
		return tag;
	}

	public boolean isSelected()
	{
		return false;
	}

	public int jswGetHeight()
	{
		if (setHeight > 0) return setHeight;
		Dimension d = getPreferredSize();
		if (d.height > 0) return d.height;
		else
		{
			d = getMaximumSize();
			if (d.height > 0) return d.height;
			else
			{
				d = getMinimumSize();
				return d.height;
			}
		}
	}

	public int jswGetWidth()
	{
		if (setWidth > 0) return setWidth;
		Dimension d = getPreferredSize();
		if (d.width > 0) return d.width;
		else
		{
			d = getMaximumSize();
			if (d.width > 0) return d.width;
			else
			{
				d = getMinimumSize();
				return d.width;
			}
		}
	}

	public void setPanelname(String panelname)
	{
		this.panelname = panelname;
	}

	private void setStyle(String stylename)
	{
		setStyles(new jswStyle());
		getStyles().setDefaultStyle();
		jswStyle basestyle = jswStyles.getDefaultStyles().getStyle(stylename);
		getStyles().overlay(basestyle);
	}

	public void setStyleParameter(String attribute, int value)
	{
		getStyles().putAttribute(attribute, value);
	}

	public void setStyleParameter(String attribute, String value)
	{

		getStyles().putAttribute(attribute, value);

	}

	public void setTag(String tag)
	{
		this.tag = tag;
	}

	@Override
	public void paint(Graphics g)
	{
		// System.out.println(" freddy ");
		super.paint(g);
		// g.setColor(getBackground());
		// g.fillRect(0, 0, getSize().width, getSize().height);
		// g.setColor(getForeground());
		// Rectangle border = getBorderBounds();
		// g.drawRect(border.x, border.y, border.width, border.height);
	}

	protected Rectangle getBorderBounds()
	{
		int x = padding.left;
		int y = padding.top;
		int width = getSize().width - padding.left - padding.right;
		int height = getSize().height - padding.top - padding.bottom;
		Rectangle bounds = new Rectangle(x, y, width, height);
		return bounds;
	}



	

	public String getMarker()
	{
		return marker;
	}

	public void setMarker(String marker)
	{
		this.marker = marker;
	}


	public jswStyle getStyles()
	{
		return getStyle();
	}

	public void setStyles(jswStyle styles)
	{
		this.setStyle(styles);
	}

	public void addStyle(String attname, String avalue)
	{
		getStyle().putAttribute(attname,avalue);
		
	}

	public jswStyle getStyle()
	{
		return style;
	}

	public void setStyle(jswStyle style)
	{
		this.style = style;
	}


	public void actionPerformed(ActionEvent e)
	{
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId, "jswPanel action");
		actionlistener.actionPerformed(event);
		
	}

}
