package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class jswVerticalLayout extends jswLayout
{

	private static final int DEFAULT_VGAP = 0;
	private int vgap;
	private layout[] clayout = new layout[10];
	private int componentcount = 0;
	private boolean trace = false;


	public jswVerticalLayout()
	{

	}

	@Override
	public void layoutContainer(Container parent)
	{
		trace=false;

		if (parent instanceof jswPanel)
		{

			if (((jswPanel) parent).getName().equals("my V button list"))
			{
				//System.out.println(" here goes 2");
				//trace=true;
			}

		}
		padding = jswPanel.getPadding(((jswPanel) parent).getStyle().getStringStyle("padding", "1"));
		
		if(trace)
	{
			System.out.println(" paddingy :"+padding);
	}
		vgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", vgap);
		verticallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("verticallayoutstyle", 0);
		horizontallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("horizontallayoutstyle", 0);
		componentcount = parent.getComponentCount();
		clayout = new layout[componentcount];
		if (componentcount == 0)
			vgap = 0;
		int availableHeight = 0;
		boolean hasBottom = false, hasMiddle = false;
		
		Container theparent = null;
		if (theparent == null)
			theparent = parent;
		Dimension parentSize = theparent.getSize();

		int usableWidth = parentSize.width - padding.left -padding.right;
		availableHeight = parentSize.height - padding.top - padding.bottom ;
		boolean useMinimum;

		useMinimum = preferredLayoutSize(parent).height > availableHeight;
		useMinimum = false;
		int j = 0;
		for (int i = 0; i < componentcount; i++)
		{
			Component comp = parent.getComponent(i);

			settings s = getSettings(comp);
			if (comp.isVisible())
			{

				clayout[j] = new layout();
				clayout[j].cindex = i;
				clayout[j].comp = comp;		
				Dimension d = useMinimum(comp, useMinimum);
				clayout[j].width = d.width;
				Border bd = ((JComponent) comp).getBorder();
				int bdwidth = 0;
				if (bd != null)
				{
					Insets dbinsets = bd.getBorderInsets(comp);
					clayout[j].insets = dbinsets;
					clayout[j].bdwidth = dbinsets.left + dbinsets.right;
				} else
				{
					clayout[j].bdwidth = 0;
					clayout[j].insets = new Insets(0, 0, 0, 0);
				}
				if (s.isTrue("FILLH"))
				{
					clayout[j].fillh = s.getInteger("FILLH");
					if (clayout[j].fillh < 1)
						clayout[j].fillh = 100;
					clayout[j].minheight = d.height;
				} else if (s.isTrue("SCROLLH"))
				{
					clayout[j].scrollh = s.getInteger("SCROLLH");
					if (clayout[j].scrollh < 1)
						clayout[j].scrollh = 100;
					clayout[j].minheight = d.height;
				} else
				{
					clayout[j].minheight = d.height;
				}
				clayout[j].height = clayout[j].minheight;
				if (s.isTrue("MAXHEIGHT"))
				{
					clayout[j].maxheight = s.getInteger("MAXHEIGHT");
				}
				if (s.isTrue("MINHEIGHT"))
				{
					clayout[j].minheight = s.getInteger("MINHEIGHT");
				}
				if (s.isTrue("MIDDLE"))
				{
					hasMiddle = true;
					clayout[j].hasMiddle = true;
				} else if (hasMiddle)
				{
					clayout[j].hasBottom = true;
				}
				if (s.isTrue("BOTTOM"))
				{
					hasBottom = true;
					clayout[j].hasBottom = true;
				}
				
				j = j + 1;
			}
		}
		int visiblecomponents = j;
		if (visiblecomponents < 1)
			return;
		double fillratio = 1.0;
		double scrollratio = 1.0;

		if (visiblecomponents == 1)
		{
			Component comp = clayout[0].comp;
			// if (comp instanceof JScrollPane)
			// {
			// ((JScrollPane) comp).setBounds(clayout[0].x + clayout[0].indent, 20,
			// usableWidth, availableHeight);
			// } else
			comp.setBounds(clayout[0].x + clayout[0].indent, 0, usableWidth, availableHeight);
			return;
		}

		int cumheight = 0;
		int fillheight = 0;
		int filltotal = 0;

		for (j = 0; j < visiblecomponents; j++)
		{
			if (clayout[j].fillh > 0)
			{
				fillheight += clayout[j].height;
				filltotal += clayout[j].fillh;
			} 
			
			cumheight += clayout[j].height;
		}
	
		int sparespace = availableHeight - cumheight - vgap*visiblecomponents + fillheight;
		if (sparespace > 0)
		{
			if (fillheight > 0)
				fillratio = (float) (sparespace ) / (float) fillheight;		
		} 
		if(trace)
		{
			System.out.println(" paddingx :"+padding);
		}
		int gap;
		int y=0;
		if (verticallayoutstyle == jswLayout.DISTRIBUTE)
		{
			gap = (sparespace + vgap*visiblecomponents )/ (visiblecomponents );
		    y =   gap / 2 + padding.top ;
		} else
		{
			gap = vgap;
		
			y =   gap / 2 +  padding.top;		
		}

	
		for (j = 0; j < visiblecomponents; j++)
		{
			clayout[j].y = y;
			if (horizontallayoutstyle == jswLayout.RIGHT )
			{
				clayout[j].x =   padding.left+ usableWidth - clayout[j].width;
				
			}else if (horizontallayoutstyle == jswLayout.MIDDLE)
			{
				clayout[j].x= padding.left+ (usableWidth - clayout[j].width)/2;
			}else
			{
				clayout[j].x = padding.left  ;
			}
		
			if (clayout[j].fillh > 0)
			{
				clayout[j].height = (int) (clayout[j].height * fillratio);
			} else if (clayout[j].scrollh > 0)
			{
				clayout[j].height = (int) (clayout[j].height * scrollratio);
			} else
			{
				clayout[j].height = clayout[j].height;
			}
			if (clayout[j].maxheight > 0 & clayout[j].height > clayout[j].maxheight)
			{
				clayout[j].height = clayout[j].maxheight;
			}
			y = y + clayout[j].height + gap ;//+ clayout[j].bdwidth;
		}

		for (j = 0; j < visiblecomponents; j++)
		{
			Component comp = clayout[j].comp;
			comp.setBounds(clayout[j].x, clayout[j].y, usableWidth , clayout[j].height);
		}

	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		Insets padding = jswPanel.getPadding(((jswPanel) parent).getStyle().getStringStyle("padding", "1"));
		vgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", vgap);
		componentcount = parent.getComponentCount();

		if (componentcount == 0)
			vgap = 0;

		Insets insets = parent.getInsets();
		if (parent instanceof jswPanel)
		{
			Border aborder = ((JComponent) parent).getBorder();
			if (aborder != null)
				insets = aborder.getBorderInsets(parent);
		}
		Insets binsets = new Insets(0, 0, 0, 0);

		int w = 0;
		int h = 0;
		int desiredheight = 0;
		for (int i = 0; i < componentcount; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible())
				continue;
			settings s = getSettings(comp);
			int maxheight = s.getInteger("HEIGHT");
			Dimension d = comp.getMinimumSize();
			if (w < d.width)
			{
				w = d.width;
			}
			desiredheight = d.height;
			if (maxheight > 0 && d.height > maxheight)
				desiredheight = maxheight;

			h += desiredheight;

			if (i != 0)
			{
				h += this.vgap;
			}
		}
		
		int prefwidth = padding.left + padding.right + w ; // binsets.left + binsets.right + w;
		int prefheight = padding.top + padding.bottom + h ; // binsets.top + binsets.bottom + h;
		return new Dimension(prefwidth, prefheight + 5);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		Insets padding = jswPanel.getPadding(((jswPanel) parent).getStyle().getStringStyle("padding", "1"));
		vgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", vgap);
		componentcount = parent.getComponentCount();

		if (componentcount == 0)
			vgap = 0;
		Insets binsets = new Insets(0, 0, 0, 0);
		int ncomponents = parent.getComponentCount();
		if (parent instanceof jswPanel)
		{
			Border aborder = ((JComponent) parent).getBorder();
			if (aborder != null)
				binsets = aborder.getBorderInsets(parent);
		}
		if (ncomponents == 0)
			vgap = 0;
		Insets insets = parent.getInsets();
		int w = 0;
		int h = 0;
		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible())
				continue;
			Dimension d = comp.getPreferredSize();
			if (w < d.width)
			{
				w = d.width;
			}
			h += d.height;
			if (i != 0)
			{
				h += this.vgap;
			}
		}
		int prefwidth = insets.left + insets.right + binsets.left + binsets.right + w;
		int prefheight = insets.top + insets.bottom + binsets.top + binsets.bottom + h;
		return new Dimension(prefwidth, prefheight + 5);
	}

	public static String[] help()
	{
		String[] helpstring = new String[2];
		helpstring[0] = "line 1";
		helpstring[1] = "line 2";
		return helpstring;
	}

	

}
