package org.lerot.mywidgets;

import java.awt.event.ActionListener;

public class jswWidget  extends jswPanel implements ActionListener
{


    public jswWidget(String name)
    {
        super(name);
    }

    public void addActionListener(ActionListener al)
    {
        addActionListener(al);
    }

    public void addActionListener(ActionListener al, String actionmessage)
    {
        addActionListener(al);
        setActionCommand(actionmessage);
    }

    private void setActionCommand(String actionmessage)
    {
    }


}
