package org.lerot.mywidgets;

import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/*
 * Base of the vertical and horizontal jsw layouts, which MigLayout now works
 * out. Panels are still built with the jsw tags (" FILLW RIGHT ", "WIDTH=200")
 * and styles; before each layout these are translated into MigLayout
 * constraints by the subclass, so changing a style later still takes effect.
 *
 * Start a program with -Djsw.layout=legacy to use the old layout code instead.
 */
public abstract class jswMigLayout implements LayoutManager2
{
	public static final boolean LEGACY = "legacy".equalsIgnoreCase(System.getProperty("jsw.layout"));

	private final MigLayout mig = new MigLayout();
	// the old layout: places the children when LEGACY is set, and always
	// reports the panel's minimum and preferred size, so panels ask their
	// parents for the same space as before
	private final jswLayout sizing;
	private final jswLayout legacy;
	// what was last given to MigLayout, so it only re-reads what changed
	private final Map<Component, String> given = new WeakHashMap<>();
	private String lastlayout, lastcolumns, lastrows;

	protected jswMigLayout(jswLayout legacy)
	{
		this.sizing = legacy;
		this.legacy = LEGACY ? legacy : null;
	}

	// a visible child and the jsw settings that affect its place
	protected static class Item
	{
		Component comp;
		int minwidth, minheight, prefwidth;
		Integer maxwidth, maxheight;
		boolean fillw, fillh;
		Integer align; // the child's LEFT / MIDDLE / RIGHT tag, if any
		String constraints = "";
	}

	// the MigLayout layout, column and row constraints for these items;
	// the subclass also sets each item's constraints
	protected abstract String[] constrain(jswStyle parentstyle, List<Item> items, Insets insets);

	private void update(Container parent)
	{
		List<Item> items = new ArrayList<>();
		for (Component comp : parent.getComponents())
		{
			if (!given.containsKey(comp))
			{
				mig.addLayoutComponent(comp, "");
				given.put(comp, "");
			}
			if (comp.isVisible()) items.add(item(comp));
		}
		String[] lcr = constrain(styleOf(parent), items, insets(parent));
		if (!lcr[0].equals(lastlayout))
			mig.setLayoutConstraints(lastlayout = lcr[0]);
		if (!lcr[1].equals(lastcolumns))
			mig.setColumnConstraints(lastcolumns = lcr[1]);
		if (!lcr[2].equals(lastrows))
			mig.setRowConstraints(lastrows = lcr[2]);
		for (Item item : items)
			if (!item.constraints.equals(given.get(item.comp)))
			{
				mig.setComponentConstraints(item.comp, item.constraints);
				given.put(item.comp, item.constraints);
			}
	}

	private static Item item(Component comp)
	{
		Item item = new Item();
		item.comp = comp;
		jswStyle s = comp instanceof jswPanel p && p.getStyle() != null ? p.getStyle() : new jswStyle();
		Dimension min = comp.getMinimumSize();
		item.minwidth = s.hasAttribute("MINWIDTH") ? s.getInteger("MINWIDTH") : min.width;
		item.minheight = s.hasAttribute("MINHEIGHT") ? s.getInteger("MINHEIGHT") : min.height;
		item.prefwidth = s.hasAttribute("WIDTH") ? s.getInteger("WIDTH") : comp.getPreferredSize().width;
		item.maxwidth = positive(s, "MAXWIDTH");
		item.maxheight = positive(s, "MAXHEIGHT");
		item.fillw = positive(s, "FILLW") != null;
		item.fillh = positive(s, "FILLH") != null;
		item.align = s.getInteger("horizontalalignstyle");
		return item;
	}

	private static Integer positive(jswStyle s, String name)
	{
		if (!s.hasAttribute(name)) return null;
		Integer v = s.getInteger(name);
		return v != null && v > 0 ? v : null;
	}

	// the space between the panel's edge and its children: the jsw padding,
	// which already includes the border, so less the border MigLayout adds itself
	private static Insets insets(Container parent)
	{
		Insets border = parent.getInsets();
		Insets pad = parent instanceof jswPanel p && p.getPadding() != null ? p.getPadding() : border;
		return new Insets(pad.top - border.top, pad.left - border.left,
				pad.bottom - border.bottom, pad.right - border.right);
	}

	protected static String px(int v)
	{
		return v + "px";
	}

	// as a size's maximum: the old layouts ignored widgets' own maximum
	// sizes, so stretching (FILL, full height) is not limited by them
	protected static final String NOMAX = ":" + "32000px";

	protected static String insets(int top, int left, int bottom, int right)
	{
		return "insets " + px(top) + " " + px(left) + " " + px(bottom) + " " + px(right);
	}

	// the size limits common to both directions
	protected static String limits(Item item)
	{
		String s = "";
		if (item.maxwidth != null) s += ", wmax " + px(item.maxwidth);
		if (item.maxheight != null) s += ", hmax " + px(item.maxheight);
		return s;
	}

	// ---- LayoutManager2, passed to MigLayout (or the old layout) ----

	@Override
	public void addLayoutComponent(Component comp, Object constraints)
	{
		if (legacy != null)
		{
			legacy.addLayoutComponent(constraints instanceof String tag ? tag : "", comp);
			return;
		}
		// jsw tags become style settings on the child, as before
		if (constraints instanceof String tag && comp instanceof jswPanel p)
			jswLayout.applyTags(tag, p);
		mig.addLayoutComponent(comp, "");
		given.put(comp, "");
	}

	@Override
	public void addLayoutComponent(String tag, Component comp)
	{
		addLayoutComponent(comp, tag);
	}

	@Override
	public void removeLayoutComponent(Component comp)
	{
		if (legacy != null)
		{
			legacy.removeLayoutComponent(comp);
			return;
		}
		mig.removeLayoutComponent(comp);
		given.remove(comp);
	}

	@Override
	public void layoutContainer(Container parent)
	{
		if (legacy != null)
		{
			legacy.layoutContainer(parent);
			return;
		}
		update(parent);
		mig.layoutContainer(parent);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		if (legacy == null) prepareSizing(sizing, styleOf(parent));
		return sizing.minimumLayoutSize(parent);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		if (legacy == null) prepareSizing(sizing, styleOf(parent));
		return sizing.preferredLayoutSize(parent);
	}

	// the old layouts keep state that their layout pass sets and their size
	// reports then use; when only sizing with them, set it the same way
	protected void prepareSizing(jswLayout sizing, jswStyle parentstyle)
	{
	}

	private static jswStyle styleOf(Container parent)
	{
		return parent instanceof jswPanel p && p.getStyle() != null ? p.getStyle() : new jswStyle();
	}

	@Override
	public Dimension maximumLayoutSize(Container parent)
	{
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	@Override
	public float getLayoutAlignmentX(Container parent)
	{
		return 0.5f;
	}

	@Override
	public float getLayoutAlignmentY(Container parent)
	{
		return 0.5f;
	}

	@Override
	public void invalidateLayout(Container parent)
	{
		if (legacy == null) mig.invalidateLayout(parent);
	}
}
