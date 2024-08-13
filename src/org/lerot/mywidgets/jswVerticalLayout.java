package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.border.Border;

class layout
{
	int cindex = -1;
	int x = 0;
	int w = 0;
	int fillh = 0;
	int scrollh = 0;
	int width = 0;
	int maxwidth = 0;
	int minwidth = 0;
	int maxheight = 0;
	int minheight = 0;
	Dimension d = null;
	Component comp = null;
	boolean hasMiddle = false;
	boolean hasBottom = false;
	public int indent = 0;
	public int height = 0;
	public int y = 0;

	public String sysprint()
	{
		String outline = comp.getClass().getName() + ":" + minheight + ":" + fillh + ":" + scrollh + ":" + height + " ";// TODO
		// Auto-generated
		// method
		// stub
		return outline;
	}
}

public class jswVerticalLayout extends jswLayout
{

	private static final int DEFAULT_VGAP = 0;
	private int vgap;
	private layout[] clayout = new layout[10];
	private int componentcount = 0;
	private boolean trace = true;

	public jswVerticalLayout()
	{
		this(DEFAULT_VGAP);
	}

	public jswVerticalLayout(int vgap)
	{
		this.vgap = vgap;
	}

	@Override
	@SuppressWarnings("deprecation")
	public void layoutContainer(Container parent)
	{

		componentcount = parent.getComponentCount();
		clayout = new layout[componentcount];
		if (componentcount == 0)
			vgap = 0;
		int availableHeight = 0;
		// cumheight = -vgap, fillheight = 0, cumbottom = 0, scrollheight = 0;
		boolean hasBottom = false, hasMiddle = false;
		Insets insets = parent.getInsets();
		if (parent instanceof jswPanel)
		{
			Border aborder = ((JComponent) parent).getBorder();
			if (aborder != null)
				insets = aborder.getBorderInsets(parent);
		}

		int x = insets.left;
		int y = insets.top;
		Container theparent = null;
		if (theparent == null)
			theparent = parent;
		Dimension parentSize = theparent.getSize();

		int usableWidth = parentSize.width - insets.left - insets.right;
		availableHeight = parentSize.height - insets.top - insets.bottom;
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

				if (s.isTrue("FILLH"))
				{
					clayout[j].fillh = s.getInteger("FILLH");
					clayout[j].minheight = d.height;
				} else if (s.isTrue("SCROLLH"))
				{
					clayout[j].scrollh = s.getInteger("SCROLLH");
					clayout[j].minheight = d.height;
				} else
				{
					clayout[j].minheight = d.height;
				}
				clayout[j].height = clayout[j].minheight;
				if (s.isTrue("MAXHEIGHT"))
				{
					clayout[j].maxheight = s.getInteger("MAXHEIGHT");
					clayout[j].minheight = d.height;
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
				if (s.containsKey("INDENT"))
				{
					clayout[j].indent = s.getInteger("INDENT");
				}
				j = j + 1;
			}
		}
		int visiblecomponents = j;
		double fillratio = 1.0;
		double scrollratio = 1.0;

		boolean delta = true;
		int loop = 0;
		while (loop < 5)
		{
			int cumheight = 0;
			int fillheight = 0;
			int scrollheight = 0;
			int filltotal = 0;
			int scrolltotal = 0;
			delta = false;
			for (j = 0; j < visiblecomponents; j++)
			{
				if (clayout[j].fillh > 0)
				{
					fillheight += clayout[j].height;
					filltotal = clayout[j].fillh;
				} else if (clayout[j].scrollh > 0)
				{
					scrollheight += clayout[j].height;
					scrolltotal = clayout[j].scrollh;
				} else
				{
					cumheight += clayout[j].height;
				}

			}

			if (cumheight + fillheight + scrollheight < availableHeight)
			{
				if (fillheight > 0)
					fillratio = (float) (availableHeight - cumheight - scrollheight) / (float) fillheight;
			} else
			{
				if (scrollheight > 0)
					scrollratio = (float) (availableHeight - cumheight - fillheight) / (float) scrollheight;
			}
			y = 0;

			for (j = 0; j < visiblecomponents; j++)
			{
				clayout[j].y = y;
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
					delta = true;
				}
				y = y + clayout[j].height;
			}
			// delta =false;
			if (trace)
			{
				for (j = 0; j < visiblecomponents; j++)
				{
					System.out.println(clayout[j].sysprint());
				}
				System.out.println("--");
			}
			loop++;
		}
		if (visiblecomponents > 1)
		{
			for (j = 0; j < visiblecomponents; j++)
			{
				Component comp = clayout[j].comp;// parent.getComponent(clayout[j].cindex);
				if (comp instanceof jswScrollPane)
				{
					// ((jswScrollPane) comp).setMyBounds(clayout[j].x + clayout[j].indent,
					// clayout[j].y, usableWidth, clayout[j].height);
					((jswScrollPane) comp).setBounds(clayout[j].x + clayout[j].indent, clayout[j].y, usableWidth,
							clayout[j].height - 40);
				} else
					comp.setBounds(clayout[j].x + clayout[j].indent, clayout[j].y, usableWidth, clayout[j].height);
			}

		} else  if (visiblecomponents == 1)
		{
			Component comp = clayout[0].comp;
			if (comp instanceof jswScrollPane)
			{
				((jswScrollPane) comp).setBounds(clayout[0].x + clayout[0].indent, 0, usableWidth,
						availableHeight - 40);
			} else
				comp.setBounds(clayout[0].x + clayout[0].indent, 0, usableWidth, availableHeight);
		}
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
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
		int desiredheight = 0;
		for (int i = 0; i < ncomponents; i++)
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
		// return new Dimension(insets.left + insets.right + w, insets.top
		// + insets.bottom + h + 20);
		int prefwidth = insets.left + insets.right + binsets.left + binsets.right + w;
		int prefheight = insets.top + insets.bottom + binsets.top + binsets.bottom + desiredheight;
		// return new Dimension(prefwidth, prefheight + 50);
		return new Dimension(prefwidth, prefheight + 5);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
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
		// return new Dimension(prefwidth, prefheight + 50);
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
