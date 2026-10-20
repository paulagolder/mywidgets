package org.lerot.mywidgets;

import java.awt.Insets;
import java.util.List;

/*
 * Lines a panel's children up left to right, worked out by MigLayout (see
 * jswMigLayout). The rules are those of the old layout:
 *  - each child is its minimum width; FILLW children share the spare width
 *    in proportion to their minimum widths
 *  - children run as a left group, then a MIDDLE group, then a RIGHT group,
 *    by each child's LEFT / MIDDLE / RIGHT tag
 *  - children are the full height, unless the panel's verticallayoutstyle
 *    is MIDDLE or BOTTOM (then their minimum height, placed accordingly);
 *    either way they are squeezed to fit a panel that is too short
 *  - the panel's "gap" style separates them (default 1); horizontallayoutstyle
 *    DISTRIBUTE spreads them out instead
 *  - a single child is placed by its own tag, else the panel's
 *    horizontallayoutstyle
 * MINWIDTH, MINHEIGHT, MAXWIDTH and MAXHEIGHT on a child are respected.
 */
public class jswHorizontalLayout extends jswMigLayout
{
	public jswHorizontalLayout()
	{
		super(new jswLegacyHorizontalLayout());
	}

	@Override
	protected String[] constrain(jswStyle parentstyle, List<Item> items, Insets in)
	{
		String layout = "hidemode 3, novisualpadding, " + insets(in.top, in.left, in.bottom, in.right);
		int gap = parentstyle.getIntegerStyle("gap", 1);
		int hstyle = parentstyle.getIntegerStyle("horizontallayoutstyle", 0);
		int vstyle = parentstyle.getIntegerStyle("verticallayoutstyle", 0);
		// the vertical constants share values with the horizontal ones
		boolean fullheight = vstyle != jswLayout.MIDDLE && vstyle != jswLayout.BOTTOM;
		String rows = fullheight ? "[grow, fill]" : vstyle == jswLayout.MIDDLE ? "[grow, center]" : "[grow, bottom]";

		if (items.isEmpty()) return new String[] { layout, "[]", rows };
		if (items.size() == 1)
		{
			Item item = items.get(0);
			int align = item.align != null && item.align >= 0 ? item.align : hstyle;
			String column = item.fillw ? "[grow, fill]"
					: align == jswLayout.RIGHT ? "[grow, right]"
							: align == jswLayout.MIDDLE ? "[grow, center]" : "[grow, left]";
			// a single FILLW child is made exactly the panel's width
			item.constraints = (item.fillw ? "w 0:" + px(item.minwidth) + NOMAX + ", growx" : width(item))
					+ height(item, fullheight) + limits(item);
			return new String[] { layout, column, rows };
		}

		boolean distribute = hstyle == jswLayout.DISTRIBUTE;
		// which group each child is in; the groups only move left -> middle -> right
		int[] groups = new int[items.size()];
		int group = jswLayout.LEFT;
		int[] groupwidth = new int[4];
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			int align = item.align != null ? item.align : jswLayout.LEFT;
			if (align == jswLayout.RIGHT) group = jswLayout.RIGHT;
			else if (align == jswLayout.MIDDLE && group == jswLayout.LEFT) group = jswLayout.MIDDLE;
			if (groupwidth[group] > 0) groupwidth[group] += gap;
			groupwidth[group] += item.minwidth;
			groups[i] = group;
		}
		boolean middle = groupwidth[jswLayout.MIDDLE] > 0;
		// to centre the middle group in the panel (not just between the left
		// and right groups), the gap on the narrower side is that much wider
		int balance = groupwidth[jswLayout.LEFT] - groupwidth[jswLayout.RIGHT];

		StringBuilder columns = new StringBuilder();
		for (int i = 0; i < items.size(); i++)
		{
			Item item = items.get(i);
			if (i == 0 ? groups[i] != jswLayout.LEFT : distribute || groups[i] != groups[i - 1])
			{
				// a push gap opens up the space before a middle or right group
				int extra = !middle || distribute ? 0
						: groups[i] == jswLayout.MIDDLE ? Math.max(0, -balance) : Math.max(0, balance);
				columns.append(extra > 0 ? px(extra) + ":push" : "push");
			} else if (i > 0)
				columns.append(px(gap));
			// spare width goes to FILLW columns in proportion to their minimum width
			columns.append(item.fillw ? "[grow " + Math.max(1, item.minwidth) + ", fill]" : "[]");
			item.constraints = width(item) + height(item, fullheight) + limits(item);
		}
		// a middle group with nothing to its right still sits in the middle
		if (groups[items.size() - 1] == jswLayout.MIDDLE)
			columns.append(balance > 0 ? px(balance) + ":push" : "push");
		return new String[] { layout, columns.toString(), rows };
	}

	private static String width(Item item)
	{
		return item.fillw ? "w " + px(item.minwidth) + ":" + px(item.minwidth) + NOMAX + ", growx"
				: "w " + px(item.minwidth) + "!";
	}

	// children fit the panel's height, squeezed if need be (as before)
	private static String height(Item item, boolean fullheight)
	{
		return ", h 0:" + px(item.minheight) + NOMAX;
	}

	// the old layout pass sets its gap to the panel's "gap" style (default 1)
	@Override
	protected void prepareSizing(jswLayout sizing, jswStyle parentstyle)
	{
		sizing.setGap(parentstyle.getIntegerStyle("gap", 1));
	}

	public static String[] getHelp()
	{
		return jswLegacyHorizontalLayout.getHelp();
	}
}
