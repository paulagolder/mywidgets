package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


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
		initialiseStyle(this.getClass().getSimpleName());
	}

	public jswPanel(int count)
	{
		this( String.valueOf(count));
	}

 void initialiseStyle(String stylename)
{	
		style = new jswStyle();
		style.setDefaultStyle();
		jswStyle basestyle = jswStyles.getDefaultStyles().getStyle(stylename);
		style.overlay(basestyle);
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
	
	 void setPanelBorder()
	 {
		 int bs = style.getBorderStyle();
		 if( bs== jswStyle.TITLEDBORDER)
		 {
			 setBorder(jswStyle.makecborder(panelname));
			 this.setPadding(20,5,5,5);;
		 }else if ( bs== jswStyle.LINEBORDER)
		 {
				setBorder(jswStyle.makeLineBorder(Color.pink, 2));
				Border thisborder = getBorder();
				int thk = ((LineBorder)thisborder).getThickness();
				this.setPadding(((LineBorder)thisborder).getThickness()+1);
		 }else
		 {
			 setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			 
		 }
		 
		 
	 }
	
	
	
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


	public void setTag(String tag)
	{
		this.tag = tag;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
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


	public void addStyle(String attname, String avalue)
	{
		getStyle().putAttribute(attname,avalue);	
	}
	
	public void addStyle(String attname, int avalue)
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
