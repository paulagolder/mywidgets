package org.lerot.mywidgets;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
//import jswStyle;

public class jswStyles
{
	public static int font_large = 14;
	public static int font_medium = 12;
	public static int font_small = 10;
	public String name;
=======
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class jswStyles
{
    public String name;
>>>>>>> master
	public Map<String, jswStyle> stylelist = new HashMap<>();

	public jswStyles()
	{

	}

	public static jswStyles clone(String name, jswStyles source)
	{
		jswStyles cstyles = new jswStyles(name);
<<<<<<< HEAD
		for (Entry<String, jswStyle> entry : source.stylelist.entrySet())
=======
		for( Entry<String, jswStyle> entry : source.stylelist.entrySet())
>>>>>>> master
		{
			jswStyle nstyle = new jswStyle();
			nstyle.copyAll(entry.getValue());
			cstyles.put(nstyle);

		}
		return cstyles;
	}

<<<<<<< HEAD
	public void copyStyles(jswStyles source)
	{

		for (Entry<String, jswStyle> entry : source.stylelist.entrySet())
=======
	public  void copyStyles( jswStyles source)
	{

		for( Entry<String, jswStyle> entry : source.stylelist.entrySet())
>>>>>>> master
		{
			jswStyle nstyle = new jswStyle();
			nstyle.copyAll(entry.getValue());
			this.put(nstyle);

		}

	}

<<<<<<< HEAD
	void put(jswStyle nstyle)
	{
		stylelist.put(nstyle.getStyleName(), nstyle);
=======
	private void put(jswStyle nstyle) {
		stylelist.put(nstyle.getStyleName(),nstyle);
>>>>>>> master

	}

	public jswStyles(String aname)
	{
<<<<<<< HEAD
		name = aname;
=======
        name= aname;
>>>>>>> master
	}

	public static jswStyles getDefaultStyles()
	{
<<<<<<< HEAD
		jswStyles panelstyles = new jswStyles();

		jswStyle jswWidgetStyles = panelstyles.makeStyle("jswWidget");
		jswWidgetStyles.putAttribute("backgroundColor", "#e0dcdf");
		jswWidgetStyles.putAttribute("boxbackgroundColor", "GREEN");
		jswWidgetStyles.putAttribute("foregroundColor", "Black");
		jswWidgetStyles.putAttribute("borderWidth", "0");
		jswWidgetStyles.putAttribute("fontsize", "14");
		jswWidgetStyles.putAttribute("borderColor", "blue");

		jswStyle jswLabelStyles = panelstyles.makeStyle("jswLabel");

		jswStyle jswButtonStyles = panelstyles.makeStyle("jswButton");
		jswButtonStyles.putAttribute("fontsize", "10");

		jswStyle jswToggleButtonStyles = panelstyles.makeStyle("jswToggleButton");
		jswToggleButtonStyles.putAttribute("foregroundColor", "Red");

		jswStyle jswTextBoxStyles = panelstyles.makeStyle("jswTextBox");

		jswStyle jswTextFieldStyles = panelstyles.makeStyle("jswTextField");

		jswStyle jswDropDownBoxStyles = panelstyles.makeStyle("jswDropDownBox");

		jswStyle jswhpStyles = panelstyles.makeStyle("jswContainer");
		jswhpStyles.putAttribute("backgroundColor", "#C0C0C0");

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

		//tablestyles = jswStyles.getDefaultStyles();
		tablestyles.name = "defaulttable";
		jswStyle tablestyle = tablestyles.makeStyle("table");
		tablestyle.putAttribute("backgroundColor", "#bbffac");
		tablestyle.putAttribute("borderwidth", "3");
		tablestyle.putAttribute("borderColor", "green");
		jswStyle cellstyle = tablestyles.makeStyle("cell");
		cellstyle.putAttribute("fontSize", "16");
		cellstyle.putAttribute("fontStyle", Font.PLAIN);
		cellstyle.putAttribute("borderColor", "red");
		cellstyle.putAttribute("borderWidth", 1);
		jswStyle rowstyle = tablestyles.makeStyle("row");
		rowstyle.putAttribute("height", "10");
		jswStyle row0style = tablestyles.makeStyle("row_0");
		row0style.putAttribute("fontStyle", Font.BOLD);
		row0style.putAttribute("foregroundColor", "green");
		jswStyle colstyle = tablestyles.makeStyle("col");
		colstyle.setHorizontalAlign("RIGHT");
		jswStyle col0style = tablestyles.makeStyle("col_0");
		col0style.putAttribute("fontStyle", Font.BOLD);
		col0style.putAttribute("backgroundColor", "blue");
		col0style.setHorizontalAlign("LEFT");
		

		return tablestyles;
	}

=======


			jswStyles panelstyles = new jswStyles();

			jswStyle jswWidgetStyles = panelstyles.makeStyle("jswWidget");
			jswWidgetStyles.putAttribute("backgroundColor","#e0dcdf");
			jswWidgetStyles.putAttribute("boxbackgroundColor","GREEN");
			jswWidgetStyles.putAttribute("foregroundColor", "Black");
			jswWidgetStyles.putAttribute("borderWidth", "0");
			jswWidgetStyles.putAttribute("fontsize", "14");
			jswWidgetStyles.putAttribute("borderColor", "blue");


			jswStyle jswLabelStyles = panelstyles.makeStyle("jswLabel");

			jswStyle jswButtonStyles = panelstyles.makeStyle("jswButton");
			jswButtonStyles.putAttribute("fontsize", "10");

			jswStyle jswToggleButtonStyles = panelstyles.makeStyle("jswToggleButton");
			jswToggleButtonStyles.putAttribute("foregroundColor", "Red");

			jswStyle jswTextBoxStyles = panelstyles.makeStyle("jswTextBox");


			jswStyle jswTextFieldStyles = panelstyles.makeStyle("jswTextField");

			jswStyle jswDropDownBoxStyles = panelstyles.makeStyle("jswDropDownBox");

			jswStyle jswhpStyles = panelstyles.makeStyle("jswContainer");
			jswhpStyles.putAttribute("backgroundColor", "#C0C0C0");

			jswStyle jswDropDownContactBoxStyles = panelstyles
					.makeStyle("jswDropDownContactBox");
			jswDropDownContactBoxStyles.putAttribute("backgroundColor", "#C0C0C0");
			jswDropDownContactBoxStyles.putAttribute("fontsize", "10");

			jswStyle jswScrollPaneStyles = panelstyles
					.makeStyle("jswScrollPaneStyles");
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
			panelstyles.name="default";
		return panelstyles;
	}

>>>>>>> master
	public jswStyle getStyle(String name)
	{
		jswStyle astyle = null;
		if (stylelist.containsKey(name))
		{
			astyle = stylelist.get(name);
		}
		return astyle;
	}

<<<<<<< HEAD
	public void copyStyle(String toname, String fromname)
=======
	public void copyStyle(String toname,String fromname)
>>>>>>> master
	{
		jswStyle astyle = null;
		if (stylelist.containsKey(fromname))
		{
			jswStyle fromstyle = stylelist.get(fromname);
			jswStyle newstyle = new jswStyle(toname);
			newstyle.copyAll(fromstyle);
			this.put(newstyle);
		}

	}

<<<<<<< HEAD
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
=======

	public jswStyle makeStyle(String name)
	{
		jswStyle newstyle = new jswStyle(name);
		stylelist.put(name, newstyle);
		return newstyle;
>>>>>>> master
	}

}
