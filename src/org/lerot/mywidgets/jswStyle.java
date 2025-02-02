package org.lerot.mywidgets;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class jswStyle
{
	public static final int LINEBORDER = 1;
	public static final int NOBORDER =0;
	public static final int TITLEDBORDER = 2;
	public  int counter = 0;

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


	static String defaultfontname;
	static int defaultfontsize;
	static int defaultfontstyle;
	static String defaulttitlefontname;
	static int defaulttitlefontsize;
	static int defaulttitlefontstyle;
	static Color defaultbackgroundcolor;
	static Color defaultwidgetcolor;
	static Color defaulttextboxcolor;
	static Color TRANSPARENT;
	private Color defaultforegroundcolor;
	private Color defaulttitlefontcolor;
	static Color defaultbordercolor;
	static int defaultborderwidth;
	
	 String stylename="anon";
	Map<String, String> attributes = new HashMap<>();


	public jswStyle()
	{
		defaultforegroundcolor = Color.black;
		defaultfontname = "SansSerif";
		defaultfontstyle = Font.PLAIN;
		defaultfontsize = 12;
		defaultborderwidth = 1;
		defaulttitlefontname = defaultfontname ;
		defaulttitlefontstyle = defaultfontstyle ;
		defaulttitlefontsize = defaultfontsize;
		defaulttitlefontcolor =defaultforegroundcolor;
		defaultbordercolor = defaultforegroundcolor;
		defaultbackgroundcolor  = new Color(0,0,0,125);
		defaultwidgetcolor = new Color(200,240,240);
		defaulttextboxcolor = new Color(240,200,200);
		TRANSPARENT = new  Color(0,0,0,0);
	}

	public jswStyle(String name)
	{
		this.setStyleName(name);
	}


	public void copyAll(jswStyle basestyles)
	{
		if (basestyles == null) return;

		for (Map.Entry<String, String> entry : basestyles.attributes.entrySet())
		{
			String key = entry.getKey();
			String value = entry.getValue();
			if (value != null && !value.equalsIgnoreCase(""))
			{
				putAttribute(key, value);
			}
		}
		setStyleName(basestyles.getStyleName());
	}

	public void printList()
	{
		if (attributes == null) return;
		for (Map.Entry<String, String> entry : attributes.entrySet())
		{
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "::" + value);
		}
	}

	public Color getBackgroundcolor()
	{
		return getColor("backgroundcolor", defaultbackgroundcolor);
	}
	
	public Color getWidgetcolor()
	{
		return getColor("backgroundcolor", defaultwidgetcolor);
	}

	
	public Color getForegroundcolor()
	{
		return getColor("foregroundcolor", Color.black);
	}
	
	

	public boolean getBooleanStyle(String stylename)
	{
		return getBooleanStyle(stylename, false);
	}

	public boolean getBooleanStyle(String stylename, boolean b)
	{
		stylename = stylename.toLowerCase();
		if (attributes.containsKey(stylename))
		{
			String value = attributes.get(stylename);
			if (value == null || value.equalsIgnoreCase("") ) return b;
			value = value.toLowerCase();
			return value.startsWith("t");
		} else
			return b;
	}
	
	public  Border makecborder(String label)
	{
		Font tf = getTitleFont();
		Color bordercolor = getColor("borderColor", Color.BLACK);
		int borderwidth = getIntegerStyle("borderWidth", defaultborderwidth);
		 borderwidth = 1;
		Color titlecolor = getColor("titleColor", defaulttitlefontcolor);
	//    LineBorder lborder = new LineBorder ( bordercolor, borderwidth, true );
	//	Integer bwidth = style.getIntegerStyle("borderwidth");
		Color bcolor = getColor("bordercolor", jswStyle.defaultbordercolor);
	//	setBorder(jswStyle.makeLineBorder(bcolor, bwidth));
		 LineBorder lborder = new LineBorder ( bcolor, borderwidth , false );
	    TitledBorder tborder = new TitledBorder ( lborder, label, TitledBorder.LEFT,
	            TitledBorder.DEFAULT_POSITION, tf, bordercolor );
	    tborder.setTitleColor(titlecolor);
		return BorderFactory.createCompoundBorder(
				tborder,
				BorderFactory.createEmptyBorder(10, 5, 5, 5));
	}
	
	public   Border makeLineborder(Insets ins)
	{

		Color bordercolor = getColor("borderColor", Color.BLACK);
		int borderwidth = getIntegerStyle("borderWidth", defaultborderwidth);
		Color bcolor = getColor("bordercolor", jswStyle.defaultbordercolor);
		LineBorder lb = new LineBorder(bcolor, borderwidth,false);

		return BorderFactory.createCompoundBorder(
				lb,
				BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	public Border getBorder()
	{
		Color bordercolor = getColor("borderColor", Color.BLACK);
		int borderwidth = getIntegerStyle("borderWidth", 1);
		if (borderwidth > 0) return makeLineBorder(bordercolor, borderwidth);
		else
			return BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}

	public Border getCellBorder()
	{

		Color bordercolor = getColor("borderColor", Color.BLACK);
		int borderwidth = getIntegerStyle("borderWidth", 1);
		return makeCellBorder(bordercolor, borderwidth);

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
	
	public Font getTitleFont()
	{
		int fontsize = getIntegerStyle("titlefontsize", defaultfontsize);
		if (fontsize > 0)
		{
			String fontname = getStringStyle("titlefontname", defaultfontname);
			int fontstyle = getIntegerStyle("titlefontstyle", defaultfontstyle);
			return new Font(fontname, fontstyle, fontsize);
		} else
			return new Font("SansSerif", Font.PLAIN, 10);

	}


	public Integer getIntegerStyle(String stylename)
	{
		stylename = stylename.toLowerCase();
		if (attributes.containsKey(stylename))
		{
			String value = attributes.get(stylename);
			if (value == null || value.equalsIgnoreCase("") ) return null;
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

	public Integer getIntegerStyle(String stylename, int defaultint)
	{
		stylename = stylename.toLowerCase();
		if (attributes.containsKey(stylename))
		{
			String value = attributes.get(stylename);
			if (value == null || value.equalsIgnoreCase("" )) return defaultint;
			try
			{
				Integer ivalue = Integer.parseInt(value);
				if(ivalue<0) return defaultint;
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
		if (attributes.containsKey(stylename))
		{
			String value = attributes.get(stylename);
			if (value == null || value.equalsIgnoreCase("") ) return null;
			else
				return value;
		} else
			return null;
	}

	public String getStringStyle(String stylename, String defaultstyle)
	{
		stylename = stylename.toLowerCase();
		if (attributes.containsKey(stylename))
		{
			String value = attributes.get(stylename);
			if (value == null || value.equalsIgnoreCase("" )|| value.equalsIgnoreCase("0")) return defaultstyle;
			else
				return value;
		} else
			return defaultstyle;
	}


	public String getStyleName()
	{
		  return stylename;
	}


	

	public void overlay(jswStyle style)
	{
		copyAll(style);

	}

	public void putAttribute(String attribute, int value)
	{

		putAttribute(attribute, (Integer.toString(value)));

	}
	

	public void putAttribute(String attribute, String value)
	{
		attributes.put(attribute.toLowerCase(), value);
	}
	
	public void putAttribute(String attribute, boolean b)
	{
		if(b) putAttribute(attribute, "true");
		else putAttribute(attribute, "false");		
	}

	public void putAttribute(String attribute)
	{

		putAttribute(attribute, true);

	}

	public void setBackgroundcolor(String backgroundcolor)
	{
		putAttribute("backgroundColor", backgroundcolor);
	}



	public void setBordercolor(String bordercolor)
	{
		putAttribute("borderColor", bordercolor);
	}

	public void setBorderWidth(int border)
	{
		putAttribute("borderWidth", (Integer.toString(border)));
	}
	
	public void setBorderStyle(int border)
	{
       if(border< 0 || border > 2 ) border=0;
		putAttribute("borderStyle", (Integer.toString(border)));
	}

	public void setColspan(int colspan)
	{
		putAttribute("colspan", (Integer.toString(colspan)));
	}

	public void setDefaultStyle()
	{
		putAttribute("backgroundcolor", "transparent");
		//putAttribute("backgroundcolor", "#b1b9c8");
		putAttribute("borderColor", "black");
		putAttribute("borderWidth", "0");
		putAttribute("cellborderColor", "black");
		putAttribute("cellborderWidth", "0");
		putAttribute("colspan", "1");
		putAttribute("direction", "horizontal");
		putAttribute("fontname", "SansSerif");
		putAttribute("fontsize", "10");
		putAttribute("fontstyle", Font.PLAIN);

		putAttribute("foregroundColor", "black");

		putAttribute("headingborder", "1");
		putAttribute("horizontalAlign", null);
		putAttribute("interblockspacing", "0");
		putAttribute("layout", null);
		putAttribute("myHeight", "0");
		putAttribute("myWidth", "0");

		putAttribute("padding", "0");

		putAttribute("rowcount", "0");
		putAttribute("rowspan", "1");
		putAttribute("verticalAlign", null);

	}

	public void setDirection(String direction)
	{
		putAttribute("direction", direction.toLowerCase());
	}


	public void setFontname(String fontname)
	{
		putAttribute("fontname", fontname);
	}

	public void setFontsize(int fontsize)
	{
		putAttribute("fontsize", (Integer.toString(fontsize)));
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
		putAttribute("headingborder", (Integer.toString(headingborder)));
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
				(Integer.toString(interblockspacing)));
	}

	protected void setLayout(String layout)
	{
		putAttribute("layout", layout);
	}


	public void setMyHeight(int myHeight)
	{
		putAttribute("myHeight", (Integer.toString(myHeight)));
	}

	public void setMyWidth(int myWidth)
	{
		putAttribute("myWidth", (Integer.toString(myWidth)));
	}


	public void setPadding(int padding)
	{
		putAttribute("padding", String.valueOf(padding) );
	}

	public void setRowcount(int intvalue)
	{
		putAttribute("rowcount", String.valueOf(intvalue));
	}

	public void setRowspan(int intvalue)
	{
		putAttribute("rowspan", String.valueOf(intvalue));
	}


	public void setStyleName(String aname)
	{
		  stylename=aname;
	}


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

	public Dimension getDimension(int i, int j)
	{
		// TODO Auto-generated method stub
		return new Dimension(50,20);
	}
	public boolean hasBorder()
	{
		int borderwidth = getBorderWidth();
		return borderwidth > 0;
	}
	
	int getBorderWidth()
	{
		return getIntegerStyle("borderWidth", 0);
	}
	
	int getBorderStyle()
	{
		return getIntegerStyle("borderStyle", 0);
	}

	
	public FontMetrics getFontMetrics()
	{
		Font font = getFont( );
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(font);
		return fm;
	}


    public boolean hasAttribute(String stylename)
    {
		stylename = stylename.toLowerCase();
		return attributes.containsKey(stylename);
    }

	public Integer  getInteger(String stylename)
	{
		stylename = stylename.toLowerCase();
		if (attributes.containsKey(stylename))
		{
			String value = attributes.get(stylename);
			if (value == null || value.equalsIgnoreCase("") ) return 0;
			try
			{
				Integer ivalue = Integer.parseInt(value);
				return ivalue;
			} catch (NumberFormatException nfe)
			{
				return 0;
			}
		} else
			return 0;
	}
}
