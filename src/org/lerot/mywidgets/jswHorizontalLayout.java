package org.lerot.mywidgets;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;


public class jswHorizontalLayout extends jswLayout 
{
	private static final int DEFAULT_HGAP = 3;
	String flag = "";
	private int hgap;
	boolean track = false;

	public jswHorizontalLayout()
	{
		this(DEFAULT_HGAP);
	}

	public jswHorizontalLayout(int hgap)
	{
		this.hgap = hgap;
	}

	@Override
	public void layoutContainer(Container parent)
	{

		if (parent instanceof jswPanel)
		{
			flag = ((jswPanel) parent).getTag();
			//padding bo = ((jswPanel) parent).getPadding();
		}
		int availableHeight = 0, cumwidth = -2 * hgap, fillweight = 0, prewidth = 0, cummiddle = 0, prefillweight = 0, postfillweight = 0, cumright = 0;
		boolean hasRight = false, hasMiddle = false;
		if ("trace".equalsIgnoreCase(flag))
		{
			System.out.println(" tracking");
			track = true;
		}
		int ncomponents = parent.getComponentCount();
	
		if (ncomponents < 2) this.hgap = 0;
		//padding padding = parent.getpadding();
		padding =  ((jswPanel) parent).getPadding();

		Dimension parentSize = parent.getSize();
		int usableWidth = parentSize.width - padding.left - padding.right - 3;
		// paul fix is border
		availableHeight = parentSize.height - padding.top - padding.bottom;
		boolean useMinimum;
		useMinimum = preferredLayoutSize(parent).width > usableWidth;
		// useMinimum=false;

		if (ncomponents == 1)
		{
			Component comp = parent.getComponent(0);
			Dimension d = comp.getMinimumSize();
			int compwidth = d.width;
			settings s = getSettings(comp);
			int x = padding.left;
			if (s.isTrue("RIGHT")) x = padding.left + usableWidth - compwidth;
			else if (s.isTrue("MIDDLE"))
				x = (int) (padding.left + (usableWidth - compwidth) / 2.0);

			if (s.isTrue("FILLW"))
			{
				compwidth = usableWidth;
				x = padding.left;
			}
			comp.setBounds(x, padding.top, compwidth, availableHeight);
		} else
		{
			for (int i = 0; i < ncomponents; i++)
			{
				Component comp = parent.getComponent(i);
				settings s = getSettings(comp);
				if (comp.isVisible())
				{
					Dimension d = useMinimum(comp, useMinimum);
					if (s.containsKey("WIDTH"))
					{
						d.width = s.getInteger("WIDTH");
					}
					if (track)
						System.out.println(comp.getClass().getName()
								+ " width=" + d.width + " height=" + d.height);
					if (s.isTrue("FILLW"))
					{
						cumwidth += this.hgap;
						fillweight += d.width;
					} else
					{
						cumwidth += d.width + this.hgap;
					}
					if (s.isTrue("MIDDLE"))
					{
						if (hasMiddle)
						{
							cummiddle += d.width + this.hgap;
						} else
						{
							prewidth = cumwidth;
							prefillweight = fillweight;
							cummiddle = d.width + this.hgap;
							hasMiddle = true;
						}
					} else if (hasMiddle) hasRight = true;
					if (s.isTrue("RIGHT")) hasRight = true;
					if (hasRight)
					{
						cumright += d.width + this.hgap;
					}
				}
			}
			hasRight = false;
			int x = padding.left;
			int y = padding.top;
			int xmiddle = padding.left + (usableWidth - cummiddle) / 2;
			boolean inMiddle = false, inRight = false;
			postfillweight = fillweight - prefillweight;
			int rightWidth = cumright;
			float fillratio = (float) (usableWidth - cumwidth)
					/ (float) fillweight;
			float prefillratio = (float) (xmiddle - prewidth)
					/ (float) prefillweight;
			float postfillratio = (float) (xmiddle - rightWidth)
					/ (float) postfillweight;
			int xright = parentSize.width - rightWidth - padding.right
					- this.hgap;
			for (int i = 0; i < ncomponents; i++)
			{
				Component comp = parent.getComponent(i);
				settings s = getSettings(comp);
				if (comp.isVisible())
				{
					Dimension d = useMinimum(comp, useMinimum);
					if (s.containsKey("WIDTH"))
					{
						d.width = s.getInteger("WIDTH");
					}
					int dwidth = d.width;
					int dheight = d.height;
					int ytop = (availableHeight-dheight)/4;
					 ytop=0;
					if (s.isTrue("FILLW"))
					{
						if (hasMiddle)
						{
							if (inRight) dwidth = (int) (dwidth * postfillratio);
							else
								dwidth = (int) (dwidth * prefillratio);
						} else
							dwidth = (int) (dwidth * fillratio);
					}
					if (s.isTrue("MIDDLE"))
					{
						if (!inMiddle)
						{
							x = xmiddle;
							inMiddle = true;
						}
					} else if (inMiddle)
					{
						inMiddle = false;
						hasRight = true;
					}
					if (s.isTrue("RIGHT")) hasRight = true;
					if (hasRight)
					{
						if (!inRight && !s.isTrue("FILLW"))
						{
							inRight = true;
							x = xright;
						}
					}
					comp.setBounds(x, ytop, dwidth, availableHeight);
					x += (dwidth + this.hgap);
				}
			}
		}

	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0) this.hgap = 0;
		padding = ((jswPanel) parent).getPadding();

		int w = 0;
		int h = 0;

		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible()) continue;
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
		return new Dimension(padding.left + padding.right + w, padding.top
				+ padding.bottom + h);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0) this.hgap = 0;
		padding = ((jswPanel) parent).getPadding();;
		int w = 0;
		int h = 0;

		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible()) continue;
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
		return new Dimension(padding.left + padding.right + w, padding.top
				+ padding.bottom + h);
	}


	public static String[] help()
	{
		String[] helpstring=new String[2];
		helpstring[0] = "line 1";
		helpstring[1] = "line 2";
		return helpstring;
	}


}