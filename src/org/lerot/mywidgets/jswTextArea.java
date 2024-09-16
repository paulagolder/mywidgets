package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

//public class jswTextArea extends jswHorizontalPanel implements ActionListener, KeyListener
public class jswTextArea extends jswPanel implements ActionListener, KeyListener
{

	private static final long serialVersionUID = 1L;
	public JTextArea textarea;
	JScrollPane scrollPane ;
	private int bl = 200;
	private int bh = 20;

	public jswTextArea(String inLabel, boolean label)
	{
		super(inLabel);
		//this.addStyle("indent", 10);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		textarea = new JTextArea("Enter text", 20, 30);
		 scrollPane = new JScrollPane(textarea);
		textarea.setEditable(true);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setEnabled(true);
		textarea.addKeyListener(this);
		textarea.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		scrollPane.setEnabled(true);
		DefaultCaret caret = (DefaultCaret) textarea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		setEnabled(true);
		add( " FILLW ",scrollPane);
		setPreferredSize(new Dimension(100, 100));
		applyStyle();
	}

	public jswTextArea(String inLabel)
	{
		this(inLabel, true);

	}

	void applyStyle(jswStyle style)
	{

		int wd = style.getIntegerStyle("mywidth", bl);
		if (wd > bl)
			bl = wd;
		int ht = style.getIntegerStyle("myheight", bh);
		if (ht > bh)
			bh = ht;
		Dimension d = new Dimension(bl, bh);
		if (textarea != null)
		{
			scrollPane.setBackground(Color.green);
			textarea.setFont(style.getFont());
			//textarea.setBackground(jswStyle.TRANSPARENT);
			textarea.setForeground(style.getForegroundcolor());
			textarea.setBackground(jswStyle.defaulttextboxcolor);
			textarea.setBorder(style.getBorder());
			textarea.setPreferredSize(d);
			// textbox.setMaximumSize(d);
			textarea.setMinimumSize(d);
		}
	    setBorder(style.getBorder());
	//	setBackground(jswStyle.TRANSPARENT);
		setBackground(jswStyle.defaulttextboxcolor);
		setPreferredSize(d);
		// setMaximumSize(d);
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
	public boolean isSelected()
	{
		return false;
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
		
		System.out.println(" bonkers");
        String text = textarea.getText();
		textarea.setText("");
		textarea.repaint();
		textarea.setText(text);
		//textarea.append("/n");
		textarea.selectAll();
		textarea.setCaretPosition(textarea.getDocument().getLength());
		
	}

}
