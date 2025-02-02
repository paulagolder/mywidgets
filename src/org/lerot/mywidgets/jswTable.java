package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class jswTable extends jswPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	jswStyles tablestyles = new jswStyles();

	public jswTable(ActionListener al, String name, jswStyles styles)
	{
		super(name);
		tablestyles.copyStyles(styles);
		style = styles.getStyle("table");
		setLayout(new jswTableLayout());
		Color bcolor = style.getColor("backgroundColor", Color.BLUE);
		setBackground(bcolor);
		actionlistener = al;
		int borderwidth = style.getIntegerStyle("borderWidth", 0);
		if (borderwidth > 0)
		{
			Color bordercolor = style.getColor("bordercolor", Color.yellow);
			this.setBorder(jswStyle.makeLineBorder(bordercolor, borderwidth));
		} else
		{

		}
		setName(name);
		applyStyle();
	}

    public void applyStyle(jswStyle astyle)
	{
		Color bcolor = astyle.getColor("backgroundColor", Color.BLUE);
		setBackground(bcolor);
		int borderwidth = astyle.getIntegerStyle("borderWidth", 0);
		if (borderwidth > 0)
		{
			Color bordercolor = astyle.getColor("bordercolor", Color.yellow);
			this.setBorder(jswStyle.makeLineBorder(bordercolor, borderwidth));
		} 
	}
	
	void applyAllStyles()
	{
		int nc = this.getComponentCount();
		for(int i=0 ; i<nc ; i++)
		{		
			Component comp = this.getComponent(i);
			if (comp instanceof jswCell acell)
			{
				jswStyle astyle = getCellStyle(acell.getRow(), acell.getCol());
				acell.applyStyle(astyle);
			//	jswStyle cstyle = getCellContentStyle(acell.row,acell.col);
				jswStyle cstyle = getCellStyle(acell.getRow(), acell.getCol());
				acell.applyContentStyle(cstyle);
			}
		}		
	}

	public jswCell  addCell(jswPanel cont, int row, int col)
	{
		jswCell acell = addCell(cont, null, row, col);
		return acell;
	}

	public jswCell addCell(Integer anint, int row, int col)
	{
		jswLabel alabel = new jswLabel(anint);
		jswCell acell = addCell(alabel,row,col);
		return acell;
	}

	public jswCell addCell(String astring, int row, int col)
	{
		jswLabel alabel = new jswLabel(astring);
		jswCell acell = addCell(alabel,row,col);
		return acell;
	}

	public jswCell addCell(jswPanel cont, String setting, int row, int col)
	{
		jswStyle cellstyle = getCellStyle(row, col);
		jswCell acell = new jswCell(this,row, col);		
		String settings = cellstyle.getStringStyle("horizontalAlign");
		if (setting != null) settings += " " + setting + " ";
		acell.setBorder(jswStyle.makeCellBorder(Color.black,4));
		cont.setBackground(new Color(0, 0, 0, 0));
		acell.setPadding(5,5,5,15);
		acell.add(settings, cont);
		acell.add( cont);
		cont.addActionListener(acell); 
		add(acell);
		jswStyle cellcontentstyle = getCellContentStyle(row, col);
		//cont.style.setForegroundcolor("red");
		cont.applyStyle(cellstyle);
	//	acell.applyContentStyle(acell.style);
		return acell;
	}

	private jswStyle getCellContentStyle(int row, int col)
	{
		jswStyle cellstyle = new jswStyle();
		cellstyle.overlay(tablestyles.getStyle("cellcontent"));
		cellstyle.overlay(tablestyles.getStyle("colcontent_" + col));
		cellstyle.overlay(tablestyles.getStyle("rowcontent_" + row));
		cellstyle.overlay(tablestyles
				.getStyle("cellcontent_" + col + "_" + row));
		return cellstyle;
	}

	private jswStyle getCellStyle(int row, int col)
	{
		jswStyle cellstyle = new jswStyle();
		cellstyle.overlay(tablestyles.getStyle("cell"));
		cellstyle.overlay(tablestyles.getStyle("col_" + col));
		cellstyle.overlay(tablestyles.getStyle("row_" + row));
		cellstyle.overlay(tablestyles.getStyle("cell_" + col + "_" + row));
		return cellstyle;
	}

	public jswStyle getColStyle(int col)
	{
		jswStyle colstyle = new jswStyle();
		colstyle.overlay(tablestyles.getStyle("col"));
		colstyle.overlay(tablestyles.getStyle("col_" + col));
		return colstyle;
	}

	public jswStyle getRowStyle(int row)
	{
		jswStyle rowstyle = new jswStyle();
		rowstyle.overlay(tablestyles.getStyle("row"));
		rowstyle.overlay(tablestyles.getStyle("col_" + row));
		return rowstyle;
	}

	public void removeCell(int row, int col)
	{
		int nc = this.getComponentCount();
		for(int i=0 ; i<nc ; i++)
		{		
			try
			{
			Component comp = this.getComponent(i);
				if (comp instanceof jswCell acell)
			{
				if (acell.getRow() == row & acell.getCol() == col)
		         {
		        	 this.remove(comp);
		         }
			}
			}
		    catch(Exception e) {
				  //  Block of code to handle errors
			}
		}	
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		HashMap<String,String> am = jswPanel.createActionMap(this, e);
		am.put("tablename",getPanelname());
		//System.out.println("+-+"+am);
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		ActionEvent event = new ActionEvent(this, uniqueId,am.toString());
		actionlistener.actionPerformed(event);
	}





}
