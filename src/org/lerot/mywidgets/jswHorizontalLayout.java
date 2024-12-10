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
    private int right = 2;
    private int middle = 1;
    private int left = 0;
    //private layoutRecord[] layoutTable;


    public jswHorizontalLayout()
    {

    }

    @Override
    public void layoutContainer(Container parent)
    {
        trace = "titlexx";
        verticallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("verticallayoutstyle", 0);
        horizontallayoutstyle = ((jswPanel) parent).getStyle().getIntegerStyle("horizontallayoutstyle", 0);
        hgap = ((jswPanel) parent).getStyle().getIntegerStyle("gap", hgap);
        padding = ((jswPanel) parent).padding;
        gap = hgap;
        //	int componentcount = parent.getComponentCount();
        //layoutTable = new layoutRecord[componentcount];

        boolean hasRight = false, hasMiddle = false;
        Dimension parentSize = parent.getSize();
        if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
        {
            System.out.println("tracing " + trace);
            //  if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
            // System.out.println(" {" + parentSize.width + ";" + parentSize.height + "} :" + padding + ":" + gap);
        }

        makeLayout(parent);
        Dimension ms = imputedMinimunSize();
        if (visibleComponents < 2)
            gap = 0;
        int usableWidth = parentSize.width - padding.left - padding.right;
        int usableHeight = parentSize.height - padding.top - padding.bottom;

        if (visibleComponents == 1)
        {
            Component comp = layoutTable[0].comp;
            Dimension d = comp.getMinimumSize();
            int compwidth = layoutTable[0].minwidth;
            //	settings s = getSettings(comp);
            jswStyle s;
            Integer horizontalalignstyle = jswLayout.NULL;
            if (comp instanceof jswPanel)
            {
                s = ((jswPanel) comp).getStyle();
                horizontalalignstyle = s.getIntegerStyle("horizontalalignstyle");
            } else
                s = new jswStyle();
            int x = padding.left;
            if (horizontallayoutstyle == jswLayout.RIGHT)
                x = -padding.right + usableWidth - compwidth;
            else if (horizontallayoutstyle == jswLayout.MIDDLE)
                x = (int) (padding.left + (usableWidth - compwidth) / 2.0);
            if (horizontalalignstyle != null && horizontalalignstyle >= 0)
            {
                if (horizontalalignstyle == jswLayout.RIGHT)
                    x = -padding.right + usableWidth - compwidth;
                else if (horizontalalignstyle == jswLayout.MIDDLE)
                    x = (int) (padding.left + (usableWidth - compwidth) / 2.0);


            }

            if (s.hasAttribute("FILLW"))
            {
                compwidth = usableWidth;
                x = padding.left;
            }
            comp.setBounds(x, padding.top, compwidth, usableHeight);
            return;
        }

        int cumwidth = -gap;
        int middlewidth = 0;
        int rightwidth = 0;
        float fillratio = 1;
        int fillwidth = 0;
        int status = left;

        for (int j = 0; j < visibleComponents; j++)
        {
            if (status == left)
            {
                if (layoutTable[j].align == jswLayout.MIDDLE) status = middle;
                if (layoutTable[j].align == jswLayout.RIGHT) status = right;
            }
            if (status == middle)
            {
                if (layoutTable[j].align == jswLayout.RIGHT) status = right;
                else
                {
                    middlewidth += layoutTable[j].minwidth + gap;
                }
            }
            if (status == right)
            {
                rightwidth += layoutTable[j].minwidth + gap;
            }
            if (layoutTable[j].fillw > 0)
            {
                layoutTable[j].minwidth = (int) (layoutTable[j].minwidth);
                fillwidth += layoutTable[j].minwidth;
            }
            cumwidth += layoutTable[j].minwidth + gap;// + clayout[j].bdwidth;
        }

        int sparespace = usableWidth - cumwidth;
        fillratio = (float) (sparespace + fillwidth) / (float) fillwidth;

        if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
        {
            System.out.println(
                    "y1 fr uw cw sp fw " + fillratio + ":" + usableWidth + ":" + cumwidth + ";" + sparespace + ":" + fillwidth);
            System.out.println(
                    "y2 mw rw  " + middlewidth + ":" + rightwidth);
        }
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

        if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
        {
            System.out.println("tracing 2 " + trace);
        }
        status = left;
        for (int j = 0; j < visibleComponents; j++)
        {
            jswStyle s = ((jswPanel) layoutTable[j].comp).getStyle();
            int horizontalalignstyle = layoutTable[j].align;
            // int compwidth = layoutTable[0].minwidth;
            // layoutTable[j].x = x;
            if (verticallayoutstyle == jswLayout.BOTTOM)
            {
                layoutTable[j].y = padding.bottom + usableHeight - layoutTable[j].minheight;
            } else if (verticallayoutstyle == jswLayout.MIDDLE)
            {
                layoutTable[j].y = padding.top + (usableHeight - layoutTable[j].minheight) / 2;
            } else
            {
                layoutTable[j].y = padding.top;
            }
            if (fillratio > 0 && fillratio < 20 && layoutTable[j].fillw > 0)
            {
                layoutTable[j].minwidth = (int) (layoutTable[j].minwidth * fillratio);
            }

            if (status == left)
            {
                if (layoutTable[j].align == jswLayout.MIDDLE)
                {
                    x = usableWidth / 2 - middlewidth / 2;
                    status = middle;
                } else if (layoutTable[j].align == jswLayout.RIGHT)
                {
                    x = -padding.right + usableWidth - rightwidth;
                    status = right;
                } else
                    layoutTable[j].x = x;
            }
            if (status == middle)
            {
                if (layoutTable[j].align == jswLayout.RIGHT)
                {
                    x = -padding.right + usableWidth - rightwidth;
                    status = right;
                } else
                    layoutTable[j].x = x;
            }
            if (status == right)
            {
                layoutTable[j].x = x;
            }
            x = x + layoutTable[j].minwidth + gap;// + clayout[j].bdwidth;
        }

        for (int j = 0; j < visibleComponents; j++)
        {
            Component comp = layoutTable[j].comp;
            comp.setBounds(layoutTable[j].x, layoutTable[j].y, layoutTable[j].minwidth, usableHeight);
            //comp.setBounds(clayout[j].x, clayout[j].y, clayout[j].finalwidth, clayout[j].finalheight);
            if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
            {
                System.out.println("x:y:w:h " + layoutTable[j].x + ":" + layoutTable[j].y + ":" + layoutTable[j].minwidth + ":"
                        + usableHeight);
                System.out.println(layoutTable[j]);
            }
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
        String[] helpstring = new String[14];
        helpstring[0] = "Horizontal Layout Help";
        helpstring[1] = "Recognised keywords";
        helpstring[2] = "..WIDTH";
        helpstring[3] = "..MINWIDTH";
        helpstring[4] = "..FILLW";
        helpstring[5] = "..RIGHT";
        helpstring[6] = "..MIDDLE";
        helpstring[7] = "RecognisedStyles";
        helpstring[8] = "    horizontallayoutstyle";
        helpstring[9] = "        jswLayout.DISTRIBUTE";
        helpstring[10] = "   horizontalalignstyle";
        helpstring[11] = "       jswLayout.TOP";
        helpstring[12] = "       jswLayout.MIDDLE";
        helpstring[13] = "       jswLayout.BOTTOM";
        return helpstring;
    }

}