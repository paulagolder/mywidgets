package org.lerot.mywidgets;

import java.awt.Color;
import java.util.Map;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

// the shared look for applications built with mywidgets: FlatLaf with a soft
// warm off-white page, a slightly darker shade for the bars at the top and for
// panels, flat rounded buttons, one accent colour for links and three font
// sizes. Call jswTheme.install() first thing in main().
public class jswTheme
{
	// the palette (the same as the MyContacts viewer)
	public static final String PAGE = "#F4F1EA";          // the page
	public static final String SHADE = "#E9E4DA";         // bars and panels on the page
	public static final String ACCENT = "#2F6FDB";        // links, and what can be clicked
	public static final String MUTED = "#8A8A8E";         // labels and notes
	public static final String HIGHLIGHT = "#DCE6F8";     // the selected button or row

	// background tints for the functional areas of a window: the bars at the
	// top are shaded, the content areas are the page
	public static final String NAVAREA = SHADE;           // navigation / mode buttons
	public static final String SELECTAREA = SHADE;        // choosing what to work on
	public static final String DETAILAREA = PAGE;         // main content
	public static final String SECONDARYAREA = PAGE;      // supporting content
	public static final String EDITAREA = PAGE;           // editing
	public static final String TOOLSAREA = PAGE;          // tools and admin

	public static final String BACKGROUND = PAGE;
	public static final String BORDER = "#D9D2C5";        // panel and table outlines
	public static final String CELLBORDER = "#E6E0D5";    // lines between table rows

	// font sizes
	public static final int TEXT = 13;
	public static final int HEADING = 16;
	public static final int TITLE = 20;

	public static void install()
	{
		FlatLaf.setGlobalExtraDefaults(Map.of(
				"@background", BACKGROUND,
				"@componentBackground", "#FBFAF7",
				"@accentColor", ACCENT,
				"Component.borderColor", BORDER,
				"Component.arc", "8",
				"Button.background", "#FBFAF7",
				"Button.borderColor", "#CFC7B8",
				"Button.shadowWidth", "0",
				"Button.arc", "10",
				"ToggleButton.selectedBackground", HIGHLIGHT));
		FlatLightLaf.setup();
	}

	// a button label in capitals ("EDIT ME") is shown in sentence case ("Edit me");
	// the action command keeps the label as written
	public static String buttonText(String label)
	{
		if (label == null || label.length() < 3 || !label.equals(label.toUpperCase())
				|| label.equals(label.toLowerCase())) return label;
		return label.charAt(0) + label.substring(1).toLowerCase();
	}

	// give a panel (and the children that inherit its background) an area tint
	public static void tint(jswPanel panel, String colour)
	{
		panel.setStyleAttribute("backgroundColor", colour);
		panel.setBackground(Color.decode(colour));
	}
}
