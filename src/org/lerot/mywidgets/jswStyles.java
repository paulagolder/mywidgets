package org.lerot.mywidgets;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class jswStyles
{
    public String name;
	public Map<String, jswStyle> stylelist = new HashMap<>();

	public jswStyles()
	{

	}

	public static jswStyles clone(String name, jswStyles source)
	{
		jswStyles cstyles = new jswStyles(name);
		for( Entry<String, jswStyle> entry : source.stylelist.entrySet())
		{
			jswStyle nstyle = new jswStyle();
			nstyle.copyAll(entry.getValue());
			cstyles.put(nstyle);

		}
		return cstyles;
	}

	public  void copyStyles( jswStyles source)
	{

		for( Entry<String, jswStyle> entry : source.stylelist.entrySet())
		{
			jswStyle nstyle = new jswStyle();
			nstyle.copyAll(entry.getValue());
			this.put(nstyle);

		}

	}

	private void put(jswStyle nstyle) {
		stylelist.put(nstyle.getStyleName(),nstyle);

	}

	public jswStyles(String aname)
	{
        name= aname;
	}

	public static jswStyles getDefaultStyles()
	{


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

	public jswStyle getStyle(String name)
	{
		jswStyle astyle = null;
		if (stylelist.containsKey(name))
		{
			astyle = stylelist.get(name);
		}
		return astyle;
	}

	public void copyStyle(String toname,String fromname)
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


	public jswStyle makeStyle(String name)
	{
		jswStyle newstyle = new jswStyle(name);
		stylelist.put(name, newstyle);
		return newstyle;
	}

}
