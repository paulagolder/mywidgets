package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class jswTextBox extends jswWidget implements KeyListener // implements ComponentListener
{
	private static final long serialVersionUID = 1L;
	public JTextField textbox;
	Color oldbgcolor;
	private Color backgroundcolor;
	private Color alertcolor = Color.red;
	int bh = 30;
	int bl = 30;
	private String prompt;


	public jswTextBox(ActionListener al, String name, int mywidth,String actioncommand)
	{
		super(name);
		setPanelname(name);
		prompt = name;
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setActionListener(al);
		setActionCommand(actioncommand);
		textbox = new JTextField();
		textbox.addActionListener(this);
		textbox.addKeyListener(this);
		if (prompt == null || prompt.isEmpty())
		{
			prompt = " ";
		}
		jswTextPrompt tp7 = new jswTextPrompt(prompt, textbox);
		tp7.setForeground(Color.BLACK);
		tp7.setFont(jswDefaults.promptfont);
		tp7.changeAlpha(0.5f);
		tp7.changeStyle(Font.ITALIC);
		tp7.setVisible(true);
		textbox.setEditable(true);
		textbox.setEnabled(true);
		this.setStyleAttribute("mywidth", mywidth);
		this.setStyleAttribute("myheight", 40);
		DefaultCaret caret = (DefaultCaret) textbox.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		style.putAttribute("borderwidth", 0);
		add(textbox);
		applyStyle();
	}

	public jswTextBox(ActionListener al, String name)
	{
		this(al, name, 100,name);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		setSelection(getText());
        System.out.println(" in text box :"+getSelection());
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		jswActionEvent event = new jswActionEvent(this, uniqueId, getActionCommand());
		getActionlistener().actionPerformed(event);
	}

    public void applyStyle(jswStyle style)
	{
		setBorder(style.getBorder());
		textbox.setFont(style.getFont());
		textbox.setForeground(style.getColor("foregroundColor", Color.BLACK));
		textbox.setBackground(jswStyle.defaulttextboxcolor);
		textbox.setBackground(Color.red);
		int wd = style.getIntegerStyle("mywidth", bl);
		if (wd > bl) bl = wd;
		//bl=wd;
		int ht = style.getIntegerStyle("myheight", bh);
		if (ht > bh) bh = ht;
		Dimension d = new Dimension(bl, bh);
		textbox.setPreferredSize(d);
		textbox.setMaximumSize(new Dimension(1000, bh));
		textbox.setMinimumSize(d);
		setBackground(jswStyle.defaulttextboxcolor);
		setPreferredSize(d);
		setMaximumSize(d);
		setMinimumSize(d);
	}

	@Override
	public void addFocusListener(FocusListener fl)
	{
		textbox.addFocusListener(fl);
	}

	public void addActionListener(ActionListener al)
	{
		textbox.addActionListener(al);
	}

	@Override
	public void addKeyListener(KeyListener kl)
	{
		textbox.addKeyListener(kl);
	}

	public void clear()
	{
		textbox.setText("");
	}

	public int getInteger()
	{
		return Integer.parseInt(textbox.getText());
	}

	public String getText()
	{
		return textbox.getText();
	}

	public JTextField getTextField()
	{
		// TODO Auto-generated method stub
		return textbox;
	}



	@Override
	public void repaint()
	{
		if (textbox != null)
			textbox.repaint();
	}

	@Override
	public void setEnabled(boolean e)
	{
		textbox.setEnabled(e);
	}

	/*
	 * public void setLabel(String t) { label.setText(t); }
	 */

	@Override
	public void setBackground(Color c)
	{
		backgroundcolor = c;
		if (textbox != null)
			textbox.setBackground(c);
	}

	public void setAlertColor(Color ac)
	{
		alertcolor = ac;
	}

	public void setAlert(boolean b)
	{
		if (b)
		{
			if (oldbgcolor == null)
				oldbgcolor = backgroundcolor;
			if (textbox != null)
				textbox.setBackground(alertcolor);
		} else
		{
			if (oldbgcolor != null)
			{
				if (textbox != null)
					textbox.setBackground(oldbgcolor);
			}
		}
	}

	public void setSelected(boolean b)
	{

	}

	public void setText(int t)
	{
		textbox.setText(Integer.toString(t));
	}

	public void setText(String t)
	{
		textbox.setText(t);

	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// textbox.revalidate();
		// System.out.println("some action:" + getText());
	}

	public Dimension getMinimumSize()
	{
		Dimension minsize = textbox.getPreferredSize();
		jswStyle s = this.getStyle();
		int minwidth = minsize.width;
		int minheight = minsize.height;
		Dimension d = new Dimension(minwidth - 40, minheight - 40);
		//System.out.println("size textarea"+d);
		return d;
	}

}
