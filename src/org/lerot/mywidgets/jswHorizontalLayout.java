package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class jswHorizontalLayout extends jswLayout
{

	private layout[] clayout;
	private String trace;
	int hgap = 1;

	public jswHorizontalLayout()
	{

	}

	@Override
	public void layoutContainer(Container parent)
	{
		trace = "none";
		//trace = "option list 1";

		verticallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("verticallayoutstyle", 0);
		horizontallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("horizontallayoutstyle", 0);
		hgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", hgap);
		padding = ((jswPanel) parent).padding;

		gap = hgap;
		int componentcount = parent.getComponentCount();
		clayout = new layout[componentcount];
		int availableHeight = 0;
		boolean hasRight = false, hasMiddle = false;
		if (componentcount < 2)
			gap = 0;
		if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
		{
			System.out.println("traceing " + trace);
		}
	//	Dimension parentSize = parent.getMinimumSize();
		Dimension parentSize = parent.getSize();
		boolean useMinimum;
		useMinimum = true;
		if (parent instanceof jswPanel)
		{
			if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
			{
				System.out.println("traceing " + trace);
				System.out.println("x " + parentSize + ":" + padding + ":" + gap);
			}

		}
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
			
				if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
				{
					System.out.println("traceing x " + trace);
				}
				Dimension d = useMinimum(comp, useMinimum);
				clayout[j].minwidth = d.width;// + clayout[j].bdwidth;
				clayout[j].finalheight = d.height;
				clayout[j].finalwidth = d.width;
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
				}
				j = j + 1;
			}
		}

		int visiblecomponents = j;
		int usableWidth = parentSize.width - padding.left - padding.right;// - gap * (visiblecomponents-1);
		availableHeight = parentSize.height - padding.top - padding.bottom;
		// availableHeight = parentSize.height ;

		if (visiblecomponents == 1)
		{
			Component comp = clayout[0].comp;
			Dimension d = comp.getMinimumSize();
			int compwidth = d.width;
			settings s = getSettings(comp);
			int x = padding.left;
			if (s.isTrue("RIGHT"))
				x = -padding.right + usableWidth - compwidth;
			else if (s.isTrue("MIDDLE"))
				x = (int) (padding.left + (usableWidth - compwidth) / 2.0);

			if (s.isTrue("FILLW"))
			{
				compwidth = usableWidth;
				x = padding.left;
			}
			comp.setBounds(x, padding.top, compwidth, availableHeight);
			return;
		}

		int cumwidth = -gap;
		float fillratio = 1;
		int fillwidth = 0;

		for (j = 0; j < visiblecomponents; j++)
		{
			clayout[j].finalwidth = clayout[j].minwidth;
			if (clayout[j].fillw > 0)
			{
				clayout[j].finalwidth = (int) (clayout[j].minwidth) ;
				fillwidth += clayout[j].finalwidth;
			}
			if (clayout[j].width > 0)
			{
				clayout[j].finalwidth = clayout[j].width;
			}
			cumwidth += clayout[j].finalwidth + gap;// + clayout[j].bdwidth;
		}

		int sparespace = usableWidth - cumwidth;
		fillratio = (float) (sparespace + fillwidth) / (float) fillwidth;

		if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
			System.out.println(
					"y " + fillratio + ":" + usableWidth + ":" + cumwidth + ";" + sparespace + ":" + fillwidth);

		if (horizontallayoutstyle == jswLayout.DISTRIBUTE)
		{
			gap = sparespace / (visiblecomponents - 1);
		}
		if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
			System.out.println("z " + gap);

		int x = padding.left; // + gap / 2 ;
		if (sparespace < 1)
		{
			gap = 1;
			fillratio = 0;
			x = padding.left;
		}
		for (j = 0; j < visiblecomponents; j++)
		{
			clayout[j].x = x;
			if (verticallayoutstyle == jswLayout.BOTTOM)
			{
				clayout[j].y = padding.bottom + availableHeight - clayout[j].finalheight;
			} else if (verticallayoutstyle == jswLayout.MIDDLE)
			{
				clayout[j].y = padding.bottom + (availableHeight - clayout[j].finalheight) / 2;
			} else
			{
				clayout[j].y = padding.top;
			}
			if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
				System.out.println("x=" + x + " y=" + clayout[j].y+ "fw="+clayout[j].minwidth);

			if (fillratio > 0 && fillratio < 20 && clayout[j].fillw >0)
			{
				clayout[j].finalwidth = (int) (clayout[j].minwidth * fillratio);
			}
			x = x + clayout[j].finalwidth + gap;// + clayout[j].bdwidth;
			;
		}

		for (j = 0; j < visiblecomponents; j++)
		{
			Component comp = clayout[j].comp;
			 comp.setBounds(clayout[j].x, clayout[j].y, clayout[j].finalwidth, availableHeight);
			//comp.setBounds(clayout[j].x, clayout[j].y, clayout[j].finalwidth, clayout[j].finalheight);
			if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
				System.out.println("y2 " + clayout[j].x + ":" + clayout[j].y + ":" + clayout[j].finalwidth + ":"
						+ availableHeight);

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
			// Dimension d = comp.getPreferredSize();
			Dimension d = comp.getMinimumSize();
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