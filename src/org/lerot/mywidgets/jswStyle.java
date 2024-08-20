package org.lerot.mywidgets;

import java.awt.Color;
<<<<<<< HEAD
import java.awt.Dimension;
=======
>>>>>>> master
import java.awt.Font;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class jswStyle
{
	protected static int counter = 0;
<<<<<<< HEAD
	public static jswStyle getDefaultStyle()
	{
		jswStyle newstyle = new jswStyle();
		newstyle.setDefaultStyle();
		return newstyle;
		
	}
	public static Border makeborder()
	{
		return BorderFactory.createEmptyBorder(5, 5, 5, 5);
	}
	public static Border makecborder(String label)
	{
		return BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(label),
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}
	public static  MatteBorder makeCellBorder(Color col, int w)
	{
		return BorderFactory.createMatteBorder(0, 0, w, w, col);
	}
	public static Border makeLineBorder()
	{
		return BorderFactory.createLineBorder(Color.BLACK, 1);
	}

	public static Border makeLineBorder(Color col, int w)
	{
		return BorderFactory.createLineBorder(col, w);
	}

	public static Border makeLineBorder(int w)
	{
		return BorderFactory.createLineBorder(Color.BLACK, w);
	}

	public static Color transparentColor()
	{
		return new Color(0, 0, 0, 0);
	}

	private String defaultfontname;

	private int defaultfontsize;

	private int defaultfontstyle;

	private String stylename="anon";

=======
	private String defaultfontname;
	private int defaultfontsize;
	private int defaultfontstyle;
	private String stylename="anon";
>>>>>>> master
	Map<String, String> styles = new HashMap<>();

	public jswStyle()
	{
		defaultfontname = "SansSerif";
		defaultfontstyle = Font.PLAIN;
		defaultfontsize = 10;
	}

	public jswStyle(String name)
	{
		this.setStyleName(name);
	}

<<<<<<< HEAD
	public void copyAll(jswStyle basestyles)
	{
		if (basestyles == null) return;
=======
	public String getStyleName()
	{
		  return stylename;
	}

	public void setStyleName(String aname)
	{
		  stylename=aname;
	}

	public void copyAll(jswStyle basestyles)
	{
		if (basestyles == null) return;

>>>>>>> master
		for (Map.Entry<String, String> entry : basestyles.styles.entrySet())
		{
			String key = entry.getKey();
			String value = entry.getValue();
			if (value != null && value != "")
			{
				putAttribute(key, value);
			}
<<<<<<< HEAD
		}
		setStyleName(basestyles.getStyleName());
	}

	public Color getBackgroundcolor()
	{
		return getColor("backgroundcolor", Color.pink);
	}

	
	public Color getForegroundcolor()
	{
		return getColor("foregroundcolor", Color.blue);
	}
	
	
=======

		}
		setStyleName(basestyles.getStyleName());

	}

>>>>>>> master
	public boolean getBooleanStyle(String stylename)
	{
		return getBooleanStyle(stylename, false);
	}

	public boolean getBooleanStyle(String stylename, boolean b)
	{
		stylename = stylename.toLowerCase();
		if (styles.containsKey(stylename))
		{
			String value = styles.get(stylename);
			if (value == null || value == "") return b;
			value = value.toLowerCase();
			if (value.startsWith("t")) return true;
			else
				return false;
		} else
			return b;
	}

	public Border getBorder()
	{

		Color bordercolor = getColor("borderColor", Color.BLACK);
		int borderwidth = getIntegerStyle("borderWidth", 2);
		if (borderwidth > 0) return makeLineBorder(bordercolor, borderwidth);
		else
			return null;

	}

	public Border getCellBorder()
	{

		Color bordercolor = getColor("borderColor", Color.BLACK);
		int borderwidth = getIntegerStyle("borderWidth", 1);
		return makeCellBorder(bordercolor, borderwidth);
		/*
		 * if (borderwidth > 0) return setCellBorder(bordercolor, borderwidth);
		 * else return null;
		 */

	}

	public Color getColor(String attribute, Color defaultcolor)
	{

		Color color = defaultcolor;
		try
		{
			String colorstr = getStringStyle(attribute).toUpperCase();
			if (colorstr.equalsIgnoreCase("TRANSPARENT")) color = new Color(0,
					0, 0, 0);
			else if (colorstr.startsWith("#"))
			{
				color = Color.decode(colorstr);
			} else
			{
				Field field = Class.forName("java.awt.Color")
						.getField(colorstr);
				color = (Color) field.get(null);
			}
		} catch (Exception e)
		{

		}
		return color;

		// p1.setBackground(new Color(0,0,0,125));
	}

	public Font getFont()
	{
		int fontsize = getIntegerStyle("fontsize", defaultfontsize);
		if (fontsize > 0)
		{
			String fontname = getStringStyle("fontname", defaultfontname);
			int fontstyle = getIntegerStyle("fontstyle", defaultfontstyle);
			return new Font(fontname, fontstyle, fontsize);
		} else
			return new Font("SansSerif", Font.PLAIN, 10);

	}

	public Integer getIntegerStyle(String stylename)
	{
		stylename = stylename.toLowerCase();
		if (styles.containsKey(stylename))
		{
			String value = styles.get(stylename);
			if (value == null || value == "") return null;
			try
			{
				Integer ivalue = Integer.parseInt(value);
				return ivalue;
			} catch (NumberFormatException nfe)
			{
				return null;
			}
		} else
			return null;
	}
