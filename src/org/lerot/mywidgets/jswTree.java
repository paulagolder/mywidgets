package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class jswTree extends jswPanel implements ComponentListener
{

	private class MyRenderer extends JLabel implements TreeCellRenderer
	{
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus)
		{
			setFont(defaultfont);
			setText(value.toString());
			if (sel)
				setForeground(selectedcolor);
			else
				setForeground(defaultcolor);
			return this;
		}
	}

	class MySelectionListener implements TreeSelectionListener
	{
		@Override
		public void valueChanged(TreeSelectionEvent e)
		{
			TreePath path = e.getPath();
			Object[] nodes = path.getPath();
			Object nd = null;
			for (int k = 0; k < nodes.length; k++)
			{
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[k];
				nd = node.getUserObject();
				// oid += "."+nd.getId();
			}
			Long t = System.currentTimeMillis() / 10000;
			int uniqueId = t.intValue();
			Map am = jswPanel.createActionMap(this, e);
			ActionEvent event = new ActionEvent(this, uniqueId, am.toString());
			actionlistener.actionPerformed(event);
		}
	}

	private static final long serialVersionUID = 1L;
	//static void print(String s)
	//{
	//	System.out.print(s);
	//}
	//int linecount;
	String name;
	//int panelwidth = 200;
	//boolean redisplay;
	public JTree reptree;
	public JScrollPane reptreeView;
	private Color defaultcolor;
	private Color selectedcolor;
	private Font defaultfont;
	private int bl = 200;
	private int bh = 20;

	public jswTree(ActionListener al, String inname, DefaultMutableTreeNode aNode)
	{
		super(0);
		name = inname;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		if (aNode == null)
			reptree = new JTree();
		else
			reptree = new JTree(aNode);
		reptreeView = new JScrollPane(reptree);
		reptreeView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		reptree.setEditable(false);
		reptree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		reptree.setShowsRootHandles(true);
		reptree.addTreeSelectionListener(new MySelectionListener());
		actionlistener = al;
		MyRenderer renderer = new MyRenderer();
		reptree.setCellRenderer(renderer);
		this.add(" FILLW ", reptreeView);
	//	this.setPreferredSize(new Dimension(bl, bh));
		this.addComponentListener(this);
		applyStyle();
	}

    public void applyStyle(jswStyle style)
	{
		int wd = style.getIntegerStyle("mywidth", bl);
		if (wd > bl)
			bl = wd;
		int ht = style.getIntegerStyle("myheight", bh);
		if (ht > bh)
			bh = ht;
		Dimension d = new Dimension(bl, bh);
		selectedcolor = style.getColor("selectedcolor", Color.red);
		if (reptreeView != null)
		{

			reptreeView.setBackground(Color.green);
			reptreeView.setFont(style.getFont());
			reptreeView.setForeground(style.getForegroundcolor());
			reptreeView.setBackground(jswStyle.defaulttextboxcolor);
			reptreeView.setBorder(style.getBorder());
			reptreeView.setPreferredSize(d);
			reptreeView.setMinimumSize(d);
		}
		Dimension d2 = new Dimension(bl, bh-30);
		reptree.setPreferredSize(d2);
		setBorder(style.getBorder());
		setBackground(jswStyle.defaulttextboxcolor);
		setPreferredSize(d);
		setMinimumSize(d);
	}

	public void actionPerformed(ActionEvent e)
	{
		Long t = System.currentTimeMillis() / 10000;
		int uniqueId = t.intValue();
		Map am = jswPanel.createActionMap(this, e);
		ActionEvent event = new ActionEvent(this, uniqueId, am.toString());
		actionlistener.actionPerformed(event);
	}

	public void addTreeModelListener(TreeModelListener tml)
	{
		reptree.getModel().addTreeModelListener(tml);
	}

	public void addTreeSelectionListener(TreeSelectionListener tsl)
	{
		reptree.addTreeSelectionListener(tsl);
	}


	@Override
	public void componentHidden(ComponentEvent e)
	{
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
		int ww = getWidth();
		int hw = getHeight();
		reptreeView.setPreferredSize(new Dimension(ww, hw));
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
	}

}
