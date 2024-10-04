package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

public class jswVerticalLayout extends jswLayout
{

	private int vgap;
	private int componentcount = 0;
	private String trace;


	public jswVerticalLayout()
	{

	}

	@Override
	public void layoutContainer(Container parent)
	{
		trace = "panel2b";

		if (parent instanceof jswPanel)
		{
			if (((jswPanel) parent).getName().equals(trace))
			{
				System.out.println("tracing "+trace);
			}
		}
		padding = ((jswPanel) parent).padding;

		jswStyle parentstyle = ((jswPanel) parent).getStyle();
		vgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", vgap);
		verticallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("verticallayoutstyle", 0);
		horizontallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("horizontallayoutstyle", 0);
		int availableHeight = 0;
		boolean hasBottom = false, hasMiddle = false;
		Dimension parentSize = parent.getSize();
		int usableWidth = parentSize.width - padding.left -padding.right;
		availableHeight = parentSize.height - padding.top - padding.bottom ;
		boolean useMinimum;

		makeLayout(parent);

		if (((jswPanel) parent).getName().equals(trace))
		{
			System.out.println(" :");
		}
		if (visibleComponents== 0)
			vgap = 0;
		if (visibleComponents < 1)
			return;
		double fillratio = 1.0;
		double scrollratio = 1.0;

		if (visibleComponents == 1)
		{
			Component comp = layoutTable[0].comp;
			comp.setBounds(layoutTable[0].x + layoutTable[0].indent, 0, usableWidth, availableHeight);
			return;
		}

		int cumheight = 0;
		int fillheight = 0;
		//int filltotal = 0;

		for (int j = 0; j < visibleComponents; j++)
		{
			if (layoutTable[j].fillh > 0)
			{
				fillheight += layoutTable[j].finalheight;
			}
			cumheight += layoutTable[j].finalheight;
		}
	
		int sparespace = availableHeight - cumheight - vgap*visibleComponents + fillheight;
		if (sparespace > 0)
		{
			if (fillheight > 0)
				fillratio = (float) (sparespace ) / (float) fillheight;		
		}
		if (((jswPanel) parent).getName().equals(trace))
		{
			System.out.println(" padding :"+padding);
		}
		int gap;
		int y=0;
		if (verticallayoutstyle == jswLayout.DISTRIBUTE)
		{
			gap = (sparespace + vgap*visibleComponents )/ (visibleComponents );
		    y =   gap / 2 + padding.top ;
		} else
		{
			gap = vgap;
			y =   gap / 2 +  padding.top;		
		}

	
		for (int j = 0; j < visibleComponents; j++)
		{
			layoutTable[j].y = y;
			if (horizontallayoutstyle == jswLayout.RIGHT )
			{
				layoutTable[j].x =   padding.left+ usableWidth - layoutTable[j].finalwidth;
				
			}else if (horizontallayoutstyle == jswLayout.MIDDLE)
			{
				layoutTable[j].x= padding.left+ (usableWidth - layoutTable[j].finalwidth)/2;
			}else
			{
				layoutTable[j].x = padding.left  ;
			}
		
			if (layoutTable[j].fillh > 0)
			{
				layoutTable[j].finalheight = (int) (layoutTable[j].finalheight * fillratio);
			} else
			{
				layoutTable[j].finalheight = layoutTable[j].finalheight;
			}
			if (layoutTable[j].maxheight > 0 & layoutTable[j].finalheight > layoutTable[j].maxheight)
			{
				layoutTable[j].finalheight = layoutTable[j].maxheight;
			}
			y = y + layoutTable[j].finalheight + gap ;//+ clayout[j].bdwidth;
		}

		for (int j = 0; j < visibleComponents; j++)
		{
			Component comp = layoutTable[j].comp;
			comp.setBounds(layoutTable[j].x, layoutTable[j].y, usableWidth , layoutTable[j].finalheight);
			if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
				System.out.println("y2 " + layoutTable[j].x + ":" + layoutTable[j].y + ":" +  usableWidth + ":"
						+ layoutTable[j].finalheight);
		}

	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		Insets padding = jswPanel.makePadding(((jswPanel) parent).getStyle().getStringStyle("padding", "1"));
		vgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", vgap);
		componentcount = parent.getComponentCount();

		if (componentcount == 0)
			vgap = 0;

		int w = 0;
		int h = 0;
		int desiredheight = 0;
		for (int i = 0; i < componentcount; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible())
				continue;
			//settings s = getSettings(comp);
			jswStyle s = ((jswPanel)comp).getStyle();
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
		return new Dimension(prefwidth, prefheight);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		Insets padding = jswPanel.makePadding(((jswPanel) parent).getStyle().getStringStyle("padding", "1"));
		vgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", vgap);
		componentcount = parent.getComponentCount();

		if (componentcount == 0)
			vgap = 0;
		int ncomponents = parent.getComponentCount();
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
		int prefwidth = insets.left + insets.right + w;//+ binsets.left + binsets.right 
		int prefheight = insets.top + insets.bottom + h;//+ binsets.top + binsets.bottom 
		return new Dimension(prefwidth, prefheight + 5);
	}

	public static String[] getHelp()
	{
		String[] helpstring = new String[13];
		helpstring[0] = "Vertical Layout Help";
		helpstring[1] = "Recognised keywords";
		helpstring[2] = "..WIDTH";
		helpstring[3] = "..MINWIDTH";
		helpstring[3] = "..MAXWIDTH";
		helpstring[3] = "..MINHEIGHT";
		helpstring[3] = "..MAXHEIGHT";
		helpstring[4] = "..FILLW";
		helpstring[5] = "..RIGHT";
		helpstring[6] = "..MIDDLE";
		helpstring[7] = "..BOTTOM";
		helpstring[8] = "RecognisedStyles";
		helpstring[9] = "    horizontallayoutstyle";
		helpstring[10] = "        jswLayout.DISTRIBUTE";
		helpstring[11] = "        jswLayout.RIGHT";
		helpstring[12] = "        jswLayout.MIDDLE";


		return helpstring;
	}

	

}