<<<<<<< HEAD
	
=======

>>>>>>> master
	public Integer getIntegerStyle(String stylename, int defaultint)
	{
		stylename = stylename.toLowerCase();
		if (styles.containsKey(stylename))
		{
			String value = styles.get(stylename);
			if (value == null || value == "") return defaultint;
			try
			{
				Integer ivalue = Integer.parseInt(value);
				return ivalue;
			} catch (NumberFormatException nfe)
			{
				return defaultint;
			}
		} else
			return defaultint;
	}

	String getStringStyle(String stylename)
	{
		stylename = stylename.toLowerCase();
		if (styles.containsKey(stylename))
		{
			String value = styles.get(stylename);
			if (value == null || value == "") return null;
			else
				return value;
		} else
			return null;
	}

	public String getStringStyle(String stylename, String defaultstyle)
	{
		stylename = stylename.toLowerCase();
		if (styles.containsKey(stylename))
		{
			String value = styles.get(stylename);
			if (value == null || value == "") return defaultstyle;
			else
				return value;
		} else
			return defaultstyle;
	}

<<<<<<< HEAD
	public String getStyleName()
	{
		  return stylename;
	}



=======
>>>>>>> master
	public void overlay(jswStyle style)
	{
		copyAll(style);

	}

	public void putAttribute(String attribute, int value)
	{
<<<<<<< HEAD
		putAttribute(attribute, (Integer.toString(value)));

	}
	
=======
		putAttribute(attribute, (new Integer(value)).toString());

	}

>>>>>>> master
	public void putAttribute(String attribute, String value)
	{
		styles.put(attribute.toLowerCase(), value);
	}

	public void setBackgroundcolor(String backgroundcolor)
	{
		putAttribute("backgroundColor", backgroundcolor);
	}

<<<<<<< HEAD
=======
	public static Border makeborder()
	{
		return BorderFactory.createEmptyBorder(5, 5, 5, 5);
	}

>>>>>>> master
	public void setBordercolor(String bordercolor)
	{
		putAttribute("borderColor", bordercolor);
	}

	public void setBorderWidth(int border)
	{
<<<<<<< HEAD
		putAttribute("borderWidth", (Integer.toString(border)));
=======
		putAttribute("borderWidth", (new Integer(border)).toString());
	}



	public static  MatteBorder makeCellBorder(Color col, int w)
	{
		return BorderFactory.createMatteBorder(0, 0, w, w, col);
>>>>>>> master
	}

	public void setColspan(int colspan)
	{
<<<<<<< HEAD
		putAttribute("colspan", (Integer.toString(colspan)));
=======
		putAttribute("colspan", (new Integer(colspan)).toString());
>>>>>>> master

	}

	public void setDefaultStyle()
	{
		putAttribute("backgroundcolor", null);
<<<<<<< HEAD
=======

>>>>>>> master
		putAttribute("borderColor", "black");
		putAttribute("borderWidth", "-1");
		putAttribute("cellborderColor", "black");
		putAttribute("cellborderWidth", "-1");
		putAttribute("colspan", "1");
		putAttribute("direction", "horizontal");
		putAttribute("fontname", "SansSerif");
		putAttribute("fontsize", "10");
		putAttribute("fontstyle", Font.PLAIN);
<<<<<<< HEAD
		putAttribute("foregroundColor", "black");
=======
		putAttribute("foregroundColor", null);
		putAttribute("backgroundColor", null);
>>>>>>> master
		putAttribute("headingborder", "1");
		putAttribute("horizontalAlign", null);
		putAttribute("interblockspacing", "0");
		putAttribute("layout", null);
		putAttribute("myHeight", "0");
		putAttribute("myWidth", "0");
<<<<<<< HEAD
		putAttribute("padding", "0");
=======

		putAttribute("padding", "0");

>>>>>>> master
		putAttribute("rowcount", "0");
		putAttribute("rowspan", "1");
		putAttribute("verticalAlign", null);

	}

	public void setDirection(String direction)
	{
		putAttribute("direction", direction.toLowerCase());
	}
