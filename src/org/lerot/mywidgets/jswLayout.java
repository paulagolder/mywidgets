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
import java.util.HashMap;
import java.util.Hashtable;

public abstract class jswLayout implements LayoutManager
{
    static final int LEFT = 0;
	private static final  int TOP=0;
	public static final int DISTRIBUTE = 4;
	public static final int RIGHT = 3;
	public static final int MIDDLE = 2;
	public static final int BOTTOM = 3;

	public int gap =5;
	int verticallayoutstyle =jswLayout.TOP;
	int horizontallayoutstyle =jswLayout.LEFT;
	protected Insets padding;
	
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
		public boolean hasRight;
		public int fillw;
		public int finalwidth;
		public int bdwidth;
		//public Insets insets;
		public int finalheight;
	

		public String sysprint()
		{
			String outline = comp.getClass().getName() + ":" + minwidth + ":" + fillw + ":" + scrollh + ":" + height + " ";// TODO
			// Auto-generated
			// method
			// stub
			return outline;
		}
	}

	
	class settings extends HashMap<String, Integer>
	{

		private static final long serialVersionUID = 1L;

		public settings()
		{
			super();
		}

		public int getInteger(String key)
		{
			if (!containsKey(key) || (get(key) == null)) return -1;
			return (get(key)).intValue();
		}

		public boolean isTrue(String key)
		{
			if (!containsKey(key) || (get(key) == null)) return false;
			int v = (get(key)).intValue();
			if (v < 1) return false;
			else
				return true;
		}

		public void putBoolean(String key, boolean value)
		{
			if (value) putInt(key, 1);
			else
				putInt(key, 0);
		}

		public void putInt(String key, int value)
		{
			put(key, value);
		}

	}

	Hashtable<Object, settings> codeTable = new Hashtable<>();
	


	public void addTag(String tag, Component comp)
	{
		settings nsetting = getSettings(comp);
		tag = tag.toUpperCase().trim();
		int harg = 0, varg = 0;
		int i, l = tag.length(), n;
		for (i = 0; i < l;)
		{
			if (tag.startsWith("CENTER", i))
			{
				i += 6;
				nsetting.putBoolean("CENTER", true);
			} else if (tag.startsWith("MIDDLE", i))
			{
				i += 6;
				nsetting.putBoolean("MIDDLE", true);
			} else if (tag.startsWith("LEFT", i))
			{
				i += 4;
				nsetting.putBoolean("LEFT", true);
			} else if (tag.startsWith("TOP", i))
			{
				i += 3;
				nsetting.putBoolean("TOP", true);
			} else if (tag.startsWith("RIGHT", i))
			{
				i += 5;
				nsetting.putBoolean("RIGHT", true);
			} else if (tag.startsWith("BOTTOM", i))
			{
				i += 6;
				nsetting.putBoolean("BOTTOM", true);
			} else if (tag.startsWith("MINWIDTH", i))
			{
				i += 8;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					varg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("MINWIDTH", varg);
				} else
				{
					varg = -1;
					break;
				}
			} else if (tag.startsWith("MINHEIGHT", i))
			{
				i += 9;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					varg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("MINHEIGHT", varg);
				} else
				{
					varg = -1;
					break;
				}

			} else if (tag.startsWith("WIDTH", i))
			{
				i += 5;
				if (tag.startsWith("=", i))

				{
					i++;
					n = countDigits(tag, i);
					harg = parseArg(tag, i, n);
					i += n;
				} else
					harg = 0;

				nsetting.putInt("WIDTH", harg);
			} else if (tag.startsWith("HEIGHT", i))
			{
				i += 6;
				if (tag.startsWith("=", i))

				{
					i++;
					n = countDigits(tag, i);
					varg = parseArg(tag, i, n);
					i += n;
				} else
					varg = 0;

				nsetting.putInt("HEIGHT", varg);
			} else if (tag.startsWith("FILLW", i))
			{
				i += 5;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					harg = parseArg(tag, i, n);
					i += n;
				} else
					harg = 1;
				
				nsetting.putInt("FILLW",harg);
			} else if (tag.startsWith("FILLH", i))
			{
				i += 5;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					harg = parseArg(tag, i, n);
					i += n;
				} else
					harg = 0;
				harg = 1;
				nsetting.putInt("FILLH",harg);
			} else if (tag.startsWith("SCROLLH", i))
			{
				i += 7;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					harg = parseArg(tag, i, n);
					i += n;
				} else
					harg = 0;
				harg = 1;
				nsetting.putInt("SCROLLH",harg);
			} else if (tag.startsWith("FILLB", i))
			{
				i += 5;
				if (tag.startsWith("=", i))

				{
					i++;
					n = countDigits(tag, i);
					harg = varg = parseArg(tag, i, n);
					i += n;
				} else
					harg = varg = 1;
				nsetting.putBoolean("FILLW", true);
				nsetting.putBoolean("FILLH", true);
			}

			else if (tag.startsWith("WIDTH", i))
			{
				i += 5;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					harg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("WIDTH", harg);
				} else
				{
					harg = -1;
					break;
				}
			} else if (tag.startsWith("HEIGHT", i))
			{
				i += 6;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					varg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("HEIGHT", varg);
				} else
				{
					varg = -1;
					break;
				}
			} else if (tag.startsWith("MAXHEIGHT", i))
			{
				i += 9;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					varg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("MAXHEIGHT", varg);
				} else
				{
					varg = -1;
					break;
				}
			} else if (tag.startsWith("INDENT", i))
			{
				i += 6;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					varg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("INDENT", varg);
				} else
				{
					varg = -1;
					break;
				}
			} else if (tag.startsWith("COLSPAN", i))
			{
				i += 7;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					harg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("COLSPAN", harg);
				} else
				{
					harg = -1;
					break;
				}
			} else if (tag.startsWith("COL", i))
			{

				i += 3;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					harg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("COL", harg);
				} else
				{
					harg = -1;
					break;
				}
			} else if (tag.startsWith("ROW", i))
			{
				i += 3;
				if (tag.startsWith("=", i))
				{
					i++;
					n = countDigits(tag, i);
					harg = parseArg(tag, i, n);
					i += n;
					nsetting.putInt("ROW", harg);
				} else
				{
					harg = -1;
					break;
				}
			}

			else if (tag.startsWith("FLUSH", i))
			{
				i += 5;
				nsetting.putBoolean("FLUSHV", true);
				nsetting.putBoolean("FLUSHH", true);
			} else
			{
				harg = -1;
				break;
			}
			for (; (i < l) && Character.isSpace(tag.charAt(i)); i++)
				; // skip
			// whitesp.
		}
		if ((harg == -1) || (varg == -1)) System.out
				.println("jswLayout: can't understand \"" + tag + "\"");
		else
		{
			codeTable.put(comp, nsetting);
		}
	}

	@Override
	public void addLayoutComponent(String tag, Component comp)
	{
		addTag(tag, comp);
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

	public settings getSettings(Component comp)
	{
		settings s = codeTable.get(comp);
		if (s == null) s = new settings();
		return s;

	}

	public boolean isSettingTrue(Component comp, String v)
	{
		settings s = codeTable.get(comp);
		return s.isTrue(v);
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


	//public abstract Dimension preferredLayoutSize(jswPanel  parent);

	/** Remove the specified component from the layout. */
	@Override
	public void removeLayoutComponent(Component comp)
	{
		codeTable.remove(comp);
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

/*	public Dimension minimumLayoutSize(Component parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void setIndent(int i)
	{
		// TODO Auto-generated method stub
		
	}

	public static Insets getInserts(String stringStyle)
	{
		// TODO Auto-generated method stub
		return null;
	}
*/



}
