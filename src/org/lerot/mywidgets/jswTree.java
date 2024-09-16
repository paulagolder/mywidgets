package org.lerot.mywidgets;




	import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 1L;


			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value,
					boolean sel, boolean expanded, boolean leaf, int row,
					boolean hasFocus)
			{
				setFont(defaultfont);
					setText(value.toString());
					if(sel)
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
				for (int k=0; k < nodes.length; k++)
				{
					DefaultMutableTreeNode node = 
						(DefaultMutableTreeNode)nodes[k];
					nd = (Object)node.getUserObject();
					//oid += "."+nd.getId();
				}
				Long t = System.currentTimeMillis() / 10000;
				int uniqueId = t.intValue();
				ActionEvent event = new ActionEvent(this, uniqueId,nd.toString());
				System.out.println(" in tree  sl");
				actionlistener.actionPerformed(event);
			}

			
		}

		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 1L;

		static void print(String s)
		{
			System.out.print(s);
		}

		//public JPanel commandPanel;

		int linecount;
		String name;
		int panelwidth = 200;
		boolean redisplay;
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
			if (aNode == null) reptree = new JTree();
			else
				reptree = new JTree(aNode);
			reptreeView = new JScrollPane(reptree);
			reptreeView
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			reptree.setEditable(false);
			reptree.getSelectionModel().setSelectionMode(
					TreeSelectionModel.SINGLE_TREE_SELECTION);
			reptree.setShowsRootHandles(true);
            reptree.addTreeSelectionListener(new MySelectionListener());
        	actionlistener = al;
			MyRenderer renderer = new MyRenderer();
			reptree.setCellRenderer(renderer);
			this.add(" FILLW " ,reptreeView);
			this.setPreferredSize(new Dimension(bl, bh));
			this.addComponentListener(this);
			applyStyle();

		}

		
       public void xapplyStyle(jswStyle astyle)
       {
    	   defaultcolor = astyle.getColor("foregroundcolor",Color.black);
    	   selectedcolor = astyle.getColor("selectedcolor",Color.red);
    	   defaultfont = astyle.getFont();
    	   
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
   		if (reptreeView != null)
   		{
   			reptreeView.setBackground(Color.green);
   			reptreeView.setFont(style.getFont());
   			//textarea.setBackground(jswStyle.TRANSPARENT);
   			reptreeView.setForeground(style.getForegroundcolor());
   			reptreeView.setBackground(jswStyle.defaulttextboxcolor);
   			reptreeView.setBorder(style.getBorder());
   			reptreeView.setPreferredSize(d);
   			// textbox.setMaximumSize(d);
   			reptreeView.setMinimumSize(d);
   		}
   	    setBorder(style.getBorder());
   	//	setBackground(jswStyle.TRANSPARENT);
   		setBackground(jswStyle.defaulttextboxcolor);
   		setPreferredSize(d);
   		// setMaximumSize(d);
   		setMinimumSize(d);

   	}
		



		public void actionPerformed(ActionEvent e)
		{
			System.out.println(" in tree al");
			
			Long t = System.currentTimeMillis() / 10000;
			int uniqueId = t.intValue();
			ActionEvent event = new ActionEvent(this, uniqueId, "value = tree selection ");
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

		public void CommandPanelHeight(int hieght)
		{

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
			//reptreeView.setPreferredSize(new Dimension(ww - 40, hw - 30));
			reptreeView.setPreferredSize(new Dimension(ww , hw ));
		//	commandPanel.setPreferredSize(new Dimension(ww - 40, commandPanel.getHeight()));
		}

		@Override
		public void componentShown(ComponentEvent e)
		{
		}

		
	}


