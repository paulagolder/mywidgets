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
    public static mywidget_gui mframe;
    private jswTextArea textarea;
    private jswVerticalPanel mainpanel;

    public static void main(String[] args)
    {
        (mframe = new mywidget_gui(800, 400)).addWindowListener(new WindowAdapter()
        {
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

        mainpanel = new jswVerticalPanel("mainpanel", false, true);
        mainpanel.setBorder(jswStyle.makeLineBorder(Color.red, 4));
        jswHorizontalPanel titlepanel = new jswHorizontalPanel("title", true, true);
        mainpanel.add(" FILLW  ", titlepanel);
        jswLabel maintitle = new jswLabel("test Gui");
        maintitle.setStyleAttribute("fontsize", 16);
        maintitle.setStyleAttribute("foregroundcolor", "red");
        titlepanel.add(" middle ", maintitle);
        jswDate date = new jswDate("test Gui");
        date.setStyleAttribute("borderwidth", 3);
        titlepanel.add(" rightt ", date);
        date.applyStyle();
        maintitle.applyStyle();
        maintitle.getStyle().printList();
        jswHorizontalPanel panel1 = new jswHorizontalPanel("Panel1", true, true);

        // ====start of panel1===

        jswTabbedPanel tabbedpanel1 = new jswTabbedPanel("tabbed panel 1");
        jswHorizontalPanel panel1a = new jswHorizontalPanel("Panel 1A", true, true);
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
        panel1a.add(" width=50 ", textbox1);
        panel1a.add(" ", dropdownbox1);
        panel1a.add(" FILLW ", label2);

        panel1a.repaint();

        jswHorizontalPanel panel1b = new jswHorizontalPanel("Panel 1B", false, true);

        jswOptionset optionset = new jswOptionset(this, "option list 1", false, true, false);
        optionset.setStyleAttribute("horizontallayoutstyle", jswLayout.DISTRIBUTE);
        optionset.setStyleAttribute("verticallayoutstyle", jswLayout.MIDDLE);

        jswOption ro = optionset.addNewOption("red", true);
       // ro.getStyle().setBorderWidth(3);

       // ro.applyStyle();
        ro.applyStyle(mwdefstyles.getStyle("redoption"));
        jswOption bo = optionset.addNewOption("blue", true);
        bo.applyStyle(mwdefstyles.getStyle("blueoption"));
        jswOption go = optionset.addNewOption("green", true);
        go.applyStyle(mwdefstyles.getStyle("greenoption"));
        panel1b.add("   ", optionset);

        jswPushButtonset pushbuttonset = new jswPushButtonset(this, "button list 1", false, true, false);
        pushbuttonset.addNewButton("red");
        pushbuttonset.addNewButton("blue");
        pushbuttonset.addNewButton("green");
        pushbuttonset.applyStyle();
        panel1b.add("  ", pushbuttonset);
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
        panel1d.add("  ", button1d);
        jswTextBox tb1d = new jswTextBox(this, "mytextbox");
        panel1d.add(" FILLW WIDTH=600 ", tb1d);

        tabbedpanel1.addTab("TAB 1 A", panel1a);
        tabbedpanel1.addTab("TAB 1 B", panel1b);
        tabbedpanel1.addTab("TAB 1 C", panel1c);
        tabbedpanel1.addTab("TAB 1 D", panel1d);
        tabbedpanel1.setSize(10000, 600);
        panel1.add("  HEIGHT=500  ", tabbedpanel1);
        mainpanel.add(" FILLW  ", panel1);

        // ====end of panel1===

        // ====start of panel2===
        jswHorizontalPanel panel2 = new jswHorizontalPanel("Panel 2", true, true);
        // panel2.setStyleAttribute("padding", 5);
        panel2.setStyleAttribute("gap", 10);
        panel2.setStyleAttribute("titlecolor", "green");
        panel2.setStyleAttribute("titlefontsize", 20);
        panel2.setBackground(Color.pink);
        panel2.applyStyle();
        jswOptionset optionseta = new jswOptionset(this, "panel2a", true, true);
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
        jswPushButtonset pushbuttonseta = new jswPushButtonset(this, "panel2b", true, true, true);
        pushbuttonseta.setStyleAttribute("verticallayoutstyle", jswLayout.DISTRIBUTE);
        pushbuttonseta.setStyleAttribute("horizontallayoutstyle", jswLayout.MIDDLE);
        pushbuttonseta.applyStyle();
        jswPushButton pbr = pushbuttonseta.addNewButton("horizontalLayout Help", "hhelp");
        pbr.applyStyle(mwdefstyles.getStyle("redbutton"));
        jswPushButton pbb = pushbuttonseta.addNewButton("verticalLayout Help", "vhelp");
        pbb.applyStyle(mwdefstyles.getStyle("bluebutton"));
        panel2.add(" FILLH ", pushbuttonseta);

        /*===============================panel2c================*/
        jswVerticalPanel panel2c = new jswVerticalPanel("panel2c", true, false);
        this.textarea = new jswTextArea("some input", true);
        this.textarea.setBorder(jswStyle.makeLineBorder(Color.yellow, 3));
        // this.textarea.setStyleAttribute("minwidth",300);
        this.textarea.applyStyle();
        panel2c.add("  MINHEIGHT=501 FILLH FILLW WIDTH=500 MIDDLE ", this.textarea);
        jswButton clear = new jswButton(this, "CLEAR");
        panel2c.add("  ", clear);
        panel2c.setStyleAttribute("horizontallayoutstyle", jswLayout.MIDDLE);
        // panel2b.setStyleAttribute("minwidth",400);
        panel2c.setStyleAttribute("BackgroundColor", "blue");
        panel2c.setStyleAttribute("BorderWidth", 3);
        panel2c.applyStyle();
        panel2.add(" MINWIDTH=200 maxwidth=400   FILLW ", panel2c);
        /*===============================panel2c================*/
        jswTable table1 = makeTableExample();
        table1.setStyleAttribute("borderwidth", 3);
        table1.setStyleAttribute("bordercolor", "red");
        table1.applyStyle();
        panel2.add(" width=400  ", table1);
        mainpanel.add(" FILLH MINHEIGHT=300 FILLW ", panel2);
        // ====end of panel2==

        // ====start of panel3==
        jswHorizontalPanel panel3 = new jswHorizontalPanel("Panel 3", true, true);
        panel3.setBackground(Color.green);
        jswPanel treepanel = makeTreeExample();
        jswTextArea textarea2 = new jswTextArea("some more input");
        textarea2.setMinimumSize(new Dimension(10, 10));
        textarea2.applyStyle();
        jswSplitPane splitpane = new jswSplitPane(this, "splitpane1", false, treepanel, textarea2);
        splitpane.setStyleAttribute("height", 300);
        splitpane.applyStyle();
        splitpane.setMinimumSize(new Dimension(800, 200));
        panel3.add(" FILLW ", splitpane);
        mainpanel.add("  HEIGHT=400  FILLW ", panel3);
        // ====start of panel4===
        jswHorizontalPanel panel4 = new jswHorizontalPanel("Panel 4", true, true);
        panel4.getStyle().setBackgroundcolor("yellow");
        jswContainer jswContainer1 = new jswContainer("subpanel 1");
        jswContainer1.getStyle().setBackgroundcolor("red");
        jswContainer1.setStyleAttribute("height", 100);
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

        mainpanel.add(" HEIGHT=100  FILLW  ", panel4);
        panel4.applyStyle();
        mainpanel.repaint();
        add(mainpanel);
        pack();
        addWindowListener(new WindowAdapter()
        {
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
            this.jswActionPerformed(e);
        } else
        {
            if (this.textarea != null)
            {
                this.textarea.addText("handling :" + e.getActionCommand() + "\n");
            }
        }
    }

    public void jswActionPerformed(ActionEvent e)
    {
        String cmd = e.getActionCommand();
        Map<String, String> actioncmd = jswPanel.parseActionCommand(cmd);
        if (actioncmd.get("handlerclass").equalsIgnoreCase("jswButton"))
        {
            if (actioncmd.get("commandstring").equalsIgnoreCase("CLEAR"))
            {
                textarea.setText("");
                return;
            }
        }
        if (actioncmd.get("handlerclass").equalsIgnoreCase("jswPushButton"))
        {
            if (actioncmd.get("commandstring").equalsIgnoreCase("helpbuttons:hhelp"))
            {
                textarea.setText("");
                String[] helplist = jswHorizontalLayout.getHelp();
                for (String help : helplist)
                {
                    textarea.addText(help);
                }
                return;
            } else if (actioncmd.get("commandstring").equalsIgnoreCase("helpbuttons:vhelp"))
            {
                textarea.setText("");
                String[] helplist = jswVerticalLayout.getHelp();
                for (String help : helplist)
                {
                    textarea.addText(help);
                }
                return;
            }
        }
        if (this.textarea != null)
        {
            this.textarea.addText("jswhandling :" + actioncmd + "\n");
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
        //treepanel.setStyleAttribute("myheight", "300");
        treepanel.applyStyle();
        return (jswPanel) treepanel;
    }

    public jswTable makeTableExample()
    {
        jswStyles defstyles = jswStyles.getDefaultTableStyles();
        jswTable table1 = new jswTable(this, "table 1", defstyles);
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

        table1.removeCell(2, 1);
        jswButton abutton = new jswButton(table1, "press me");
        jswCell acell = table1.addCell(abutton, 2, 1);
        table1.removeCell(3, 1);
        jswDropDownBox addb = new jswDropDownBox(table1, "select");
        jswCell bcell = table1.addCell(addb, 3, 1);
        addb.setEnabled(false);
        addb.addItem("LEFT");
        addb.addItem("MIDDLE");
        addb.addItem("RIGHT");
        addb.setEnabled(true);
        table1.removeCell(4, 1);
        jswTextBox textbox = new jswTextBox(table1, "text");
        textbox.style.setMyWidth(100);
        textbox.applyStyle();
        jswCell ccell = table1.addCell(textbox, 4, 1);
        table1.removeCell(5, 1);
        jswOption opt = new jswOption(table1, "show total", false);
        jswCell ocell = table1.addCell(opt, 5, 1);


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
