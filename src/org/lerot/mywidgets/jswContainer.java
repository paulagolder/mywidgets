package org.lerot.mywidgets;

import java.awt.Dimension;

public class jswContainer extends jswPanel
{

	private static final long serialVersionUID = 1L;
//	int cheight = 0, cwidth = 0;
	int bl=30;
	int bh=30;

	public jswContainer(String name)
	{
		super("C:" + name);
	}

	public void applyStyle(jswStyle astyle)
	{
	
				int wd =style.getIntegerStyle("mywidth",bl);
				if(wd > bl) bl= wd;
				int ht =style.getIntegerStyle( "myheight",bh);
				if(ht > bh ) bh=ht;
				Dimension d = new Dimension(bl, bh);
				setBackground(style.getColor("backgroundcolor",jswStyle.TRANSPARENT));
				setForeground(style.getForegroundcolor());
				setBorder(style.getBorder());
				setPreferredSize(d);
				setMaximumSize(d);
				setMinimumSize(d);
		
	}
	

	
}