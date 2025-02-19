package org.lerot.mywidgets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class jswWidget extends jswPanel implements ActionListener
{
    public String actionCommand;
    public String selection;
    public ActionListener actionlistener = null;

    public jswWidget(String name)
    {
        super(name);
        //setActionCommand(name);
    }

    public abstract void actionPerformed(ActionEvent e);


    public String getActionCommand()
    {
        return actionCommand;
    }

    public void setActionCommand(String actionCommand)
    {
        this.actionCommand = actionCommand;
    }

    public String getSelection()
    {
        return selection;
    }

    public void setSelection(String selection)
    {
        this.selection = selection;
    }

    public boolean isSelected()
    {
        return false;
    }

    public ActionListener getActionlistener()
    {
        return actionlistener;
    }

    public void setActionListener(ActionListener al)
    {
        this.actionlistener = al;
    }
}