<<<<<<< HEAD
	
	
=======
>>>>>>> master

	public void setFontname(String fontname)
	{
		putAttribute("fontname", fontname);
	}

	public void setFontsize(int fontsize)
	{
<<<<<<< HEAD
		putAttribute("fontsize", (Integer.toString(fontsize)));
=======
		putAttribute("fontsize", (new Integer(fontsize)).toString());
>>>>>>> master

	}

	public void setFontstyle(int fontstyle)
	{
		putAttribute("fontstyle", fontstyle);
	}

	public void setForegroundcolor(String foregroundcolor)
	{
		putAttribute("foregroundColor", foregroundcolor);
	}

	public void setHeadingborder(int headingborder)
	{
<<<<<<< HEAD
		putAttribute("headingborder", (Integer.toString(headingborder)));
=======
		putAttribute("headingborder", (new Integer(headingborder)).toString());
>>>>>>> master

	}

	public void setHorizontalAlign(String ahorizontalAlign)
	{
		String ha = ahorizontalAlign.toLowerCase();
		String horizontalAlign;
		if (ha.startsWith("l")) horizontalAlign = "LEFT";
		else if (ha.startsWith("r")) horizontalAlign = "RIGHT";
		else
			horizontalAlign = "CENTER";
		putAttribute("horizontalAlign", horizontalAlign);
	}

	public void setInterblockspacing(int interblockspacing)
	{
		putAttribute("interblockspacing",
<<<<<<< HEAD
				(Integer.toString(interblockspacing)));
=======
				(new Integer(interblockspacing)).toString());
>>>>>>> master

	}

	protected void setLayout(String layout)
	{
		putAttribute("layout", layout);
	}

<<<<<<< HEAD
	public void setMyHeight(int myHeight)
	{
		putAttribute("myHeight", (Integer.toString(myHeight)));
=======
	public static Border makecborder(String label)
	{
		return BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(label),
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	public static Border makeLineBorder()
	{
		return BorderFactory.createLineBorder(Color.BLACK, 1);
	}

	public static Border makeLineBorder(Color col, int w)
	{
		return BorderFactory.createLineBorder(col, w);
	}

	public static Border makeLineBorder(int w)
	{
		return BorderFactory.createLineBorder(Color.BLACK, w);
	}

	public void setMyHeight(int myHeight)
	{
		putAttribute("myHeight", (new Integer(myHeight)).toString());
>>>>>>> master

	}

	public void setMyWidth(int myWidth)
	{
<<<<<<< HEAD
		putAttribute("myWidth", (Integer.toString(myWidth)));

	}

=======
		putAttribute("myWidth", (new Integer(myWidth)).toString());

	}



>>>>>>> master
	public void setPadding(int padding)
	{
		putAttribute("padding", (new Integer(padding)).toString());

	}

	public void setRowcount(int rowcount)
	{
		putAttribute("rowcount", (new Integer(rowcount)).toString());

	}

	public void setRowspan(int rowspan)
	{
		putAttribute("rowspan", (new Integer(rowspan)).toString());

	}

<<<<<<< HEAD
	public void setStyleName(String aname)
	{
		  stylename=aname;
	}

=======
>>>>>>> master
	public void setVerticalAlign(String averticalAlign)
	{
		String va = averticalAlign.toLowerCase();
		String verticalAlign;
		if (va.startsWith("t")) verticalAlign = "TOP";
		else if (va.startsWith("b")) verticalAlign = "BOTTOM";
		else
			verticalAlign = "MIDDLE";
		putAttribute("verticalAlign", verticalAlign);
	}
<<<<<<< HEAD
	public Dimension getDimension(int i, int j)
	{
		// TODO Auto-generated method stub
		return new Dimension(50,20);
	}
	
	

	

	
=======

	public static Color transparentColor()
	{
		return new Color(0, 0, 0, 0);
	}
>>>>>>> master

}
