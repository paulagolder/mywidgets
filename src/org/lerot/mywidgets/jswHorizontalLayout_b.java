package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

public class jswHorizontalLayout_b extends jswLayout
{
	private static final int DEFAULT_HGAP = 3;
	String flag = "";
	private int hgap;
	boolean track = false;
	private layout[] clayout;

	public jswHorizontalLayout_b()
	{
		this(DEFAULT_HGAP);
	}

	public jswHorizontalLayout_b(int hgap)
	{
		this.hgap = hgap;
	}

	@Override
	public void layoutContainer(Container parent)
	{

		int componentcount = parent.getComponentCount();
		if (parent instanceof jswPanel)
		{
			if (((jswPanel) parent).getBorder() != null)
			{
				// padding inset = ((jswPanel) parent).getBorder().getBorderpadding(parent);
				// if(((jswPanel) parent).getStyle().hasBorder() )
				// borderwidth = ((jswPanel) parent).getStyle().getBorderWidth()+20;
				// top=inset.top+1;
				// left=inset.left+1;
				// right=inset.right+1;
				// bottom=inset.bottom+1;
			}
		}
		clayout = new layout[componentcount];
		int availableHeight = 0;
		boolean hasRight = false, hasMiddle = false;
		if (componentcount < 2)
			this.hgap = 0;
		padding = ((jswPanel) parent).getPadding();

		Dimension parentSize = parent.getSize();
		
		boolean useMinimum;
		//useMinimum = preferredLayoutSize(parent).width > usableWidth;
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

				/*
				 * if (s.isTrue("FILLH")) { clayout[j].fillh = s.getInteger("FILLH");
				 * clayout[j].minheight = d.height; }
				 */

				clayout[j].minwidth = d.width;
				clayout[j].minheight = d.height;
				clayout[j].finalwidth = clayout[j].minwidth;
				if (s.containsKey("WIDTH"))
				{
					clayout[j].width = s.getInteger("WIDTH");
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
		int usableWidth = parentSize.width - padding.left - padding.right -5  - hgap *visiblecomponents;
		// paul fix is border
		availableHeight = parentSize.height - padding.top - padding.bottom;

		boolean delta = true;
		int loop = 0;
		float fillratio = 1;

		while (loop < 2)
		{
			int cumwidth =  -hgap;

			int fillwidth = -hgap;
			delta = false;

			for (j = 0; j < visiblecomponents; j++)
			{
				clayout[j].finalwidth = clayout[j].minwidth;
				if (clayout[j].fillw > 0)
				{

					clayout[j].finalwidth = (int) (clayout[j].minwidth * ((float) clayout[j].fillw / fillweight));
					fillwidth += clayout[j].finalwidth+hgap;

				}
				if (clayout[j].width > 0)
				{
					clayout[j].finalwidth = clayout[j].width;
				}
				cumwidth += clayout[j].finalwidth+hgap;

			}

			fillratio = (float) (usableWidth - (cumwidth - fillwidth)) / (float) fillwidth;

			int x = padding.left;
			int y = padding.top;

			for (j = 0; j < visiblecomponents; j++)
			{
				clayout[j].y = y;
				clayout[j].x = x;
				if (clayout[j].fillw > 0)
				{
					clayout[j].finalwidth = (int) (clayout[j].minwidth * ((float) clayout[j].fillw / fillweight)
							* fillratio);
				}
				x = x + clayout[j].finalwidth+hgap;
			}
			loop++;
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
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0)
			this.hgap = 0;

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
				w += this.hgap;
			} // if
		} // for
		return new Dimension(padding.left + padding.right + w, padding.top + padding.bottom + h);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0)
			this.hgap = 0;
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
				w += this.hgap;
			}
		}
		return new Dimension(padding.left + padding.right + w, padding.top + padding.bottom + h);
	}

	public static String[] help()
	{
		String[] helpstring = new String[2];
		helpstring[0] = "lHorizontal Layout Help";
		helpstring[1] = "line 2";
		return helpstring;
	}

}