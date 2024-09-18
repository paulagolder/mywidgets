package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

//import org.lerot.mywidgets.jswTree;

public class mywidget_gui extends JFrame implements ActionListener, TreeModelListener, TreeSelectionListener
{
	private static final long serialVersionUID = 1L;
	public static mywidget_gui mframe;
	private jswTextArea textarea;

	public static void main(String[] args)
	{
		(mframe = new mywidget_gui(800, 400)).addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		mframe.setLocation(50, 50);
		mframe.setMinimumSize(new Dimension(800, 400));
		mframe.pack();
		mframe.setVisible(true);
	}

	public mywidget_gui(int w, int h)
	{
		jswContainer apanel = new jswContainer("dummy");
		jswStyles defstyles = jswStyles.getDefaultStyles();
		jswStyles mwdefstyles = defaultStyles();

		jswVerticalPanel mainpanel = new jswVerticalPanel("mainpanel", false, true);
		mainpanel.setBorder(jswStyle.makeLineBorder(Color.red, 4));
		jswHorizontalPanel panel1 = new jswHorizontalPanel("Panel1", true, true);

		// ====start of panel1===

		jswTabbedPanel tabbedpanel1 = new jswTabbedPanel("tabbed panel 1");
		jswHorizontalPanel panel1a = new jswHorizontalPanel("Panel 1A", true, true);
		//panel1a.setStyleAttribute("gap",2);
		//panel1a.setStyleAttribute("verticallayoutstyle",jswLayout.MIDDLE);
		panel1a.applyStyle();
		jswButton button1 = new jswButton(this, "button_1");
		jswTextBox textbox1 = new jswTextBox(this, "textfield_1");
		textbox1.applyStyle();
		jswDropDownBox dropdownbox1 = new jswDropDownBox(this, "dropdownbox 1");
		Vector<String> list = new Vector<>();
		list.add("LEFT");
		list.add("MIDDLE");
		list.add("RIGHT");
		dropdownbox1.setEnabled(false);
		dropdownbox1.setList(list);
		dropdownbox1.setEnabled(true);
		jswLabel label2 = new jswLabel("Label 2");
		label2.style.setFontsize(18);
		label2.applyStyle();
		panel1a.add(" ", button1);
		panel1a.add(" ", textbox1);
		panel1a.add(" ", dropdownbox1);
		panel1a.add(" FILLW ", label2);

		panel1a.repaint();

		jswHorizontalPanel panel1b = new jswHorizontalPanel("Panel 1B", false, true);
		// panel1b.setBackground(Color.lightGray);
		jswOptionset optionset = new jswOptionset(this, "option list 1", false, true, false);
		optionset.setStyleAttribute("horizontallayoutstyle",jswLayout.DISTRIBUTE);
		optionset.setStyleAttribute("verticallayoutstyle",jswLayout.MIDDLE);
		//optionset.setStyleAttribute("titlefontsize", 24);
		
		//optionset.applyStyle();
		jswOption ro = optionset.addNewOption("red", true);
		ro.applyStyle(mwdefstyles.getStyle("redoption"));
		jswOption bo = optionset.addNewOption("blue", true);
		bo.applyStyle(mwdefstyles.getStyle("blueoption"));
		jswOption go = optionset.addNewOption("green", true);
		go.applyStyle(mwdefstyles.getStyle("greenoption"));
		panel1b.add("  minheight=100 ", optionset);

		jswPushButtonset pushbuttonset = new jswPushButtonset(this, "my H button list", false, true,false);
		//pushbuttonset.setStyleAttribute("horizontallayoutstyle", jswLayout.DISTRIBUTE);
		pushbuttonset.addNewButton("red");
		pushbuttonset.addNewButton("blue");
		pushbuttonset.addNewButton("green");
		pushbuttonset.applyStyle();
		panel1b.add(" ", pushbuttonset);
		jswButton buttonl = new jswButton(this, "large button");
		buttonl.applyStyle(mwdefstyles.getStyle("largebutton"));
		jswButton buttonm = new jswButton(this, "medium button");
		buttonm.applyStyle(mwdefstyles.getStyle("mediumbutton"));
		jswButton buttons = new jswButton(this, "small");
		buttons.applyStyle(mwdefstyles.getStyle("smallbutton"));
		panel1b.add(" ", (Component) buttonl);
		panel1b.add((Component) buttonm);
		panel1b.add((Component) buttons);

		jswHorizontalPanel panel1c = new jswHorizontalPanel("Panel 1C", true, true);
		jswThumbwheel thumbwheel1 = new jswThumbwheel(this, "thumbwheel 1", 5, 10);
		thumbwheel1.getStyle().setBorderStyle(jswStyle.LINEBORDER);
		thumbwheel1.getStyle().setBorderWidth(4);
		thumbwheel1.getStyle().setBordercolor("blue");
		thumbwheel1.setFontStyle("SansSerif", Font.BOLD, 18);
		thumbwheel1.applyStyle();
		panel1c.add(" ", thumbwheel1);
		jswCheckbox checkbox1 = new jswCheckbox(this, "my checkbox");
		checkbox1.getStyle().setBorderStyle(jswStyle.LINEBORDER);
		checkbox1.getStyle().setBorderWidth(4);
		checkbox1.getStyle().setBordercolor("green");
		checkbox1.getStyle().setFontsize(24);
		checkbox1.getStyle().setFontstyle(Font.BOLD + Font.ITALIC);
		checkbox1.applyStyle();
		panel1c.add(" ", checkbox1);
		jswToggleButton togglebutton1 = new jswToggleButton(this, "my toggle", "togglebutton1");
		panel1c.add(" ", (Component) togglebutton1);
		jswTextBox textfield1 = new jswTextBox(this, " ");
		textfield1.addActionListener(this);

		jswHorizontalPanel panel1d = new jswHorizontalPanel("Panel 1D", true, true);
		jswButton button1d = new jswButton(this, "mybutton");
		panel1d.add(" // ", button1d);
		jswTextBox tb1d = new jswTextBox(this, "mytextbox");
		panel1d.add(" FILLW WIDTH=600 ", tb1d);

		tabbedpanel1.addTab("TAB 1 A", panel1a);
		tabbedpanel1.addTab("TAB 1 B", panel1b);
		tabbedpanel1.addTab("TAB 1 C", panel1c);
		tabbedpanel1.addTab("TAB 1 D", panel1d);
		tabbedpanel1.setSize(10000, 600);
		panel1.add("  HEIGHT=500  ", tabbedpanel1);
		mainpanel.add(" FILLW  HEIGHT=500 ", panel1);

		// ====end of panel1===

		// ====start of panel2===
		jswHorizontalPanel panel2 = new jswHorizontalPanel("Panel 2", true, true);
		// panel2.setStyleAttribute("padding", 5);
		panel2.setStyleAttribute("gap", 10);
		panel2.setStyleAttribute("titlecolor", "green");
		panel2.setStyleAttribute("titlefontsize", 20);
		panel2.applyStyle();
		jswOptionset optionseta = new jswOptionset(this, "my option list", true, true);
		optionseta.setStyleAttribute("verticallayoutstyle", jswLayout.DISTRIBUTE);
		optionseta.setStyleAttribute("horizontallayoutstyle", jswLayout.LEFT);
		jswOption ros = optionseta.addNewOption("red", true);
		ros.applyStyle(mwdefstyles.getStyle("redoption"));
		jswOption rob = optionseta.addNewOption("blue", true);
		rob.applyStyle(mwdefstyles.getStyle("blueoption"));
		jswOption rog = optionseta.addNewOption("green", true);
		rog.applyStyle(mwdefstyles.getStyle("greenoption"));
		optionseta.applyStyle();
		panel2.add("  ", optionseta); 
		jswPushButtonset pushbuttonseta = new jswPushButtonset(this, "my V button list", true, true, true);
		pushbuttonseta.setStyleAttribute("verticallayoutstyle", jswLayout.DISTRIBUTE);
		pushbuttonseta.setStyleAttribute("horizontallayoutstyle", jswLayout.MIDDLE);
		pushbuttonseta.applyStyle();
		jswPushButton pbr = pushbuttonseta.addNewButton("red");
		pbr.applyStyle(mwdefstyles.getStyle("redbutton"));
		jswPushButton pbb = pushbuttonseta.addNewButton("blue");
		pbb.applyStyle(mwdefstyles.getStyle("bluebutton"));
		jswPushButton pbg = pushbuttonseta.addNewButton("green");
		pbg.applyStyle(mwdefstyles.getStyle("greenbutton"));
		panel2.add(" FILLH ", pushbuttonseta);

		this.textarea = new jswTextArea("some input", true);
		panel2.add(" WIDTH=300 FILLH ", (Component) this.textarea);
		jswTable table1 = makeTableExample();
		panel2.add(" width=400 FILLH  ", table1);
		mainpanel.add(" HEIGHT=300 ", panel2);
		// ====end of panel2==

		// ====start of panel3==
		jswHorizontalPanel panel3 = new jswHorizontalPanel("Panel 3", true, true);
		panel3.setBackground(Color.green);

		jswPanel treepanel = makeTreeExample();
		jswTextArea textarea3 = new jswTextArea("textarea3");

		String[] hhelp = jswHorizontalLayout.help();

		textarea3.setText(hhelp);

		String[] vhelp = jswVerticalLayout.help();

		textarea3.setText(vhelp);

		jswTextArea textarea2 = new jswTextArea("some more input");

		panel3.add(" FILLW  ", textarea2);
		panel3.add(" FILLW ", treepanel);
		panel3.add(" FILLW ", textarea3);

		jswHorizontalPanel panel4 = new jswHorizontalPanel("Panel 4", true, true);
		panel4.setBackground(Color.yellow);
		jswContainer jswContainer1 = new jswContainer("subpanel 1");
		jswContainer1.getStyle().setBackgroundcolor("red");
		jswContainer1.setStyleAttribute("myheight", 150);
		jswContainer1.applyStyle();
		jswContainer jswContainer2 = new jswContainer("sub panel 2");
		jswContainer2.getStyle().setBackgroundcolor("white");
		jswContainer2.applyStyle();
		jswContainer jswContainer3 = new jswContainer("sub panel 3");
		jswContainer3.getStyle().setBackgroundcolor("blue");
		jswContainer3.applyStyle();

		panel4.add("  FILLW  ", jswContainer1);
		panel4.add("  FILLW ", jswContainer2);
		panel4.add("  FILLW ", jswContainer3);

		mainpanel.add(" FILLH MAXHEIGHT=200 ", panel4);
		mainpanel.add("  FILLH  ", panel3);

		add(mainpanel);

		pack();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		String sourceclass = e.getSource().getClass().getSimpleName();
		if (sourceclass.startsWith("jsw"))
		{
			jswActionPerformed(e);
			return;
		} else
		{

			System.out.println("handling :");
			if (this.textarea != null)
			{
				this.textarea.addText("handling :" + e.getActionCommand() + "\n");
			}
		}
	}

