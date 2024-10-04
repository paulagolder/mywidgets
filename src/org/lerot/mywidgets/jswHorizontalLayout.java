package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class jswHorizontalLayout extends jswLayout
{


	private String trace;
	int hgap = 1;
	//private layoutRecord[] layoutTable;


	public jswHorizontalLayout()
	{

	}

	@Override
	public void layoutContainer(Container parent)
	{
		trace = "xxx";
		verticallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("verticallayoutstyle", 0);
		horizontallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("horizontallayoutstyle", 0);
		hgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", hgap);
		padding = ((jswPanel) parent).padding;
		gap = hgap;
	//	int componentcount = parent.getComponentCount();
		//layoutTable = new layoutRecord[componentcount];
		int availableHeight ;
		boolean hasRight = false, hasMiddle = false;

		if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
		{
			System.out.println("traceing " + trace);
		}
		Dimension parentSize = parent.getSize();
		if (parent instanceof jswPanel)
		{
			if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
				System.out.println(" {" + parentSize + "} :" + padding + ":" + gap);
		}
		makeLayout(parent);
		if (visibleComponents < 2)
			gap = 0;
		int usableWidth = parentSize.width - padding.left - padding.right;// - gap * (visiblecomponents-1);
		availableHeight = parentSize.height - padding.top - padding.bottom;
		if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
		{
			System.out.println(" " + availableHeight + " : " +usableWidth);
		}

		if (visibleComponents == 1)
		{
			Component comp = layoutTable[0].comp;
			Dimension d = comp.getMinimumSize();
			int compwidth =  layoutTable[0].finalwidth;
		//	settings s = getSettings(comp);
			jswStyle s;
			if(comp instanceof jswPanel)
			{
				 s = ((jswPanel) comp).getStyle();
			}else
				s= new jswStyle();
			int x = padding.left;
			if (horizontallayoutstyle==jswLayout.RIGHT)
				x = -padding.right + usableWidth - compwidth;
			else if (horizontallayoutstyle==jswLayout.MIDDLE)
				x = (int) (padding.left + (usableWidth - compwidth) / 2.0);

			if (s.hasAttribute("FILLW"))
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

		for (int j = 0; j < visibleComponents; j++)
		{
			layoutTable[j].finalwidth = layoutTable[j].minwidth;
			if (layoutTable[j].fillw > 0)
			{
				layoutTable[j].finalwidth = (int) (layoutTable[j].minwidth) ;
				fillwidth += layoutTable[j].finalwidth;
			}
			if (layoutTable[j].width > 0)
			{
				layoutTable[j].finalwidth = layoutTable[j].width;
			}
			cumwidth += layoutTable[j].finalwidth + gap;// + clayout[j].bdwidth;
		}

		int sparespace = usableWidth - cumwidth;
		fillratio = (float) (sparespace + fillwidth) / (float) fillwidth;

		if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
			System.out.println(
					"y " + fillratio + ":" + usableWidth + ":" + cumwidth + ";" + sparespace + ":" + fillwidth);

		if (horizontallayoutstyle == jswLayout.DISTRIBUTE)
		{
			gap = sparespace / (visibleComponents - 1);
		}

		int x = padding.left; // + gap / 2 ;
		if (sparespace < 1)
		{
			gap = 1;
			fillratio = 0;
			x = padding.left;
		}
		for (int j = 0; j < visibleComponents; j++)
		{
			layoutTable[j].x = x;
			if (verticallayoutstyle == jswLayout.BOTTOM)
			{
				layoutTable[j].y = padding.bottom + availableHeight - layoutTable[j].finalheight;
			} else if (verticallayoutstyle == jswLayout.MIDDLE)
			{
				layoutTable[j].y =padding.top + (availableHeight - layoutTable[j].finalheight) / 2;
			} else
			{
				layoutTable[j].y = padding.top;
			}
			if (fillratio > 0 && fillratio < 20 && layoutTable[j].fillw >0)
			{
				layoutTable[j].finalwidth = (int) (layoutTable[j].minwidth * fillratio);
			}
			x = x + layoutTable[j].finalwidth + gap;// + clayout[j].bdwidth;

		}

		for (int j = 0; j < visibleComponents; j++)
		{
			Component comp = layoutTable[j].comp;
			 comp.setBounds(layoutTable[j].x, layoutTable[j].y, layoutTable[j].finalwidth, availableHeight);
			//comp.setBounds(clayout[j].x, clayout[j].y, clayout[j].finalwidth, clayout[j].finalheight);
			if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
				System.out.println("y2 " + layoutTable[j].x + ":" + layoutTable[j].y + ":" + layoutTable[j].finalwidth + ":"
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

	public static String[] getHelp()
	{
		String[] helpstring = new String[10];
		helpstring[0] = "Horizontal Layout Help";
		helpstring[1] = "Recognised keywords";
		helpstring[2] = "..WIDTH";
		helpstring[3] = "..MINWIDTH";
		helpstring[4] = "..FILLW";
		helpstring[5] = "..RIGHT";
		helpstring[6] = "..MIGGLE";
		helpstring[7] = "RecognisedStyles";
		helpstring[8] = "    horizontallayoutstyle";
		helpstring[9] = "        jswLayout.DISTRIBUTE";

		return helpstring;
	}

}