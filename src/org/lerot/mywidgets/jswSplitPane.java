package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.border.Border;

import static java.lang.Boolean.TRUE;

public class jswSplitPane extends jswContainer
{

	private JSplitPane splitpane;
	private boolean isvertical;
	private jswPanel panel2;
	private jswPanel panel1;

	public jswSplitPane(ActionListener parentListener, String name, boolean isvertical, jswPanel panela,
			jswPanel panelb)
	{
		super(name);
		setPanelname(name);
		panel1 = panela;
		panel2 = panelb;
		setAlignmentX(Component.CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		actionlistener = parentListener;
		this.isvertical = isvertical;

		if (!isvertical)
		{
			setAlignmentX(Component.LEFT_ALIGNMENT);
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panela, panelb);
			splitpane.setAlignmentY(Component.CENTER_ALIGNMENT);
			panela.setMinimumSize(new Dimension(100, 100));
			panelb.setMinimumSize(new Dimension(100, 100));
		} else
		{
			setAlignmentX(Component.LEFT_ALIGNMENT);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			splitpane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panela, panelb);
			splitpane.setAlignmentX(Component.CENTER_ALIGNMENT);

		}

		splitpane.setOneTouchExpandable(true);
		splitpane.setDividerLocation(-1);
		splitpane.setEnabled(true);
		splitpane.setContinuousLayout(TRUE);
		double weight = .5D;
		splitpane.setResizeWeight(weight);
		setName(name);
		add(splitpane);
		style.setBorderStyle(jswStyle.LINEBORDER);
		style.setBorderWidth(1);
		style.setBordercolor("RED");
		applyStyle();
	}

	public void applyStyle(jswStyle astyle)
	{
		Font sfont = astyle.getFont();
		this.setFont(sfont);
		this.setPanelBorder(astyle);
		Border aborder = getBorder();
		if (aborder != null)
			padding = aborder.getBorderInsets(this);
		this.setForeground(astyle.getColor("foregroundColor", Color.BLACK));
		this.setBackground(astyle.getColor("backgroundColor", Color.green));
		Dimension pd = new Dimension(900,800);
		//splitpane.setMinimumSize(pd);
		panel1.setPreferredSize(pd);
		panel2.setPreferredSize(pd);
	}
	
	
	
	
		  

}
