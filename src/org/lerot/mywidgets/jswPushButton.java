package org.lerot.mywidgets;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;
import javax.swing.border.Border;


public class jswPushButton extends jswWidget //implements ItemListener
{
    private static final long serialVersionUID = 1L;
    //private final String command;
    JToggleButton button;
    int bh = 30;
    int bl = 30;

    public jswPushButton(ActionListener al, String label)
    {
        this(al, label, label);
    }

    public jswPushButton(ActionListener al, String label, String command)
    {
        super(label);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        button = new JToggleButton(label);
        button.addActionListener(this);
        setActionListener(al);
        setActionCommand(command);
       // setTag(command);
         button.setActionCommand(command);
      //  this.command = command;
        add(button);
        applyStyle();
        button.setVisible(true);
    }

   @Override
    public void actionPerformed(ActionEvent e)
    {
        Long t = System.currentTimeMillis() / 10000;
        int uniqueId = t.intValue();
        jswActionEvent event = new jswActionEvent(this, uniqueId, e.getActionCommand());
        getActionlistener().actionPerformed(event);
    }

    public void setSelected()
    {
        // button.t

    }

    public void setSelected(boolean b)
    {
        button.setSelected(b);
    }

    public void applyStyle(jswStyle style)
    {
        int l = button.getText().length() * 8 + 30;
        if (l > bl) bl = l;
        button.setFont(style.getFont());
        button.setBackground(jswStyle.defaultwidgetcolor);
        button.setForeground(style.getForegroundcolor());
        Border bborder = BorderFactory.createRaisedBevelBorder();
        button.setBorder(bborder);
        int wd = style.getIntegerStyle("mywidth", bl);
        if (wd > bl) bl = wd;
        int ht = style.getIntegerStyle("myheight", bh);
        if (ht > bh) bh = ht;
        Dimension d = new Dimension(bl, bh);
        button.setPreferredSize(d);
        button.setMaximumSize(d);
        button.setMinimumSize(d);
        setBackground(jswStyle.TRANSPARENT);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
    }

    public String getLabel()
    {
        return button.getText();
    }

    public void itemStateChanged(ItemEvent evt)
    {
        System.out.println(" Selecting x "+button.getText());
    }
}
