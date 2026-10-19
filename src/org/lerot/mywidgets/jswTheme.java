package org.lerot.mywidgets;

import java.awt.Color;
import java.util.Map;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

// the shared look for applications built with mywidgets: FlatLaf with a warm
// neutral palette, slightly raised buttons, tints marking the functional areas
// of a window and three font sizes. Call jswTheme.install() first thing in main().
public class jswTheme
{
	// background tints for the functional areas of a window
	public static final String NAVAREA = "#D3D8DE";       // navigation / mode buttons
	public static final String SELECTAREA = "#E8E1D2";    // choosing what to work on
	public static final String DETAILAREA = "#EEECE7";    // main content
	public static final String SECONDARYAREA = "#DFE6DE"; // supporting content
	public static final String EDITAREA = "#EBDFDB";      // editing
	public static final String TOOLSAREA = "#E3DFE9";     // tools and admin

	public static final String BACKGROUND = "#E4E2DD";
	public static final String BORDER = "#B4B0A8";        // panel and table outlines
	public static final String CELLBORDER = "#CFCBC3";    // lines between table cells

	// font sizes
	public static final int TEXT = 13;
	public static final int HEADING = 16;
	public static final int TITLE = 20;

	public static void install()
	{
		FlatLaf.setGlobalExtraDefaults(Map.of(
				"@background", BACKGROUND,
				"@componentBackground", "#F7F6F3",
				"Component.borderColor", BORDER,
				"Button.startBackground", "#FBFAF8",
				"Button.endBackground", "#E2DFD9",
				"Button.borderColor", "#A39F97",
				"Button.shadowWidth", "2",
				"Button.shadowColor", "#00000030",
				"Button.arc", "8",
				"ToggleButton.selectedBackground", "#BFC9D4"));
		FlatLightLaf.setup();
	}

	// give a panel (and the children that inherit its background) an area tint
	public static void tint(jswPanel panel, String colour)
	{
		panel.setStyleAttribute("backgroundColor", colour);
		panel.setBackground(Color.decode(colour));
	}
}
