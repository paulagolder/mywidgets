package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class jswHorizontalLayout extends jswLayout
{
	private static final int DEFAULT_HGAP = 3;
	String flag = "";
	boolean track = false;
	private layout[] clayout;
	private boolean trace;
	int hgap = 1;
	private int indent = 1;;

	public jswHorizontalLayout()
	{

	}

	@Override
	public void layoutContainer(Container parent)
	{
		
		if(parent instanceof jswPanel)
		{
			if (((jswPanel)parent).getPanelname().equals("Panel 4")	)
					{
				//System.out.println(" here goes");
					}
					
		}
		trace = false;
		indent = ((jswPanel) parent).getStyle().getIntegerStyle("indent", 1);
		hgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", hgap);
        gap = hgap;
		int componentcount = parent.getComponentCount();
		clayout = new layout[componentcount];
		int availableHeight = 0;
		boolean hasRight = false, hasMiddle = false;
		if (componentcount < 2)
			gap = 0;
		Insets insets = parent.getInsets();
		if (parent instanceof jswPanel)
		{
			Border aborder = ((JComponent) parent).getBorder();
			if (aborder != null)
				insets = aborder.getBorderInsets(parent);
		}
		Dimension parentSize = parent.getSize();
		boolean useMinimum;
		useMinimum = true;

		int fillweight = 0;
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
				Border bd = ((JComponent) comp).getBorder();
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
				clayout[j].minwidth = d.width + clayout[j].bdwidth;
				clayout[j].minheight = d.height;
				clayout[j].finalwidth = clayout[j].minwidth;
				if (s.containsKey("WIDTH"))
				{
					clayout[j].width = s.getInteger("WIDTH");
				}
				if (s.containsKey("MINWIDTH"))
				{
					clayout[j].minwidth = s.getInteger("MINWIDTH");
				}
				if (s.isTrue("FILLW"))
				{
					clayout[j].fillw = s.getInteger("FILLW");
					fillweight += clayout[j].fillw;
					// if(clayout[j].fillw < 2 )clayout[j].fillw=100;
				}
				if (s.containsKey("INDENT"))
				{
					clayout[j].indent = s.getInteger("INDENT");
				}
				j = j + 1;
			}
		}

		int visiblecomponents = j;
		int usableWidth = parentSize.width -indent- insets.left - insets.right - 5 - gap * visiblecomponents;
		availableHeight = parentSize.height - insets.top - insets.bottom;

		if (visiblecomponents == 1)
		{
			
			Component comp = clayout[0].comp;
			Dimension d = comp.getMinimumSize();
			int compwidth = d.width;
			settings s = getSettings(comp);
			int x = insets.left + indent;
			if (s.isTrue("RIGHT"))
				x = -insets.right + usableWidth - compwidth;
			else if (s.isTrue("MIDDLE"))
				x = (int) (insets.left +indent + (usableWidth - compwidth) / 2.0);

			if (s.isTrue("FILLW"))
			{
				compwidth = usableWidth;
				x = insets.left;
			}
			comp.setBounds(x, insets.top, compwidth, availableHeight);
			return;
		}
		
		int cumwidth = 0;
		
		float fillratio = 1;
		int fillwidth = 0;

		for (j = 0; j < visiblecomponents; j++)
		{
			clayout[j].finalwidth = clayout[j].minwidth;
			if (clayout[j].fillw > 0)
			{
				clayout[j].finalwidth = (int) (clayout[j].minwidth); 
				fillwidth += clayout[j].finalwidth;
			}
			if (clayout[j].width > 0)
			{
				clayout[j].finalwidth = clayout[j].width;
			}
			cumwidth += clayout[j].finalwidth + gap + clayout[j].bdwidth;
		}

		int sparespace = usableWidth - cumwidth;
		fillratio = (float) (sparespace + fillwidth) / (float) fillwidth;
		if (((jswPanel) parent).getStyle().getBooleanStyle("distribute") == true)
		{
			gap = sparespace / (visiblecomponents+1);
		} 
		int x = indent+ insets.left + gap / 2 ;
		int y = insets.top;

		for (j = 0; j < visiblecomponents; j++)
		{
			clayout[j].y = y;
			clayout[j].x = x;
			if (clayout[j].fillw > 0)
			{
				clayout[j].finalwidth = (int) (clayout[j].minwidth * fillratio);
			}
			x = x + clayout[j].finalwidth + gap ;//+ clayout[j].bdwidth;
		}
	
		for (j = 0; j < visiblecomponents; j++)
		{
			Component comp = clayout[j].comp;
			comp.setBounds(clayout[j].x, clayout[j].y, clayout[j].finalwidth, availableHeight);
		}

	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		Insets insets = parent.getInsets();
		if (parent instanceof jswPanel)
		{
			Border aborder = ((JComponent) parent).getBorder();
			if (aborder != null)
				insets = aborder.getBorderInsets(parent);
		}
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0)
			this.setGap(0);
		setGap(this.getGap());
		int w = 0;
		int h = 0;

		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible())
				continue;
			Dimension d = comp.getMinimumSize();
			if (h < d.height)
			{
				h = d.height;
			} // if
			w += d.width;
			if (i != 0)
			{
				w += this.getGap();
			} // if
		} // for
		return new Dimension(insets.left + insets.right + w, insets.top + insets.bottom + h);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		Insets insets = parent.getInsets();
		if (parent instanceof jswPanel)
		{
			Border aborder = ((JComponent) parent).getBorder();
			if (aborder != null)
				insets = aborder.getBorderInsets(parent);
		}
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0)
			this.setGap(0);
		int w = 0;
		int h = 0;

		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible())
				continue;
			Dimension d = comp.getPreferredSize();
			if (h < d.height)
			{
				h = d.height;
			} // if
			w += d.width;
			if (i != 0)
			{
				w += this.getGap();
			}
		}
		return new Dimension(insets.left + insets.right + w, insets.top + insets.bottom + h);
	}

	public static String[] help()
	{
		String[] helpstring = new String[2];
		helpstring[0] = "lHorizontal Layout Help";
		helpstring[1] = "line 2";
		return helpstring;
	}

}