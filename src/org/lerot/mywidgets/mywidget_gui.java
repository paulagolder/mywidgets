package org.lerot.mywidgets;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		// mwdefstyles.copyStyles(defstyles);
		// apanel.setStyles(mwdefstyles);
		jswVerticalPanel mainpanel = new jswVerticalPanel();
		jswHorizontalPanel panel1 = new jswHorizontalPanel("Panel1", true,true);

		// ====start of panel1===


		jswTabbedPanel tabbedpanel1 = new jswTabbedPanel("tabbed panel 1");
		jswHorizontalPanel panel1a = new jswHorizontalPanel("Panel 1 A", true,true);
		//panel1a.setBackground(Color.pink);
		jswButton button1 = new jswButton(this, "button_1");
		//button1.setBorder(jswStyle.makeLineBorder(Color.blue,2));
		//button1.applyStyle();
		jswTextField textbox1 = new jswTextField(this,"textbox_1");
		textbox1.addActionListener(this);
		textbox1.setBackground(Color.pink);
		textbox1.applyStyle();
		jswDropDownBox dropdownbox1 = new jswDropDownBox(this, "dropdownbox 1");
		Vector<String> list = new Vector<>();
		list.add("LEFT");
		list.add("MIDDLE");
		list.add("RIGHT");
		dropdownbox1.setList(list);
		jswLabel label2 = new jswLabel("Label 2");
		label2.style.setFontsize(18);
		label2.applyStyle();
		panel1a.add((Component) button1);
		panel1a.add(" WIDTH=100 ", textbox1);
		panel1a.add(" ", (Component) dropdownbox1);
		panel1a.add(" FILLW ", (Component) label2);

		panel1a.repaint();

	
		
		jswHorizontalPanel panel1b = new jswHorizontalPanel("Panel 1B", true,true);
		//panel1b.setBackground(Color.lightGray);
		jswOptionset optionset = new jswOptionset(this,"option list 1", false);
		jswOption ro = optionset.addNewOption("red", true);
		ro.doStyling(mwdefstyles.getStyle("redoption"));
		jswOption bo = optionset.addNewOption("blue", true);
		bo.doStyling(mwdefstyles.getStyle("blueoption"));
		jswOption go = optionset.addNewOption("green", true);
		go.doStyling(mwdefstyles.getStyle("greenoption"));
		panel1b.add("  ",optionset);
		jswPushButtonset pushbuttonset = new jswPushButtonset(this, "my button list", false, true);
		pushbuttonset.getStyle().putAttribute("distribute",true);
		jswPushButton rb = pushbuttonset.addNewOption("red");
		//rb.applyStyle(mwdefstyles.getStyle("redbutton"));
		jswPushButton bb = pushbuttonset.addNewOption("blue");
		//bb.applyStyle(mwdefstyles.getStyle("bluebutton"));
		jswPushButton gb = pushbuttonset.addNewOption("green");
		//gb.applyStyle(mwdefstyles.getStyle("greenbutton"));
		//panel1b.add((Component) pushbuttonset);
		panel1b.add(" WIDTH=400 ",pushbuttonset);
		jswButton buttonl = new jswButton(this, "large button");
		buttonl.applyStyle(mwdefstyles.getStyle("largebutton"));
		jswButton buttonm = new jswButton(this, "medium button");
		buttonm.applyStyle(mwdefstyles.getStyle("mediumbutton"));
		jswButton buttons = new jswButton(this, "small");
		buttons.applyStyle(mwdefstyles.getStyle("smallbutton"));
		panel1b.add(" ", (Component) buttonl);
		panel1b.add((Component) buttonm);
		panel1b.add((Component) buttons);
		
	
		jswHorizontalPanel panel1c = new jswHorizontalPanel("Panel 1C", true,true);
		jswThumbwheel thumbwheel = new jswThumbwheel(this, "thumbwheel 1", 5, 10);
		panel1c.add(" ",(Component) thumbwheel);
		jswCheckbox checkbox1 = new jswCheckbox(this, "my checkbox");
		panel1c.add(" ",(Component) checkbox1);
		jswToggleButton togglebutton1 = new jswToggleButton(this, "my toggle", "togglebutton1");
		panel1c.add(" ",(Component) togglebutton1);
		jswTextBox textfield1 = new jswTextBox(this," ");
		textfield1.addActionListener(this);	
		

		jswHorizontalPanel panel1d = new jswHorizontalPanel("Panel 1D", true,true);
		jswButton button1d = new jswButton(this, "mybandtb");
		panel1d.add(" MINIMUM ",(Component) button1d);
		jswTextBox tb1d = new jswTextBox(this, "mybandtb");
		panel1d.add(" FILLW ",(Component) tb1d);
		
		tabbedpanel1.addTab("TAB 1 A", (jswPanel) panel1a);
		tabbedpanel1.addTab("TAB 1 B", (jswPanel) panel1b);
		tabbedpanel1.addTab("TAB 1 C", (jswPanel) panel1c);
		tabbedpanel1.addTab("TAB 1 D", (jswPanel) panel1d);
		
		panel1.add( "FILLW ",tabbedpanel1);
		
	//	mainpanel.add("TAB 1 A", (jswPanel) panel1a);
		
		mainpanel.add(" FILLW  ", panel1);
	//	mainpanel.add( "FILLW ",tabbedpanel1);
	
		//mainpanel.add("TAB 1 C", (jswPanel) panel1c);	
		
	//	mainpanel.add("TAB 1 B", (jswPanel) panel1b);
		// ====end of panel1===

		// ====start of panel2===
		jswHorizontalPanel panel2 = new jswHorizontalPanel("Panel 2", true,true);
		panel2.setBackground(Color.cyan);
		jswVerticalPanel padpanel = new jswVerticalPanel("padding", true);
		panel2.add(" width=40 ", (Component) padpanel);
		jswOptionset optionseta = new jswOptionset(this,"my option list", true);
		optionseta.setPadding(20);
		optionseta.doStyling(mwdefstyles.getStyle("optionset"));
		
		jswOption ros = optionseta.addNewOption("red", true);
		ros.doStyling(mwdefstyles.getStyle("redoption"));

		jswOption rob = optionseta.addNewOption("blue", true);
		rob.doStyling(mwdefstyles.getStyle("blueoption"));
		jswOption rog = optionseta.addNewOption("green", true);
		rog.doStyling(mwdefstyles.getStyle("greenoption"));
		panel2.add(" width=80 ", (Component) optionseta);
		jswPushButtonset pushbuttonseta = new jswPushButtonset(this, "my button list", true, true);
		jswPushButton pbr = pushbuttonseta.addNewOption("red");
		pbr.applyStyle(mwdefstyles.getStyle("redbutton"));
		jswPushButton pbb = pushbuttonseta.addNewOption("blue");
		pbb.applyStyle(mwdefstyles.getStyle("bluebutton"));
		jswPushButton pbg = pushbuttonseta.addNewOption("green");
		pbg.applyStyle(mwdefstyles.getStyle("greenbutton"));

		panel2.add((Component) pushbuttonseta);
		this.textarea = new jswTextArea("some input", true);
		panel2.add(" WIDTH=300 FILLH INDENT=20 ", (Component) this.textarea);
		jswTable table1 = makeTableExample();
		panel2.add(" FILLW FILLH  ", (Component) table1);
		mainpanel.add(" HEIGHT=400 ", (Component) panel2);
		// ====end of panel2==

		// ====start of panel3==
		jswHorizontalPanel panel3 = new jswHorizontalPanel("Panel 3", true,true);
		panel3.setBackground(Color.green);
		//panel3.applyStyle();

		mainpanel.add(" FILLH MAXHEIGHT=200   ", (Component) panel3);
		jswPanel treepanel = makeTreeExample();
		jswTextArea textarea3 = new jswTextArea("textarea3");

		String[] hhelp = jswHorizontalLayout.help();

		textarea3.setText(hhelp);

		String[] vhelp = jswVerticalLayout.help();

		textarea3.setText(vhelp);

		jswTextArea textarea2 = new jswTextArea("some more input");

		panel3.add(" FILLW FILLH ", (Component) textarea2);
		// panel3.add(" FILLW FILLH ", (Component)treepanel);
		panel3.add(" FILLW FILLH ", (Component) textarea3);

		jswHorizontalPanel panel4 = new jswHorizontalPanel("Panel 4", true, false);
		panel4.setBackground(Color.yellow);
		
   
		mainpanel.add(" FILLH ", (Component) panel4);
	

		jswContainer jswContainer1 = new jswContainer("subpanel 1");
		jswContainer1.setBackground(Color.red);
		jswContainer jswContainer2 = new jswContainer("sub panel 2");
		jswContainer2.setBackground(Color.white);
		jswContainer jswContainer3 = new jswContainer("sub panel 3");
		jswContainer3.setBackground(Color.blue);

		panel4.add("  FILLW=30 ", jswContainer1);
		panel4.add("  FILLW=10 ", (Component) jswContainer2);
		panel4.add("  FILLW=30 ", (Component) jswContainer3);
		
		add((Component) mainpanel);
		
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
        if(sourceclass.startsWith("jsw")) 
        	{
        	jswActionPerformed(e);
        	return;
        	}
		if (cmd.startsWith("button 1"))
		{

			jswPopUp popup1 = new jswPopUp();
			popup1.getClass();
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
		if (sourceclass.equalsIgnoreCase("jswThumbwheel"))
		{
			if (this.textarea != null)
			{
				this.textarea.addText("jswhandling :" + ((jswThumbwheel) e.getSource()).getValue() + "\n");
			}
		} else
		{

			System.out.println("jswhandling :"+sourceobject+":"+cmd );
			if (this.textarea != null)
			{
				this.textarea.addText("jswhandling :" + sourceobject+":"+cmd + "\n");
			}
		}
	}

	public jswPanel makeTreeExample()
	{
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Duke Charles *the Bold* Of Burgundy 1433-1477");
		DefaultMutableTreeNode pm = new DefaultMutableTreeNode("Duke Philipp III Of Burgundy 1396-1467");
		DefaultMutableTreeNode pf = new DefaultMutableTreeNode("Princess Isabel Of Portugal 1396-");
		top.add(pm);
		top.add(pf);

		jswTree treepanel = new jswTree("my tree", top, 10);

		treepanel.addTreeModelListener(this);
		treepanel.addTreeSelectionListener(this);


		return (jswPanel) treepanel;
	}

	public jswTable makeTableExample()
	{
		jswTable table1 = new jswTable("table 1", tablestyles());
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
				table1.addCell(" cell:" + i + ":" + m, i, m);
			}
		}
		return table1;
	}


	public static jswStyles defaultStyles()
	{

		jswStyles defstyles = new jswStyles("default");
	/*	jswStyle defaultstyle = defstyles.makeStyle("default");
		defaultstyle.putAttribute("backgroundColor", "#C0C0C0");
		defaultstyle.putAttribute("boxbackgroundColor", "pink");
		defaultstyle.putAttribute("foregroundColor", "#6b6a6a");
		defaultstyle.putAttribute("borderWidth", "1");
		defaultstyle.setFontsize(jswStyles.font_large);
		defaultstyle.putAttribute("borderColor", "#909090");
		defaultstyle.putAttribute("cellborderWidth", "1");
		defaultstyle.putAttribute("cellborderColor", "#909090");*/
		
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
		rolstyle.putAttribute("borderWidth", "1");
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


		
		/*jswStyle tastyle = defstyles.makeStyle("jswTextArea");
		tastyle.setFontsize(jswStyles.font_small);
		tastyle.putAttribute("boxbackgroundColor", "#EFE5E5");

		tastyle.putAttribute("borderWidth", "1");
		tastyle.putAttribute("borderColor", "blue");
		tastyle.putAttribute("cellborderWidth", "1");

		jswStyle labstyle = defstyles.makeStyle("jswLabel");
		labstyle.setFontsize(jswStyles.font_small);
		labstyle.putAttribute("backgroundColor", "TRANSPARENT");
		labstyle.putAttribute("boxbackgroundColor", "TRANSPARENT");
		labstyle.putAttribute("borderWidth", "0");
		labstyle.putAttribute("cellborderWidth", "0");

		jswStyle cbstyle = defstyles.makeStyle("jswCheckbox");
		cbstyle.setFontsize(jswStyles.font_small);
		cbstyle.putAttribute("backgroundColor", "TRANSPARENT");
		cbstyle.putAttribute("boxbackgroundColor", "TRANSPARENT");

		jswStyle twstyle = defstyles.makeStyle("jswThumbwheel");
		twstyle.setFontsize(jswStyles.font_small);
		twstyle.putAttribute("backgroundColor", "TRANSPARENT");
		twstyle.putAttribute("boxbackgroundColor", "TRANSPARENT");*/
		return defstyles;
	}

	public static jswStyles tablestyles()
	{
		jswStyles tablestyles = new jswStyles();

		tablestyles = jswStyles.getDefaultTableStyles();
		tablestyles.name = "defaulttable";
		jswStyle colstyle = tablestyles.makeStyle("col");
		colstyle.putAttribute("horizontalAlignment", "RIGHT");
		colstyle.putAttribute("width", 80);
		jswStyle col23style = tablestyles.makeStyle("cell_3_4");
		col23style.putAttribute("foregroundcolor", "yellow");
		col23style.putAttribute("borderWidth", "1");
		col23style.putAttribute("borderColor", "blue");
		jswStyle col2style = tablestyles.makeStyle("col_2");
		col2style.putAttribute("horizontalAlignment", "RIGHT");

		// col2style.putAttribute("width", 40);
		col2style.putAttribute("foregroundcolor", "red");
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
