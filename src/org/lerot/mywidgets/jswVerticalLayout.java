package org.lerot.mywidgets;

import java.awt.Insets;
import java.util.List;

/*
 * Stacks a panel's children top to bottom, worked out by MigLayout (see
 * jswMigLayout). The rules are those of the old layout:
 *  - each child is its minimum height; FILLH children share the spare
 *    height in proportion to their minimum heights
 *  - FILLW children are the full width; the others are their minimum width,
 *    placed by the panel's horizontallayoutstyle (LEFT, MIDDLE or RIGHT)
 *  - the panel's "gap" style separates the children, with half a gap above
 *    the first; verticallayoutstyle DISTRIBUTE spreads them out instead
 *  - a single child fills the whole panel
 * MINWIDTH, MINHEIGHT, MAXWIDTH and MAXHEIGHT on a child are respected.
 */
public class jswVerticalLayout extends jswMigLayout
{
	public jswVerticalLayout()
	{
		super(new jswLegacyVerticalLayout());
	}

	@Override
	protected String[] constrain(jswStyle parentstyle, List<Item> items, Insets in)
	{
		String common = "hidemode 3, novisualpadding, ";
		if (items.size() == 1)
		{
			Item only = items.get(0);
			// a single child is made exactly the panel's size, even if smaller than its minimum
			only.constraints = "w 0:" + px(only.minwidth) + NOMAX
					+ ", h 0:" + px(only.minheight) + NOMAX + ", grow" + limits(only);
			return new String[] { common + insets(in.top, in.left, in.bottom, in.right), "[grow, fill]",
					"[grow, fill]" };
		}
		int gap = parentstyle.getIntegerStyle("gap", 0);
		boolean distribute = parentstyle.getIntegerStyle("verticallayoutstyle", 0) == jswLayout.DISTRIBUTE;
		int hstyle = parentstyle.getIntegerStyle("horizontallayoutstyle", 0);
		String align = hstyle == jswLayout.RIGHT ? "right" : hstyle == jswLayout.MIDDLE ? "center" : "left";

		StringBuilder rows = new StringBuilder();
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			if (i > 0) rows.append(distribute ? "push" : px(gap));
			// spare height goes to FILLH rows in proportion to their minimum height
			rows.append(item.fillh ? "[grow " + Math.max(1, item.minheight) + ", fill]" : "[]");
			// FILLW children take the panel's width, even if narrower than their minimum
			String width = item.fillw ? "w 0:" + px(item.minwidth) + NOMAX + ", growx"
					: hstyle == jswLayout.RIGHT || hstyle == jswLayout.MIDDLE
							? "w " + px(item.minwidth) + ":" + px(Math.max(item.minwidth, item.prefwidth))
							: "w " + px(item.minwidth) + "!";
			String height = item.fillh ? "h " + px(item.minheight) + ":" + px(item.minheight) + NOMAX
					: "h " + px(item.minheight) + "!";
			item.constraints = width + ", " + height + limits(item);
		}
		int top = in.top + gap / 2, bottom = in.bottom + gap - gap / 2;
		return new String[] { common + "flowy, " + insets(top, in.left, bottom, in.right),
				"[grow, " + align + "]", rows.toString() };
	}

	public static String[] getHelp()
	{
		return jswLegacyVerticalLayout.getHelp();
	}
}
