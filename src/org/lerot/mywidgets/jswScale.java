package org.lerot.mywidgets;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Rectangle;

import com.formdev.flatlaf.util.UIScale;

// Sizes in mywidgets (gaps, padding, WIDTH=, font sizes) are written for a
// screen at 100%. On a screen set to a larger text scale (125%, 150%) FlatLaf
// makes its fonts bigger; these turn our sizes into the matching pixels.
// Where Java itself scales the whole window (Windows, Mac, GDK_SCALE=2 on
// Linux) FlatLaf leaves the factor at 1, so nothing is scaled twice.
//
// Try it with -Dflatlaf.uiScale=1.5
public final class jswScale
{
	private jswScale()
	{
	}

	public static float factor()
	{
		return UIScale.getUserScaleFactor();
	}

	public static int px(int v)
	{
		return UIScale.scale(v);
	}

	public static Integer px(Integer v)
	{
		return v == null ? null : px(v.intValue());
	}

	public static Insets px(int top, int left, int bottom, int right)
	{
		return new Insets(px(top), px(left), px(bottom), px(right));
	}

	public static Dimension px(int width, int height)
	{
		return new Dimension(px(width), px(height));
	}

	// a window size at 100%, scaled but kept within the screen's usable area
	public static Dimension windowSize(int width, int height)
	{
		Dimension d = px(width, height);
		try
		{
			Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			d.width = Math.min(d.width, screen.width);
			d.height = Math.min(d.height, screen.height);
		} catch (HeadlessException e)
		{
			// no screen: keep the scaled size
		}
		return d;
	}

	// a size already in screen pixels (a laf font size) back to 100%
	public static int unscale(int v)
	{
		return UIScale.unscale(v);
	}
}
