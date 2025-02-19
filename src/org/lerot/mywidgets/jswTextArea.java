package org.lerot.mywidgets;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class jswTextArea extends jswPanel implements ActionListener, KeyListener
{

	private static final long serialVersionUID = 1L;
	public JTextArea textarea;
	JScrollPane scrollPane ;
    private int bw = 10;
    private int bh = 10;

	public jswTextArea(String inLabel, boolean label)
	{
		super(inLabel);
        setAlignmentX(Component.CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        textarea = new JTextArea("Enter text", 2, 3);
        //	Dimension d1 = textarea.getPreferredSize();
        //   bw= d1.width;
        //	bh= d1.height;
        scrollPane = new JScrollPane(textarea);
		textarea.setEditable(true);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setEnabled(true);
		textarea.addKeyListener(this);
		textarea.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		scrollPane.setEnabled(true);
		DefaultCaret caret = (DefaultCaret) textarea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setEnabled(true);
        add(scrollPane);
		applyStyle();
	}

	public jswTextArea(String inLabel)
	{
		this(inLabel, true);
	}

    public void applyStyle(jswStyle style)
	{
        int wd = style.getIntegerStyle("width", bw);
        if (wd > bw)
            bw = wd;
        int ht = style.getIntegerStyle("height", bh);
		if (ht > bh)
			bh = ht;
        Dimension d = new Dimension(bw, bh);
		if (textarea != null)
		{
			scrollPane.setBackground(Color.green);
            //	scrollPane.setMinimumSize(new Dimension (bw,bh));
			textarea.setFont(style.getFont());
			textarea.setForeground(style.getForegroundcolor());
			textarea.setBackground(jswStyle.defaulttextboxcolor);
			textarea.setBorder(style.getBorder());
            //	textarea.setPreferredSize(d);
            //	textarea.setMinimumSize(d);
		}
	    setBorder(style.getBorder());
		setBackground(jswStyle.defaulttextboxcolor);
		setPreferredSize(d);
		setMinimumSize(d);
	}

	@Override
	public void actionPerformed(ActionEvent evt)
	{
		String text = textarea.getText();
		textarea.setText(text);
		textarea.append(text + "/n");
		textarea.selectAll();
		textarea.setCaretPosition(textarea.getDocument().getLength());
	}

	public void addText(String text)
	{
		String atext = textarea.getText();
		String btext = atext + "\n" + text;
		textarea.setText(btext);
	}

	public String getText()
	{
		return textarea.getText();
	}



	@Override
	public void setEnabled(boolean e)
	{
		textarea.setEnabled(e);
	}

	public void setSelected(boolean b)
	{

	}

	public void setStyle(String string)
	{
		setEnabled(true);
		textarea.setOpaque(true);
		textarea.setBackground(Color.LIGHT_GRAY);
		textarea.setDisabledTextColor(Color.blue);
		textarea.setFont(new Font("SansSerif", Font.ITALIC, 10));
		setEnabled(false);
	}

	public void setText(String t)
	{
		textarea.setText(t);
	}

	public void setText(String[] hhelp)
	{
		String alltext = "";
		for (String astring : hhelp)
		{
			alltext = alltext + "/n" + astring;
		}
		textarea.setText(alltext);
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
        String text = textarea.getText();
		textarea.setText("");
		textarea.repaint();
		textarea.setText(text);
		textarea.selectAll();
		textarea.setCaretPosition(textarea.getDocument().getLength());
    }


    @Override
    public Dimension getMinimumSize()
    {
        Dimension minsize = textarea.getPreferredSize();
        jswStyle s = this.getStyle();
        int minwidth = minsize.width;
        int minheight = minsize.height;
        Dimension d = new Dimension(minwidth - 40, minheight - 40);
        //System.out.println("size textarea"+d);
        return d;
	}

}
