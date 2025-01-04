package org.lerot.mywidgets;

import javax.swing.*;
import javax.swing.border.Border;
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
		if(((jswPanel) parent).trace)
		{
			trace=  ((jswPanel) parent).getPanelname();
		}

		if (parent instanceof jswPanel)
		{
			if (parent.getName().equals(trace))
			{
				System.out.println("tracing "+trace);
			}
		}
		padding = ((jswPanel) parent).padding;

		jswStyle parentstyle = ((jswPanel) parent).getStyle();
        vgap = parentstyle.getIntegerStyle("gap", vgap);
        verticallayoutstyle = parentstyle.getIntegerStyle("verticallayoutstyle", 0);
        horizontallayoutstyle = parentstyle.getIntegerStyle("horizontallayoutstyle", 0);
		int availableHeight = 0;
		boolean hasBottom = false, hasMiddle = false;
		Dimension parentSize = parent.getSize();
		int usableWidth = parentSize.width - padding.left -padding.right;
		availableHeight = parentSize.height - padding.top - padding.bottom ;
		makeLayout(parent);
		if (parent.getName().equals(trace))
		{
			System.out.println("tracing "+trace);

		}
		if (visibleComponents== 0)
			vgap = 0;
		if (visibleComponents < 1)
			return;
		double fillratio = 1.0;

		if (visibleComponents == 1)
		{
			Component comp = layoutTable[0].comp;
			comp.setBounds(layoutTable[0].x + padding.left, padding.top, usableWidth, availableHeight);
			return;
		}

		int cumheight = 0;
		int fillheight = 0;

        //Dimension mind = imputedMinimunSize();
		for (int j = 0; j < visibleComponents; j++)
		{
			if (layoutTable[j].fillh > 0)
			{
                fillheight += layoutTable[j].minheight;
			}
            cumheight += layoutTable[j].minheight;
			if (parent.getName().equals(trace))
			{

				System.out.println(layoutTable[j].toString());
			}
		}
	
		int sparespace = availableHeight - cumheight - vgap*visibleComponents + fillheight;
		if (sparespace > 0)
		{
			if (fillheight > 0)
				fillratio = (float) (sparespace ) / (float) fillheight;		
		}
		if (parent.getName().equals(trace))
		{

			System.out.println(availableHeight+":"+cumheight+":"+fillheight);
			System.out.println(sparespace+":"+fillheight);
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

		if (parent.getName().equals(trace))
        {
            System.out.println("tracing 2 " + trace);
        }
		for (int j = 0; j < visibleComponents; j++)
		{
			layoutTable[j].y = y;
            if (layoutTable[j].width > usableWidth) layoutTable[j].finalwidth = layoutTable[j].minwidth;
            else layoutTable[j].finalwidth = layoutTable[j].width;
            if (layoutTable[j].fillw > 0)
			{
                layoutTable[j].x = padding.left;
                layoutTable[j].finalwidth = usableWidth;
            } else if (horizontallayoutstyle == jswLayout.RIGHT)
            {
                layoutTable[j].x = padding.left + usableWidth - layoutTable[j].minwidth;
			}else if (horizontallayoutstyle == jswLayout.MIDDLE)
			{
                layoutTable[j].x = padding.left + (usableWidth - layoutTable[j].minwidth) / 2;
                if (layoutTable[j].x < 1) layoutTable[j].x = padding.left;
			}else
			{
				layoutTable[j].x = padding.left  ;
                layoutTable[j].finalwidth = layoutTable[j].minwidth;
			}
		
			if (layoutTable[j].fillh > 0)
			{
                layoutTable[j].finalheight = (int) (layoutTable[j].minheight * fillratio);
			} else
			{
                layoutTable[j].finalheight = layoutTable[j].minheight;
			}
            if (layoutTable[j].maxheight > 0 & layoutTable[j].minheight > layoutTable[j].maxheight)
			{
				layoutTable[j].finalheight = layoutTable[j].maxheight;
			}
			y = y + layoutTable[j].finalheight + gap ;//+ clayout[j].bdwidth;
		}

		for (int j = 0; j < visibleComponents; j++)
		{
			Component comp = layoutTable[j].comp;
            comp.setBounds(layoutTable[j].x, layoutTable[j].y, layoutTable[j].finalwidth, layoutTable[j].finalheight);
			if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
                System.out.println("x:y:w:h " +((jswPanel) comp).getPanelname()+":"+ layoutTable[j].x + ":" + layoutTable[j].y + ":" + layoutTable[j].finalwidth + ":"
						+ layoutTable[j].finalheight);
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
			if (w < d.width)
			{
				w = d.width;
			} // if
			h += d.height;
			if (i != 0)
			{
				h += this.getGap();
			} // if
		} // for
		return new Dimension(insets.left + insets.right + w, insets.top + insets.bottom + h);
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
        String[] helpstring = new String[19];
		helpstring[0] = "Vertical Layout Help";
		helpstring[1] = "Recognised keywords";
		helpstring[2] = "..WIDTH";
		helpstring[3] = "..MINWIDTH";
        helpstring[4] = "..MAXWIDTH";
        helpstring[5] = "..MINHEIGHT";
        helpstring[6] = "..MAXHEIGHT";
        helpstring[7] = "..FILLW";
        helpstring[8] = "..RIGHT";
        helpstring[9] = "..MIDDLE";
        helpstring[10] = "..BOTTOM";
        helpstring[11] = "RecognisedStyles";
        helpstring[12] = "    horizontallayoutstyle";
        helpstring[13] = "        jswLayout.DISTRIBUTE";
        helpstring[14] = "        jswLayout.RIGHT";
        helpstring[15] = "        jswLayout.MIDDLE";
		helpstring[16] = "    verticallayoutstyle";
		helpstring[17] = "        jswLayout.DISTRIBUTE";
		helpstring[18] = "        jswLayout.MIDDLE";

		return helpstring;
	}

	

}
