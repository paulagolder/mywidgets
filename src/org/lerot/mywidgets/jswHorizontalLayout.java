package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class jswHorizontalLayout extends jswLayout
{
	private static final int DEFAULT_HGAP = 3;
	String flag = "";
	boolean track = false;
	private layout[] clayout;
	private boolean trace;


	public jswHorizontalLayout()
	{
		this(DEFAULT_HGAP);
	}

	public jswHorizontalLayout(int hgap)
	{
		this.setHgap(hgap);
	}

	@Override
	public void layoutContainer(Container parent)
	{
		trace=false;
		if(  ((jswPanel)parent).getPanelname().equalsIgnoreCase("Panel 4"))
		{
			System.out.println(" Panel 4");
			trace= true;
		}

		int componentcount = parent.getComponentCount();
		if (parent instanceof jswPanel)
		{
			if (((jswPanel) parent).getBorder() != null)
			{
				// padding inset = ((jswPanel) parent).getBorder().getBorderpadding(parent);
				// if(((jswPanel) parent).getStyle().hasBorder() )
				// borderwidth = ((jswPanel) parent).getStyle().getBorderWidth()+20;
				// top=inset.top+1;
				// left=inset.left+1;
				// right=inset.right+1;
				// bottom=inset.bottom+1;
			}
		}
		clayout = new layout[componentcount];
		int availableHeight = 0;
		boolean hasRight = false, hasMiddle = false;
		if (componentcount < 2)
			this.setHgap(0);
		padding = ((jswPanel) parent).getPadding();

		Dimension parentSize = parent.getSize();
		
		boolean useMinimum;
		//useMinimum = preferredLayoutSize(parent).width > usableWidth;
		useMinimum = true;
		

		
		int fillweight = 0;
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
                Border bd = ((JComponent) comp).getBorder();
                int bdwidth = 0;
                if(bd != null)
                {
                    Insets dbinsets = bd.getBorderInsets(comp);
                    clayout[j].insets =  dbinsets;
                    clayout[j].bdwidth =  dbinsets.left + dbinsets.right;
                }
                else
                {
                	clayout[j].bdwidth =0;
                	  clayout[j].insets =  new Insets(0,0,0,0);;
                }
				/*
				 * if (s.isTrue("FILLH")) { clayout[j].fillh = s.getInteger("FILLH");
				 * clayout[j].minheight = d.height; }
				 */
            
                     clayout[j].minwidth = d.width +  clayout[j].bdwidth;
       
				clayout[j].minheight = d.height;
				clayout[j].finalwidth = clayout[j].minwidth;
				if (s.containsKey("WIDTH"))
				{
					clayout[j].width = s.getInteger("WIDTH");
				}
				if (s.containsKey("MINWIDTH"))
				{
					clayout[j].minwidth = s.getInteger("MINWIDTH");
				}


				if (s.isTrue("FILLW"))
				{
					clayout[j].fillw = s.getInteger("FILLW");
					fillweight += clayout[j].fillw;
					// if(clayout[j].fillw < 2 )clayout[j].fillw=100;
				}
				if (s.containsKey("INDENT"))
				{
					clayout[j].indent = s.getInteger("INDENT");
				}

				j = j + 1;
			}

		}

		int visiblecomponents = j;
		int usableWidth = parentSize.width - padding.left - padding.right -5  - getHgap() *visiblecomponents;
		// paul fix is border
		availableHeight = parentSize.height - padding.top - padding.bottom;
		
		if (visiblecomponents == 1)
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
			return;
		} 
		if(trace)
		{
		System.out.println(usableWidth);
		}
		boolean delta = true;
		int loop = 0;
		float fillratio = 1;

		while (loop < 1)
		{
			int cumwidth =  0;

			int fillwidth = 0;
			delta = false;

			for (j = 0; j < visiblecomponents; j++)
			{
				clayout[j].finalwidth = clayout[j].minwidth;
				if (clayout[j].fillw > 0)
				{

					clayout[j].finalwidth = (int) (clayout[j].minwidth ); //* ((float) clayout[j].fillw / fillweight));
					//fillwidth += clayout[j].finalwidth+getHgap()+clayout[j].bdwidth;
					fillwidth += clayout[j].finalwidth;

				}
				if (clayout[j].width > 0)
				{
					clayout[j].finalwidth = clayout[j].width;
				}
				cumwidth += clayout[j].finalwidth+getHgap()+clayout[j].bdwidth;

			}

			//int sparespace = usableWidth - (cumwidth - fillwidth);
			int sparespace = usableWidth - cumwidth ;
			fillratio = (float) (sparespace + fillwidth) / (float) fillwidth;
			if(trace)
			{
			System.out.println(usableWidth+":"+cumwidth+":"+fillwidth+":"+fillratio);
			}
            int gap;
			if(((jswPanel) parent).getStyle().getBooleanStyle("distribute") == true)
            {
            	gap = sparespace/(visiblecomponents);
            }
            else
            	gap = getHgap();
			int x = padding.left+gap/2+clayout[0].insets.left;
			int y = padding.top;

			for (j = 0; j < visiblecomponents; j++)
			{
				clayout[j].y = y;
				clayout[j].x = x;
				if (clayout[j].fillw > 0)
				{
					//clayout[j].finalwidth = (int) (clayout[j].minwidth * ((float) clayout[j].fillw / fillweight)	* fillratio);
					clayout[j].finalwidth = (int) (clayout[j].minwidth *  fillratio);
				}
				x = x + clayout[j].finalwidth+gap+clayout[j].bdwidth;;
			}
			loop++;
		}

		for (j = 0; j < visiblecomponents; j++)
		{
			Component comp = clayout[j].comp;
			comp.setBounds(clayout[j].x, clayout[j].y, clayout[j].finalwidth, availableHeight);
		}

	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0)
			this.setHgap(0);
        setHgap(this.getHgap());
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
				w += this.getHgap();
			} // if
		} // for
		return new Dimension(padding.left + padding.right + w, padding.top + padding.bottom + h);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0)
			this.setHgap(0);
		int w = 0;
		int h = 0;

		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (!comp.isVisible())
				continue;
			Dimension d = comp.getPreferredSize();
			if (h < d.height)
			{
				h = d.height;
			} // if
			w += d.width;
			if (i != 0)
			{
				w += this.getHgap();
			}
		}
		return new Dimension(padding.left + padding.right + w, padding.top + padding.bottom + h);
	}

	public static String[] help()
	{
		String[] helpstring = new String[2];
		helpstring[0] = "lHorizontal Layout Help";
		helpstring[1] = "line 2";
		return helpstring;
	}

	/*		@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

@Override
	public Dimension preferredLayoutSize(jswPanel parent)
	{
		// TODO Auto-generated method stub
		return null;
	}*/



}