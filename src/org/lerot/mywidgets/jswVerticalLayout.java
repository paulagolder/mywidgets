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
        trace = "xxx";

		if (parent instanceof jswPanel)
		{
			if (((jswPanel) parent).getName().equals(trace))
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
        //int grosswidth =  parentSize.width;
        //int grossheight = parentSize.height;
		int usableWidth = parentSize.width - padding.left -padding.right;
		availableHeight = parentSize.height - padding.top - padding.bottom ;
        //boolean useMinimum;

		makeLayout(parent);

		if (visibleComponents== 0)
			vgap = 0;
		if (visibleComponents < 1)
			return;
		double fillratio = 1.0;

		if (visibleComponents == 1)
		{
			Component comp = layoutTable[0].comp;
			comp.setBounds(layoutTable[0].x + layoutTable[0].indent, 0, usableWidth, availableHeight);
			return;
		}

		int cumheight = 0;
		int fillheight = 0;

        Dimension mind = imputedMinimunSize();
		for (int j = 0; j < visibleComponents; j++)
		{
			if (layoutTable[j].fillh > 0)
			{
                fillheight += layoutTable[j].minheight;
			}
            cumheight += layoutTable[j].minheight;
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

        if (((jswPanel) parent).getName().equals(trace))
        {
            System.out.println("tracing " + trace);
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
                System.out.println("x:y:w:h " + layoutTable[j].x + ":" + layoutTable[j].y + ":" + layoutTable[j].finalwidth + ":"
						+ layoutTable[j].finalheight);
		}

	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
        Insets padding = ((jswPanel) parent).getPadding();
        //Insets padding = jswPanel.makePadding(((jswPanel) parent).getStyle().getStringStyle("padding", "1"));
        vgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", vgap);

        if (visibleComponents < 2)
            vgap = 0;
        Dimension mind = this.imputedMinimunSize();

        int prefwidth = padding.left + padding.right + mind.width; // binsets.left + binsets.right + w;
        int prefheight = padding.top + padding.bottom + mind.height + vgap * (visibleComponents - 1); // binsets.top + binsets.bottom + h;
        return new Dimension(prefwidth, prefheight);
    }

    public Dimension xminimumLayoutSize(Container parent)
    {
		Insets padding = jswPanel.makePadding(((jswPanel) parent).getStyle().getStringStyle("padding", "1"));
		vgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", vgap);
		componentcount = parent.getComponentCount();

        if (((jswPanel) parent).getName().equals(trace))
        {
            System.out.println("tracing " + trace);
        }
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
        String[] helpstring = new String[16];
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

		return helpstring;
	}

	

}
