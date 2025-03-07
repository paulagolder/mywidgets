package org.lerot.mywidgets;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class jswStyles
{
	public static int font_large = 14;
	public static int font_medium = 12;
	public static int font_small = 10;
	public String name;
	public Map<String, jswStyle> stylelist = new HashMap<>();
	public jswStyles()
	{

	}

	public static jswStyles clone(String name, jswStyles source)
	{
		jswStyles cstyles = new jswStyles(name);
		for (Entry<String, jswStyle> entry : source.stylelist.entrySet())
		{
			jswStyle nstyle = new jswStyle();
			nstyle.copyAll(entry.getValue());
			cstyles.put(nstyle);

		}
		return cstyles;
	}

	public void copyStyles(jswStyles source)
	{
		for (Entry<String, jswStyle> entry : source.stylelist.entrySet())
		{
			jswStyle astyle =entry.getValue();
			jswStyle nstyle = new jswStyle(astyle.getStyleName());
			nstyle.copyAll(astyle);
			this.put(nstyle);
		}
	}

	void put(jswStyle nstyle)
	{
		stylelist.put(nstyle.getStyleName(), nstyle);
	}

	public jswStyles(String aname)
	{
		name = aname;
	}

	public static jswStyles getDefaultStyles()
	{
		jswStyles panelstyles = new jswStyles();
		jswStyle jswWidgetStyles = panelstyles.makeStyle("jswWidget");
		jswWidgetStyles.putAttribute("backgroundColor", "#e0dcdf");
		jswWidgetStyles.putAttribute("boxbackgroundColor", "GREEN");
		jswWidgetStyles.putAttribute("foregroundColor", "Black");
		jswWidgetStyles.putAttribute("borderWidth", "0");
		jswWidgetStyles.putAttribute("fontsize", "14");
		jswWidgetStyles.putAttribute("borderColor", "blue");
		jswStyle jswDropDownContactBoxStyles = panelstyles.makeStyle("jswDropDownContactBox");
		jswDropDownContactBoxStyles.putAttribute("backgroundColor", "#C0C0C0");
		jswDropDownContactBoxStyles.putAttribute("fontsize", "10");
		jswStyle jswScrollPaneStyles = panelstyles.makeStyle("jswScrollPaneStyles");
		jswScrollPaneStyles.putAttribute("backgroundColor", "#C0C0C0");
		jswScrollPaneStyles.putAttribute("fontsize", "10");
		jswStyle jswBorderStyle = panelstyles.makeStyle("borderstyle");
		jswBorderStyle.putAttribute("borderWidth", "1");
		// jswBorderStyle.putAttribute("borderColor", "#C0C0C0");
		jswBorderStyle.putAttribute("borderColor", "black");
		jswStyle hpanelStyle = panelstyles.makeStyle("hpanelstyle");
		hpanelStyle.putAttribute("borderWidth", "2");
		hpanelStyle.putAttribute("borderColor", "blue");
		hpanelStyle.putAttribute("height", "100");
		jswStyle pbStyle = panelstyles.makeStyle("jswPushButton");
		pbStyle.putAttribute("backgroundColor", "#C0C0C0");
		pbStyle.putAttribute("fontsize", "10");
		pbStyle.putAttribute("foregroundColor", "black");
		jswStyle greenfont = panelstyles.makeStyle("greenfont");
		greenfont.putAttribute("foregroundColor", "green");
		panelstyles.name = "default";
		return panelstyles;
	}

	public static jswStyles getDefaultTableStyles()
	{
		jswStyles tablestyles = new jswStyles();
		tablestyles.name = "defaulttable";
		jswStyle tablestyle = tablestyles.makeStyle("table");
		//tablestyle.putAttribute("backgroundColor", "#c0d6f2");
		tablestyle.putAttribute("borderwidth", "1");
		tablestyle.putAttribute("borderColor", "green");
		tablestyle.putAttribute("padding", 10);
		jswStyle cellstyle = tablestyles.makeStyle("cell");
		cellstyle.putAttribute("backgroundColor", "#c0d6f2");
		cellstyle.putAttribute("fontSize", "12");
		cellstyle.putAttribute("fontStyle", Font.PLAIN);
		cellstyle.putAttribute("borderColor", "black");
		cellstyle.putAttribute("borderWidth", 1);
		cellstyle.putAttribute("padding", 4);

		jswStyle row0style = tablestyles.makeStyle("row_0");
		row0style.putAttribute("fontStyle", Font.BOLD + Font.ITALIC);
		row0style.putAttribute("foregroundColor", "#0e56f2");
		jswStyle cell00style = tablestyles.makeStyle("cell_0_0");
		cell00style.putAttribute("foregroundColor", "transparent");
		jswStyle colstyle = tablestyles.makeStyle("col");
		colstyle.setHorizontalAlign("RIGHT");

		jswStyle col0style = tablestyles.makeStyle("col_0");
		col0style.putAttribute("fontStyle", Font.BOLD);
		col0style.putAttribute("fontSize", "16");
		col0style.putAttribute("minwidth", true);
		col0style.putAttribute("backgroundColor", "PINK");
		col0style.setHorizontalAlign("LEFT");

		jswStyle col3style = tablestyles.makeStyle("col_3");
		col3style.putAttribute("minwidth", true);

		return tablestyles;
	}

	public jswStyle getStyle(String name)
	{
		jswStyle astyle = null;
		if (stylelist.containsKey(name))
		{
			//System.out.println( " found "+name);
			astyle = stylelist.get(name);
		}
		return astyle;
	}

	public void copyStyle(String toname, String fromname)
	{
		if (stylelist.containsKey(fromname))
		{
			jswStyle fromstyle = stylelist.get(fromname);
			jswStyle newstyle = new jswStyle(toname);
			newstyle.copyAll(fromstyle);
			this.put(newstyle);
		}
	}

	public jswStyle makeStyle(String name)
	{
		jswStyle astyle = getStyle(name);
		if(astyle == null )
		{
		jswStyle newstyle = new jswStyle(name);
		stylelist.put(name, newstyle);
		return newstyle;
		}
		else
			return astyle;
	}

}