	public void jswActionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		String sourceclass = e.getSource().getClass().getSimpleName();
		String sourceobject = ((jswPanel) e.getSource()).getPanelname();
		System.out.println("jswhandling XX:" + sourceobject + ":" + sourceclass);
		System.out.println("jswhandling XX:" + cmd);

		// ObjectReader reader = new ObjectMapper().readerFor(Map.class);

		// Map<String, String> map = reader.readValue("{\"foo\":\"val\"}");

		System.out.println("jswhandling :" + sourceobject + ":" + cmd);
		if (this.textarea != null)
		{
			this.textarea.addText("jswhandling :" + sourceobject + ":" + cmd + "\n");
		}

	}

	public jswPanel makeTreeExample()
	{
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Duke Charles *the Bold* Of Burgundy 1433-1477");
		DefaultMutableTreeNode pm = new DefaultMutableTreeNode("Duke Philipp III Of Burgundy 1396-1467");
		DefaultMutableTreeNode pf = new DefaultMutableTreeNode("Princess Isabel Of Portugal 1396-");
		top.add(pm);
		top.add(pf);

		jswTree treepanel = new jswTree(this, "my tree", top);
        
		treepanel.applyStyle();


		return (jswPanel) treepanel;
	}

	public jswTable makeTableExample()
	{
		jswStyles defstyles = jswStyles.getDefaultTableStyles();
		jswTable table1 = new jswTable("table 1", defstyles);
		for (int k = 0; k < 10; k++)
		{
			table1.addCell(" row " + k, k, 0);
		}	
		for (int j = 1; j < 5; j++)
		{
			table1.addCell(" col:" + j, 0, j);
		}
		for (int i = 1; i < 10; i++)
		{
			for (int m = 1; m < 5; m++)
			{
				jswCell acell = table1.addCell(" cell:" + i + ":" + m, i, m);
				acell.applyStyle();
				acell.applyContentStyle();
			}
		}
		table1.applyAllStyles();
		return table1;
	}

	public static jswStyles defaultStyles()
	{

		jswStyles defstyles = new jswStyles("default");

		jswStyle lstyle = defstyles.makeStyle("largebutton");
		lstyle.setFontsize(jswStyles.font_large);
		lstyle.setFontstyle(Font.BOLD);
		lstyle.setForegroundcolor("BLUE");
		lstyle.setBackgroundcolor("PINK");
		lstyle.setMyHeight(40);
		lstyle.setMyWidth(80);

		jswStyle mstyle = defstyles.makeStyle("mediumbutton");
		mstyle.setFontsize(jswStyles.font_medium);
		mstyle.setFontstyle(Font.ITALIC);
		mstyle.setBackgroundcolor("yellow");
		mstyle.setMyHeight(30);
		mstyle.setMyWidth(60);

		jswStyle sstyle = defstyles.makeStyle("smallbutton");
		sstyle.setFontsize(jswStyles.font_small);
		sstyle.setBackgroundcolor("green");
		sstyle.setMyHeight(20);
		sstyle.setMyWidth(40);

		jswStyle rolstyle = defstyles.makeStyle("optionset");
		rolstyle.putAttribute("backgroundColor", "#e0ffde");
		rolstyle.putAttribute("borderWidth", "0");
		rolstyle.putAttribute("borderColor", "black");

		jswStyle rostyle = defstyles.makeStyle("redoption");
		rostyle.putAttribute("foregroundColor", "red");
		rostyle.putAttribute("backgroundColor", "transparent");

		jswStyle bostyle = defstyles.makeStyle("blueoption");
		bostyle.putAttribute("foregroundColor", "blue");
		bostyle.putAttribute("backgroundColor", "transparent");

		jswStyle gostyle = defstyles.makeStyle("greenoption");
		gostyle.putAttribute("foregroundColor", "green");
		gostyle.putAttribute("backgroundColor", "transparent");

		jswStyle rbstyle = defstyles.makeStyle("redbutton");
		rbstyle.putAttribute("backgroundColor", "red");

		jswStyle bbstyle = defstyles.makeStyle("bluebutton");
		bbstyle.putAttribute("backgroundColor", "blue");

		jswStyle gbstyle = defstyles.makeStyle("greenbutton");
		gbstyle.putAttribute("backgroundColor", "green");

		return defstyles;
	}

	public static jswStyles tablestyles()
	{
		jswStyles tablestyles = new jswStyles();
		tablestyles = jswStyles.getDefaultTableStyles();
		tablestyles.name = "defaulttable";
		jswStyle colstyle = tablestyles.makeStyle("col");
		colstyle.putAttribute("horizontalAlignment", "RIGHT");
		colstyle.putAttribute("width", 50);
		colstyle.putAttribute("foregroundcolor", "yellow");
		
		jswStyle col23style = tablestyles.makeStyle("cell_3_4");
		 col23style.putAttribute("foregroundcolor", "yellow");
		  col23style.putAttribute("borderWidth", "1");
		  col23style.putAttribute("borderColor", "blue");
		/*
		 * jswStyle colstyle2 = tablestyles.makeStyle("col_2");
		 * colstyle2.putAttribute("horizontalAlignment", "LEFT");
		 * colstyle2.putAttribute("width", 70);
		 * colstyle2.putAttribute("backgroundcolor", "pink"); jswStyle colstyle3 =
		 * tablestyles.makeStyle("col_3"); colstyle3.putAttribute("horizontalAlignment",
		 * "RIGHT"); jswStyle rowstyle = tablestyles.makeStyle("row");
		 * rowstyle.putAttribute("height", 200 ); jswStyle row0style =
		 * tablestyles.makeStyle("row_0"); row0style.putAttribute("fontstyle",
		 * Font.ITALIC ); row0style.putAttribute("backgroundcolor", "yellow"); jswStyle
		 * cellstyle = tablestyles.makeStyle("cell");
		 * cellstyle.putAttribute("foregroundcolor", "red");
		 * cellstyle.putAttribute("fontsize", 16);
		 * 
		 * jswStyle col23style = tablestyles.makeStyle("cell_3_4");
		 * col23style.putAttribute("foregroundcolor", "yellow");
		 * col23style.putAttribute("borderWidth", "1");
		 * col23style.putAttribute("borderColor", "blue");
		 * 
		 * /* jswStyle col2style = tablestyles.makeStyle("col_2");
		 * col2style.putAttribute("horizontalAlignment", "RIGHT");
		 */

		// col2style.putAttribute("width", 40);
		// col2style.putAttribute("foregroundcolor", "red");
		// jswStyle tablestyle = tablestyles.makeStyle("table");
		// tablestyles.getStyle("table").putAttribute("backgroundColor", "red");
		/*
		 * jswStyle rowstyle = tablestyles.makeStyle("row");
		 * rowstyle.putAttribute("height", "10"); jswStyle col0style =
		 * tablestyles.makeStyle("col_0"); col0style.putAttribute("fontStyle",
		 * Font.BOLD); col0style.setHorizontalAlign("RIGHT");
		 * col0style.putAttribute("minwidth", "true"); jswStyle col1style =
		 * tablestyles.makeStyle("col_1"); col1style.putAttribute("fontStyle",
		 * Font.BOLD); col1style.setHorizontalAlign("RIGHT"); jswStyle col2style =
		 * tablestyles.makeStyle("col_2"); col2style.putAttribute("horizontalAlignment",
		 * "RIGHT"); col2style.putAttribute("minwidth", "true");
		 * col2style.putAttribute("background", "red");
		 */

		return tablestyles;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void treeNodesChanged(TreeModelEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void treeNodesInserted(TreeModelEvent e)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void treeStructureChanged(TreeModelEvent e)
	{
		// TODO Auto-generated method stub

	}

}
