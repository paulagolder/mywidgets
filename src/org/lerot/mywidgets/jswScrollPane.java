package org.lerot.mywidgets;

import javax.swing.*;
import java.awt.*;

public class jswScrollPane extends jswPanel
{

	private static final long serialVersionUID = 1L;
	JScrollPane window;
    private final jswPanel target;
	int xshift = 0;
	int yshift = 0;

	public jswScrollPane(jswPanel atarget, int xshift, int yshift)
	{
		super("scrollpane");
		target = atarget;
	//	jswHorizontalLayout arlayout = new jswHorizontalLayout();
	//	this.setLayout(arlayout);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		window = new JScrollPane(target);
		add( window);
		applyStyle();
		window.setVisible(true);
	}

	public jswScrollPane(jswPanel atarget)
	{
		super("scrollpane");
		target = atarget;
		jswHorizontalLayout arlayout = new jswHorizontalLayout();
		this.setLayout(arlayout);
		window = new JScrollPane(target);
		add(" FILLW ", window);
	}

	public void setMyBounds(int x, int y, int w, int h)
	{
		window.setBounds(x, y, w, h);
		this.setBounds(x, y, w, h);
		// //System.out.format(" setting bounds %d %d %d %d %n ", x, y, w, h);
	}

	public void setHorizontalScrollBarPolicy(int policy)
	{
		window.setHorizontalScrollBarPolicy(policy);
	}

	public void setVerticalScrollBarPolicy(int policy)
	{
		window.setVerticalScrollBarPolicy(policy);
	}

}
