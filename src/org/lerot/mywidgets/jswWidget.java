package org.lerot.mywidgets;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;

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

    // room beyond the label's measured width: on a screen scaled to 125% or
    // 150% (Windows), text drawn at that scale can come out a pixel or two
    // wider than it measured, and a button sized exactly to it then cuts its
    // label short ("Brow…")
    private static final int LABEL_SLACK = 8;

    // style a button using the look and feel's own border and size, with a
    // little room to spare for its label;
    // mywidth/myheight act as minimums (height defaults to 30, which the layouts expect)
    protected void styleButton(AbstractButton button, jswStyle style)
    {
        button.setFont(style.getFont());
        button.setForeground(style.getForegroundcolor());
        button.setPreferredSize(null);
        button.setMinimumSize(null);
        button.setMaximumSize(null);
        Dimension pref = button.getPreferredSize();
        int wd = Math.max(pref.width + jswScale.px(LABEL_SLACK), style.getIntegerStyle("mywidth", 0));
        int ht = Math.max(Math.max(pref.height, jswScale.px(30)), style.getIntegerStyle("myheight", 0));
        Dimension d = new Dimension(wd, ht);
        button.setPreferredSize(d);
        button.setMaximumSize(d);
        button.setMinimumSize(d);
        setOpaque(false);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
        setSize(d);
    }


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
