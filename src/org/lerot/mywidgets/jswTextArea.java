package org.lerot.mywidgets;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class jswTextArea extends jswVerticalPanel   implements ActionListener
{

    private static final long serialVersionUID = 1L;
    public JTextArea textbox;

    public jswTextArea(String inLabel, boolean label)
    {
        super(inLabel,label);
        textbox = new JTextArea("Enter text",20,30);
        JScrollPane scrollPane = new JScrollPane(textbox);
        textbox.setEditable(true);
        textbox.setLineWrap(true);
        textbox.setWrapStyleWord(true);
        textbox.setEnabled(true);
        scrollPane.setEnabled(true);
        DefaultCaret caret = (DefaultCaret)textbox.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        setEnabled(true);
        add("FILLH", scrollPane);
        //textbox.setPreferredSize(new Dimension(100, 100));
        setPreferredSize(new Dimension(100, 100));
    }

    public jswTextArea(String inLabel)
	{
    	this(inLabel,true);
    	/*super(inLabel,true);
        textbox = new JTextArea("Enter text",20,30);
        JScrollPane scrollPane = new JScrollPane(textbox);
        textbox.setEditable(true);
        textbox.setLineWrap(true);
        textbox.setWrapStyleWord(true);
        textbox.setEnabled(true);
        scrollPane.setEnabled(true);
        setEnabled(true);
        add("FILLH", scrollPane);
        textbox.setPreferredSize(new Dimension(100, 100));
        setPreferredSize(new Dimension(100, 100));*/
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
       String text = textbox.getText();
       textbox.append(text + "/n");   
       textbox.selectAll();

//Make sure the new text is visible, even if there
//was a selection in the text area.
        textbox.setCaretPosition(textbox.getDocument().getLength());
    }

	public void addText(String text)
	{		
		 String atext = textbox.getText();
		 String btext = atext+"\n"+text;
         textbox.setText(btext);		
	}
	
    public String getText()
    {
        return textbox.getText();
    }

    @Override
	public boolean isSelected()
    {
        return false;
    }

    @Override
	public void setEnabled(boolean e)
    {
        textbox.setEnabled(e);
    }

    public void setSelected(boolean b)
    {

    }

    public void setStyle(String string)
    {
        setEnabled(true);
        textbox.setOpaque(true);
        textbox.setBackground(Color.LIGHT_GRAY);
        textbox.setDisabledTextColor(Color.blue);
        textbox.setFont(new Font("SansSerif", Font.ITALIC, 10));
        setEnabled(false);
    }

    public void setText(String t)
    {
        textbox.setText(t);
    }

	public void setText(String[] hhelp)
	{
		String alltext ="";
		for(String astring : hhelp)
		{
			alltext = alltext +"/n"+astring;
		}
		textbox.setText(alltext);		
	}


}

