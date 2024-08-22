package org.lerot.mywidgets;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import org.lerot.mywidgets.jswLayout.layout;
import org.lerot.mywidgets.jswLayout.settings;

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
		clayout = new layout[componentcount];

		int availableHeight = 0, cumwidth = -2 * hgap, fillweight = 0, prewidth = 0, cummiddle = 0, prefillweight = 0, postfillweight = 0, cumright = 0;
		boolean hasRight = false, hasMiddle = false;
		if (componentcount < 2) this.hgap = 0;
		Insets insets = parent.getInsets();

		Dimension parentSize = parent.getSize();
		int usableWidth = parentSize.width - insets.left - insets.right - 3;
		// paul fix is border
		availableHeight = parentSize.height - insets.top - insets.bottom;
		boolean useMinimum;
		useMinimum = preferredLayoutSize(parent).width > usableWidth;
		// useMinimum=false;

	/*	if (componentcount == 1)
		{
			Component comp = parent.getComponent(0);
			Dimension d = comp.getMinimumSize();
			int compwidth = d.width;
			settings s = getSettings(comp);
			int x = insets.left;
			if (s.isTrue("RIGHT")) x = insets.left + usableWidth - compwidth;
			else if (s.isTrue("MIDDLE"))
				x = (int) (insets.left + (usableWidth - compwidth) / 2.0);

			if (s.isTrue("FILLW"))
			{
				compwidth = usableWidth;
				x = insets.left;
			}
			comp.setBounds(x, insets.top, compwidth, availableHeight);
		} else
		{*/
		
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

				if (s.isTrue("FILLH"))
				{
					clayout[j].fillh = s.getInteger("FILLH");
					clayout[j].minheight = d.height;
				}
					if (s.containsKey("WIDTH"))
					{
						clayout[j].width= s.getInteger("WIDTH");
					}
				
					if (s.isTrue("FILLW"))
					{
						clayout[j].fillw = s.getInteger("FILLw");
					} 
					if (s.isTrue("MIDDLE"))
					{
						hasMiddle = true;
						clayout[j].hasMiddle = true;
					} else if (hasMiddle)
					{
						clayout[j].hasBottom = true;
					}
					if (s.isTrue("RIGHT"))
					{
						hasRight = true;
						clayout[j].hasRight = true;
					}
					if (s.containsKey("INDENT"))
					{
						clayout[j].indent = s.getInteger("INDENT");
					}				
					if (s.containsKey("WIDTH"))
					{
						clayout[j].width = s.getInteger("WIDTH");
					}
				}
			}
			hasRight = false;
			int x = insets.left;
			int y = insets.top;
			int xmiddle = insets.left + (usableWidth - cummiddle) / 2;
			boolean inMiddle = false, inRight = false;
			postfillweight = fillweight - prefillweight;
			int rightWidth = cumright;
			float fillratio = (float) (usableWidth - cumwidth)
					/ (float) fillweight;
			float prefillratio = (float) (xmiddle - prewidth)
					/ (float) prefillweight;
			float postfillratio = (float) (xmiddle - rightWidth)
					/ (float) postfillweight;
			int xright = parentSize.width - rightWidth - insets.right
					- this.hgap;
			for (int i = 0; i < componentcount; i++)
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

	

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0) this.hgap = 0;
		Insets insets = parent.getInsets();

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
		return new Dimension(insets.left + insets.right + w, insets.top
				+ insets.bottom + h);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0) this.hgap = 0;
		Insets insets = parent.getInsets();
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
		return new Dimension(insets.left + insets.right + w, insets.top
				+ insets.bottom + h);
	}


	public static String[] help()
	{
		String[] helpstring=new String[2];
		helpstring[0] = "line 1";
		helpstring[1] = "line 2";
		return helpstring;
	}


}