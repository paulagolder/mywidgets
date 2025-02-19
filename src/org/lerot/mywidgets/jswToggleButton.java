package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class jswToggleButton extends jswWidget implements  ChangeListener
{
    private static final long serialVersionUID = 1L;
    private final String label;
    JToggleButton button;


    public jswToggleButton(ActionListener al, String alabel)
    {
        this(al, alabel, alabel);
    }

    public jswToggleButton(ActionListener al, String alabel, String command)
    {
        super("TB" + alabel);
        UIDefaults dflts = UIManager.getLookAndFeelDefaults();
        dflts.put("ToggleButton.select", Color.GREEN);
        label = alabel;
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        button = new JToggleButton(label);
       button.addActionListener(this);
       button.addChangeListener(this);
        setActionListener(al);
        button.setActionCommand(command);
        add(button);
        applyStyle();
        button.setVisible(true);
    }

    public void applyStyle()
    {
        int bl = label.length() * 8 + 30;
        int bh = 30;
        button.setFont(style.getFont());
        button.setBackground(jswStyle.defaultwidgetcolor);
        button.setForeground(style.getForegroundcolor());
        button.setBorder(style.getBorder());
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

   @Override
    public void actionPerformed(ActionEvent e)
    {
        button.repaint();
      //  HashMap<String, String> am = jswPanel.createActionMap(this, e);
        String mess;
        if (button.isSelected()) mess = "ON";
        else mess = "OFF";
     //   am.put("value", mess);
     //   am.put("command", "buttontoggled");
	//	System.out.println("action"+am);
        Long t = System.currentTimeMillis() / 10000;
        int uniqueId = t.intValue();
        jswActionEvent event = new jswActionEvent(this, uniqueId, e.getActionCommand());
        getActionlistener().actionPerformed(event);
    }


    public void stateChanged(ChangeEvent evt)
    {
        if (button.isSelected())
        {
            button.setText("ON");
        } else
        {
            button.setText("OFF");
        }
        /*
		//System.out.println("jtogglechange");
		button.repaint();
		HashMap<String, String> am = jswPanel.createActionMap(this, evt);
		String mess;
		if (button.isSelected()) mess = "ON";
		else mess = "OFF";
		am.put("value", mess);
		am.put("command", "buttontoggled");
		//System.out.println("jtoggle"+am);
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		jswActionEvent event = new jswActionEvent(this, uniqueId, am.toString());
		actionlistener.actionPerformed(event);*/
    }

    public boolean isSelected()
    {
        return button.isSelected();
    }

}

	
