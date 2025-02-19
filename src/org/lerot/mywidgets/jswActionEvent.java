package org.lerot.mywidgets;

import java.awt.event.ActionEvent;

public class jswActionEvent extends ActionEvent
{


    public jswActionEvent(Object source, int id, String command)
    {
        super(source, id, command);
    }
}
