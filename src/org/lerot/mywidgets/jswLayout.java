/*
 * Created on 17-Jan-2005
 *
 * revised Paul Golder 26/4/2008
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager;

public abstract class jswLayout implements LayoutManager
{
	public static final int LEFT = 0;
	public static final int TOP = 0;
    public static final int DISTRIBUTE = 4;
    public static final int RIGHT = 3;
    public static final int MIDDLE = 2;
    public static final int BOTTOM = 3;

    public int gap = 5;
    int verticallayoutstyle = jswLayout.TOP;
    int horizontallayoutstyle = jswLayout.LEFT;
    protected Insets padding;
    private String status;
    protected layoutRecord[] layoutTable;
   // private int visiblecomponents;
   protected int visibleComponents;

    public class layoutRecord
    {
        int cindex = -1;
        int x = 0;
        int y = 0;

        int height = 0;
        int width = 0;
        int maxwidth = 0;
        int minwidth = 0;
        int maxheight = 0;
        int minheight = 0;
        int finalwidth;
        int bdwidth;
        int finalheight;
        Component comp = null;
        int fillw;
        int fillh;
        boolean hasMiddle = false;
        boolean hasBottom = false;
        boolean hasRight = false;
        int indent = 0;

        public String toString()
        {
            String outline = comp.getClass().getName() + ":" + fillw + "\n:"
                    + minwidth + ":" + width + ":" + maxwidth + ":" + finalwidth + "\n:"
                    + minheight + ":" + height + ":" + maxheight + ":" + finalheight;
            return outline;
        }

    }

    public void  makeLayout(Container parent)
    {
        int componentcount = parent.getComponentCount();
        int j = 0;
        layoutRecord[] clayout = new layoutRecord[componentcount];
        for (int i = 0; i < componentcount; i++)
        {
            Component comp = parent.getComponent(i);
            if (comp.isVisible())
            {
                jswStyle s;
                if(comp instanceof jswPanel)
                {
                    s = ((jswPanel) comp).getStyle();
                }
                else
                    s=new jswStyle();
                clayout[j] = new layoutRecord();
                clayout[j].cindex = i;
                clayout[j].comp = comp;
                Dimension d = comp.getMinimumSize();
                clayout[j].minwidth = d.width;
                clayout[j].finalheight = d.height;
                clayout[j].finalwidth = d.width;
                clayout[j].minheight = d.height;
                if (s.hasAttribute("WIDTH"))
                {
                    clayout[j].width = s.getInteger("WIDTH");
                }
                if (s.hasAttribute("MINWIDTH"))
                {
                    clayout[j].minwidth = s.getInteger("MINWIDTH");
                }
                if (s.hasAttribute("MAXWIDTH"))
                {
                    clayout[j].maxwidth = s.getInteger("MAXWIDTH");
                }
                if (s.hasAttribute("HEIGHT"))
                {
                    clayout[j].height = s.getInteger("HEIGHT");
                }
                if (s.hasAttribute("MINHEIGHT"))
                {
                    clayout[j].minheight = s.getInteger("MINHEIGHT");
                }
                if (s.hasAttribute("MAXHEIGHT"))
                {
                    clayout[j].maxheight = s.getInteger("MAXHEIGHT");
                }
                if (s.hasAttribute("FILLW"))
                {
                    clayout[j].fillw = s.getInteger("FILLW");
                }
                if (s.hasAttribute("FILLH"))
                {
                    clayout[j].fillh = s.getInteger("FILLH");
                }
                j = j + 1;
            }
        }
        visibleComponents = j;
        layoutTable= clayout;
    }


    @Override
    public void removeLayoutComponent(Component comp)
    {
        //codeTable.remove(comp);
    }

    public void addTag(String tag, jswPanel comp)
    {

        jswTagparser tagp = new jswTagparser(tag);
        String[] atoken= {"*","*"};
        while ( tagp.notfinished() )
        {
            atoken = tagp.getToken();
            if(atoken != null)
            {
                ((jswPanel) comp).setStyleAttribute(atoken[0], atoken[1]);
            }
        }
        
    }



    @Override
    public void addLayoutComponent(String tag, Component comp)
    {
        //addTag(tag, comp);
        if(comp instanceof jswPanel)
        {
            addTag(tag, (jswPanel)comp);
        }
        else
            System.out.println(" not taging:"+comp.getClass().getSimpleName());
    }

    int countDigits(String tag, int i)
    {
        int j, k = i, l = tag.length();
        if (tag.charAt(i) == '-')
        {
            k++;
        }
        for (j = k; (j < l) && Character.isDigit(tag.charAt(j)); j++)
            ;
        return j - i;
    }



    @Override
    public abstract void layoutContainer(Container parent);

    @Override
    public abstract Dimension minimumLayoutSize(Container parent);

    Integer parseArg(String tag, int i, int n)
    {
        Integer num = null;
        try
        {
            num = Integer.parseInt(tag.substring(i, i + n));
        } catch (Exception e)
        {
            System.out.println(" cannot parse " + tag + " at "
                    + tag.substring(i, i + n));
        }
        return num;
    }



    public Dimension useMinimum(Component comp, boolean usemin)
    {
        if (usemin) return comp.getMinimumSize();
        else
            return comp.getPreferredSize();
    }

    protected int getGap()
    {
        return gap;
    }

    protected void setGap(int gap)
    {
        this.gap = gap;
    }






}

