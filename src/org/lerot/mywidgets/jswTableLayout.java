package org.lerot.mywidgets;

//import java.awt.Component;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class jswTableLayout extends jswLayout
{

	private class rowcol
	{
		public boolean minsize;
		protected double position;
		protected double size;

		@Override
		public String toString()
		{
			String out = "minsize=" + minsize + " position=" + position + " size=" + size;
			return out;
		}
	}

	public static final int CENTER = 0;
	private static final int DEFAULT_HGAP = 0;
	private static final int DEFAULT_VGAP = 0;
	private final Vector<rowcol> columns;
	private final Vector<rowcol> rows;
	private int hgap;
	int maxcol = 0;
	int maxrow = 0;

	private String trace;

	public jswTableLayout()
	{
		this(DEFAULT_HGAP, DEFAULT_VGAP);
	}

	public jswTableLayout(int hgap, int vgap)
	{
		this.hgap = hgap;
		rows = new Vector<>();
		columns = new Vector<>();
	}

	public void changed()
	{
	}

	@Override
	public void layoutContainer(Container parent)
	{
		trace = "table x";
		if (parent instanceof jswPanel)
		{
			String marker = ((jswPanel) parent).getMarker();
		}
		if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
		{
			System.out.println("tracing " + trace);
		}
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0)
		{
			this.hgap = 0;
		}
		Insets insets = parent.getInsets();
		padding = ((jswPanel) parent).padding;
	//	Dimension parentSize = parent.getParent().getSize();
		Dimension parentSize = parent.getSize();
		int usableWidth = parentSize.width - insets.left - insets.right - 2;
		// paul fix replace by border width?
		// - (fixedwidth);
		int availableHeight = parentSize.height - insets.top - insets.bottom;
		setRowCols((jswTable) parent);
		//scaleRows(availableHeight);
		scaleRows(0);
		scaleColumns(usableWidth);
		//scaleColumns(0);
		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (comp instanceof jswCell cell)
			{
				if (((jswPanel) parent).getPanelname().equalsIgnoreCase(trace))
				{
					//System.out.println(cell.toString());
				}
				int ncol = cell.getCol();
				int nrow = cell.getRow();
				int colspan = cell.colspan;
				rowcol rowsettings = rows.get(nrow);
				int y = (int) rowsettings.position + insets.top;
				int height = (int) rowsettings.size; // +insets.top+insets.bottom;
				rowcol colsettings = columns.get(ncol);
				int x = (int) colsettings.position + insets.left+padding.left;
				int width = 0;
				if (colspan > 1)
				{
					for (int c = 0; c < colspan; c++)
					{
						width += (int) columns.get(ncol + c).size;
					}

				} else
				{
					width = (int) colsettings.size+padding.left+padding.right;
				}
				comp.setBounds((x), (y), (width), (height));
			}
		}
	}

	public Dimension maximumLayoutSize(Container parent)
	{
		return minimumLayoutSize(parent);
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		padding = ((jswPanel)parent).getPadding();
		int ncomponents = parent.getComponentCount();
		if (ncomponents == 0)
		{
			this.hgap = 0;
		}
		setRowCols((jswTable) parent);
		double height = 0;
		for (rowcol r : rows)
		{
			height = height + r.size;
		}
		double width = 0;
		for (rowcol c : columns)
		{
			if (c != null)
				width = width + c.size;
		}
		int bwidth = 0;
		int bheight = 0;
		if (parent instanceof jswTable)
		{
			Border aborder = ((jswTable) parent).getBorder();
			if (aborder != null)
			{
				Insets insts = aborder.getBorderInsets(parent);
				bheight = insts.bottom + insts.top;
				bwidth = insts.left + insts.right;
			}
		}
		return new Dimension((int) width + bwidth+padding.left+padding.right, (int) height + bheight+padding.top+padding.bottom);
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return minimumLayoutSize(parent);
	}

	private void scaleColumns(int targetwidth)
	{
		int cwidth = 0;
		int cfixed = 0;

		for (int ncol = 0; ncol < maxcol; ncol++)
		{
			rowcol cset = columns.get(ncol);
			cwidth += cset.size;
			if (cset.minsize)
				cfixed += cset.size;
		}

		double desiredwidth = cwidth;
		double fixedwidth = cfixed;
		double scale = 1;
		if(targetwidth<1)
		{
			int cpos = 0;
			for (int ncol = 0; ncol < maxcol; ncol++)
			{
				rowcol cset = columns.get(ncol);
				if (!cset.minsize)
					cset.size = cset.size * scale;
				cset.position = cpos;
				cpos += cset.size;
				columns.set(ncol, cset);
			}
			return;
		}
		if (targetwidth > desiredwidth)
		{
			scale = (targetwidth - fixedwidth) / (desiredwidth - fixedwidth);
			int cpos = 0;
			for (int ncol = 0; ncol < maxcol; ncol++)
			{
				rowcol cset = columns.get(ncol);
				if (!cset.minsize)
					cset.size = cset.size * scale;
				cset.position = cpos;
				cpos += cset.size;
				columns.set(ncol, cset);
			}
		} else
		{
			scale = targetwidth / desiredwidth;
			int cpos = 0;
			for (int ncol = 0; ncol < maxcol; ncol++)
			{
				rowcol cset = columns.get(ncol);
				cset.size = cset.size * scale;
				cset.position = cpos;
				cpos += cset.size;
				columns.set(ncol, cset);
			}
		}

	}

	private void scaleRows(int targetheight)
	{

		int rpos = 0;
		for (int nrow = 0; nrow < maxrow; nrow++)
		{
			rowcol rset = rows.get(nrow);
			if (rset != null)
			{
				rset.position = rpos;
				rows.set(nrow, rset);
				rpos += rset.size;
			}
		}

		if (targetheight < 1)return;
		if (rpos < 1) return;

		float scale = 1.0f;
		if (rpos < targetheight) scale = targetheight / rpos;
		rpos = 0;
		for (int nrow = 0; nrow < maxrow; nrow++)
		{
			rowcol rset = rows.get(nrow);
			if (rset != null)
			{
				rset.size = rset.size * scale;
				rset.position = rpos;
				rpos += rset.size;
				rows.set(nrow, rset);
			}
		}

	}

	public void setRowCols(jswTable parent)
	{
		Insets padding = parent.getPadding();
		rows.clear();
		columns.clear();
		maxrow = 0;
		maxcol = 0;
		int ncomponents = parent.getComponentCount();
		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (comp instanceof jswCell cell)
			{
				int ncol = cell.getCol();
				int nrow = cell.getRow();
				if (ncol > maxcol)	maxcol = ncol;
				if (nrow > maxrow)	maxrow = nrow;
			}
		}
		maxcol++;
		maxrow++;
		rows.setSize(maxrow);
		columns.setSize(maxcol);
		for (int nrow = 0; nrow < maxrow; nrow++)
		{
			rowcol rset = new rowcol();
			rset.size = 0;
			rset.position = 0;
			rows.set(nrow, rset);
			jswStyle rowstyle = parent.getRowStyle(nrow);
			if (rowstyle != null)
			{
				int height = rowstyle.getIntegerStyle("height", 0);
				rset.minsize = rowstyle.getBooleanStyle("minheight", false);
				if (height > 0)
				{
					rset.size = height;
					rset.minsize = true;
				}
			} else
			{
				rset.minsize = false;
			}
			rows.set(nrow, rset);
		}
		for (int ncol = 0; ncol < maxcol; ncol++)
		{
			rowcol cset = new rowcol();
			cset.size = 0;
			cset.position = 0;
			jswStyle colstyle = parent.getColStyle(ncol);
			if (colstyle != null)
			{
				int width = colstyle.getIntegerStyle("width", 0);
				cset.minsize = colstyle.getBooleanStyle("minwidth", false);
				if (width > 0)
				{
					cset.size = width;
					cset.minsize = true;
				}
			} else
			{
				cset.minsize = false;
			}
			columns.set(ncol, cset);
			//
		}
		for (int i = 0; i < ncomponents; i++)
		{
			Component comp = parent.getComponent(i);
			if (comp instanceof jswCell cell)
			{
				Dimension min = null;
				if (cell.getComponentCount() == 0)
					continue;
				Component cellcontent = cell.getComponent(0);
				if (cellcontent instanceof jswPanel jccomp)
				{
					min = jccomp.getMinimumSize();
				} else
				{
					JComponent jccomp = (JComponent) cellcontent;
					min = jccomp.getMinimumSize();
					System.out.println(" not jswpanel =" + comp.getClass().getName());
				}
				min.width = min.width+padding.left+padding.right;
				min.height = min.height+padding.top+padding.bottom;
				int ncol = cell.getCol();
				int nrow = cell.getRow();
				int colspan = cell.colspan;
				int width = cell.width;
				if (width > min.width)
				{
					min.width = width;
					System.out.println(" Setting width =" + width);
				}
				if (width < min.width)
				{
					//min.width = width;
					//System.out.println(" Setting width =" + width);
				}
				if (ncol > -1 && nrow > -1)
				{
					rowcol rset = rows.get(nrow);
					if (rset.size < min.height)
					{
						rset.size = min.height;
						rows.set(nrow, rset);
					}

					if (colspan > 1)
					{
						double sharedwidth = min.width / colspan;
						for (int c = ncol; c < ncol + colspan; c++)
						{
							rowcol cset = columns.get(c);
							if (cset.size < sharedwidth)
							{
								cset.size = sharedwidth;
								columns.set(c, cset);
							}
						}
					} else
					{
						rowcol cset = columns.get(ncol);
						if (cset.size < min.width)
						{
							cset.size = min.width;
							columns.set(ncol, cset);
						}
					}
				}
			}
		}
		for (int ncol = 0; ncol < maxcol; ncol++)
		{
			rowcol cset = columns.get(ncol);
			jswStyle colstyle = parent.getColStyle(ncol);
			cset.minsize = false;
			if (colstyle != null)
			{
				int width = colstyle.getIntegerStyle("width", 0);
				cset.minsize = colstyle.getBooleanStyle("minwidth", false);
				if (width > 0)
				{
					cset.minsize = true;
					if (width > cset.size)
					{
						cset.size = width;
					}
				}
			}
			columns.set(ncol, cset);
		}
	}

}
