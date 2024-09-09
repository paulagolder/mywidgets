package org.lerot.mywidgets;




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


public class jswTextArea extends jswHorizontalPanel   implements ActionListener
{

    private static final long serialVersionUID = 1L;

    public JTextArea textarea;

	private int bl=200;

	private int bh=20;
    

    public jswTextArea(String inLabel, boolean label)
    {
        super(inLabel,label);
        this.addStyle("indent", 10);
        textarea = new JTextArea("Enter text",20,30);
        JScrollPane scrollPane = new JScrollPane(textarea);     
        textarea.setEditable(true);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setEnabled(true);
        scrollPane.setEnabled(true);
        DefaultCaret caret = (DefaultCaret)textarea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        setEnabled(true);
        add("FILLw  ", scrollPane);
        setPreferredSize(new Dimension(100, 100));
        applyStyle();
    }

    public jswTextArea(String inLabel)
	{
    	this(inLabel,true);
    	
	}
    
    void applyStyle(jswStyle style)
	{	
    
    	int wd =style.getIntegerStyle("mywidth",bl);
		if(wd > bl) bl= wd;
		int ht =style.getIntegerStyle( "myheight",bh);
		if(ht > bh ) bh=ht;
		Dimension d = new Dimension(bl, bh);
			if(textarea!=null)	
			{
		 textarea.setFont(style.getFont());
		 textarea.setBackground(jswStyle.TRANSPARENT);
		 textarea.setForeground(style.getForegroundcolor());
		 textarea.setBorder(style.getBorder());
		 textarea.setPreferredSize(d);
		// textbox.setMaximumSize(d);
		 textarea.setMinimumSize(d);
			}
		setBackground(jswStyle.TRANSPARENT);
		setPreferredSize(d);
	//	setMaximumSize(d);
		setMinimumSize(d);
			
	}
	

	@Override
	public void actionPerformed(ActionEvent evt) {
       String text = textarea.getText();
       textarea.append(text + "/n");   
       textarea.selectAll();
        textarea.setCaretPosition(textarea.getDocument().getLength());
    }

	public void addText(String text)
	{		
		 String atext = textarea.getText();
		 String btext = atext+"\n"+text;
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
		String alltext ="";
		for(String astring : hhelp)
		{
			alltext = alltext +"/n"+astring;
		}
		textarea.setText(alltext);		
	}

	


}

