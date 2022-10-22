package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.border.Border;

public class jswVerticalLayout extends jswLayout
{

	private static final int DEFAULT_VGAP = 0;
	private int vgap;

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
		boolean trace = false;
		if (parent instanceof jswPanel)
		{
			if (((jswPanel) parent).getTag().equals("trace"))
			{
				System.out.println("tracing");
				trace = true;
			}
		}
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0) vgap = 0;
		int availableHeight = 0, cumheight = -vgap, fillheight = 0, cumbottom = 0, scrollheight = 0;
		boolean hasBottom = false, hasMiddle = false;
		Insets insets = parent.getInsets();
		if (parent instanceof jswPanel)
		{
			Border aborder = ((JComponent) parent).getBorder();
			if (aborder != null) insets = aborder.getBorderInsets(parent);
		}

		int x = insets.left;
		int y = insets.top;
		Container theparent = null;
		if (trace)
		{
			Container p2 = parent;
			while (p2 != null)
			{
				Dimension p2size = p2.getSize();
				System.out.print(" p2 =" + p2.getClass().getSimpleName());
				System.out.println(" available w=" + p2size.width + " h="
						+ p2size.height);
				if (theparent == null
						&& p2.getClass().getSimpleName()
								.equalsIgnoreCase("jswVerticalPanel"))
					theparent = p2;
				p2 = p2.getParent();

			}
		}
		if (theparent == null) theparent = parent;
		Dimension parentSize = theparent.getSize();
		int usableWidth = parentSize.width - insets.left - insets.right;
		availableHeight = parentSize.height - insets.top - insets.bottom;
		boolean useMinimum;
		useMinimum = preferredLayoutSize(parent).height > availableHeight;
		useMinimum = false;
		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			settings s = getSettings(comp);
			if (comp.isVisible())
			{
				Dimension d = useMinimum(comp, useMinimum);
				if (trace)
				{
					System.out.print(comp.getClass().getSimpleName() + " "
							+ comp.getName());
					d = comp.getPreferredSize();
					System.out
							.println(" Wants w=" + d.width + " h=" + d.height);
				}
				if (s.isTrue("FILLH"))
				{
					cumheight += this.vgap;
					fillheight += d.height;
				} else if (s.isTrue("SCROLLH"))
				{
					cumheight += this.vgap;
					scrollheight += d.height;
					// System.out.println(comp.getClass().getSimpleName()
					// + " in scrollh ");
				} else
				{
					cumheight += d.height + this.vgap;
				}
				if (s.isTrue("MIDDLE"))
				{
					if (hasMiddle)
					{
					} else
					{
						hasMiddle = true;
					}
				} else if (hasMiddle) hasBottom = true;
				if (s.isTrue("BOTTOM")) hasBottom = true;
				if (hasBottom)
				{
					cumbottom += d.height + this.vgap;
				}
			}
		}
		int i = 0;
		boolean inBottom = false;
		int bottomHeight = cumbottom;
		double fillratio = 1.0;
		double scrollratio = 1.0;
		if (cumheight + fillheight + scrollheight < availableHeight)
		{
			if (fillheight > 0)
				fillratio = (float) (availableHeight - cumheight - scrollheight)
						/ (float) fillheight;
		} else
		{
			if (scrollheight > 0)
				scrollratio = (float) (availableHeight - cumheight - fillheight)
						/ (float) scrollheight;
		}
		if (trace)
		{
			System.out.println(" fillratio =" + fillratio);
			System.out.println(" scrollratio =" + scrollratio);
		}
		while (i < ncomponents)
		{
			Component comp = parent.getComponent(i);
			settings s = getSettings(comp);
			if (comp.isVisible())
			{
				Dimension d = useMinimum(comp, useMinimum);
				int dheight = d.height;
				int indent = 0;
				if (s.containsKey("INDENT"))
				{
					indent = s.getInteger("INDENT");
				}
				if (!hasMiddle)
				{
					if (s.isTrue("FILLH"))
					{
						if (ncomponents == 1) dheight = availableHeight;
						else
							dheight = (int) (dheight * fillratio);
					} else if (s.isTrue("SCROLLH"))
					{
						// if (ncomponents == 1) dheight = availableHeight;
						// else
						dheight = (int) (dheight * scrollratio) - 40;
						// paul fix needs sorting
					}
					if (s.isTrue("BOTTOM"))
					{
						if (!inBottom)
						{
							inBottom = true;
							y = availableHeight - bottomHeight + this.vgap
									+ insets.top;
						}
					}
				}
				if (comp instanceof jswScrollPane) ((jswScrollPane) comp)
						.setMyBounds(x + indent, y, usableWidth, dheight);
				else
					comp.setBounds(x + indent, y, usableWidth, dheight);
				y += (dheight + this.vgap);
				if (trace)
					System.out.println(comp.getClass().getSimpleName()
							+ " gets w=" + (usableWidth) + " h=" + dheight);
			}
			i++;
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
			if (aborder != null) binsets = aborder.getBorderInsets(parent);

		}
		if (ncomponents == 0) vgap = 0;
		Insets insets = parent.getInsets();
		int w = 0;
		int h = 0;
		int desiredheight = 0;
		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible()) continue;
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
		int prefwidth = insets.left + insets.right + binsets.left
				+ binsets.right + w;
		int prefheight = insets.top + insets.bottom + binsets.top
				+ binsets.bottom + desiredheight;
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
			if (aborder != null) binsets = aborder.getBorderInsets(parent);
		}
		if (ncomponents == 0) vgap = 0;
		Insets insets = parent.getInsets();
		int w = 0;
		int h = 0;
		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible()) continue;
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
		int prefwidth = insets.left + insets.right + binsets.left
				+ binsets.right + w;
		int prefheight = insets.top + insets.bottom + binsets.top
				+ binsets.bottom + h;
		// return new Dimension(prefwidth, prefheight + 50);
		return new Dimension(prefwidth, prefheight + 5);
	}
}
