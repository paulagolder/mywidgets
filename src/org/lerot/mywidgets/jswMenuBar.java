package org.lerot.mywidgets;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class jswMenuBar extends jswWidget
{

    private final JMenuBar menuBar;
    private final ActionListener actionlistener;

    public jswMenuBar(String name, ActionListener al)
    {
        super(name);
        actionlistener = al;
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 441, 21);
        add(menuBar);
    }

    public JMenu addMenuHeading(String name)
    {
        JMenu menu = new JMenu(name);
        menuBar.add(menu);
        //menu.addActionListener(this);
        return menu;
    }

    public void addMenuItem(JMenu heading, String name, String command)
    {
        JMenuItem mfile = new JMenuItem(name);
        mfile.addActionListener(this);
        mfile.setActionCommand(command);
        heading.add(mfile);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        HashMap<String, String> am = jswPanel.createActionMap(this, e);
        Long t = System.currentTimeMillis() / 10000;
        int uniqueId = t.intValue();
        ActionEvent event = new ActionEvent(this, uniqueId, am.toString());
        actionlistener.actionPerformed(event);
    }


}


