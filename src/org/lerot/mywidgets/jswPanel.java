package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;

import org.lerot.mywidgets.jswTree.MySelectionListener;

public abstract class jswPanel extends JPanel implements ActionListener

{
    private static final long serialVersionUID = 1L;
    private String panelname;
    private final int setHeight = 0;
    private final int setWidth = 0;
    protected jswStyle style;
    private String tag = "";
    private String marker = "";
    protected Insets padding = new Insets(1, 1, 1, 1);
    ActionListener actionlistener = null;

    public jswPanel(String name)
    {
        panelname = name;
        initialiseStyle(this.getClass().getSimpleName());
        //applyStyle();
    }

    public jswPanel(int count)
    {
        this(String.valueOf(count));
    }


    public void initialiseStyle(String stylename)
    {
        style = new jswStyle();
        style.setDefaultStyle();
        jswStyle basestyle = jswStyles.getDefaultStyles().getStyle(stylename);
        style.overlay(basestyle);
    }

    public Insets getPadding()
    {
        return padding;
    }

    public static Insets makePadding(String ins)
    {
        Insets padding = null;
        Vector<Integer> pvs = new Vector<Integer>();
        String[] tokenArray = ins.split("\\s*,\\s*");
        for (String token : tokenArray)
        {
            int v = Integer.parseInt(token);
            pvs.add(v);
        }
        if (pvs.size() == 1)
        {
            padding = new Insets(pvs.get(0), pvs.get(0), pvs.get(0), pvs.get(0));
        } else if (pvs.size() == 2)
        {
            padding = new Insets(pvs.get(0), pvs.get(1), pvs.get(0), pvs.get(1));
        } else if (pvs.size() == 4)
        {
            padding = new Insets(pvs.get(0), pvs.get(1), pvs.get(2), pvs.get(3));
        } else
            padding = new Insets(1, 1, 1, 1);
        return padding;

    }

    public void setPadding(int in)
    {
        padding = new Insets(in, in, in, in);
    }

    public void setPadding(String instr)
    {
        padding = makePadding(instr);
    }

    public void setPadding()
    {
        setPadding(5);
    }

    public void setPadding(int i, int j, int k, int l)
    {
        padding = new Insets(i, j, k, l);

    }

    public void addActionListener(ActionListener al)
    {
        actionlistener = al;

    }

    public void applyStyle()
    {
        applyStyle(style);
    }


    public void applyContentStyle()
    {
        applyContentStyle(style);
    }


    public void applyContentStyle(jswStyle style2)
    {
        applyStyle(style);
    }

    public void applyStyle(jswStyle astyle)
    {

        Font sfont = astyle.getFont();
        this.setFont(sfont);
        this.setPanelBorder(astyle);

        Border aborder = getBorder();
        if (aborder != null)
            padding = aborder.getBorderInsets(this);

        this.setForeground(astyle.getColor("foregroundColor", Color.BLACK));
        this.setBackground(astyle.getColor("backgroundColor", Color.green));

    }

    public void setPanelBorder(jswStyle style)
    {
        int bs = style.getBorderStyle();
        if (bs == jswStyle.TITLEDBORDER)
        {
            Border bd = style.makecborder(panelname);
            setBorder(bd);
            this.setPadding(bd.getBorderInsets(this));
        } else if (bs == jswStyle.LINEBORDER)
        {
            setBorder(style.makeLineborder(new Insets(20, 20, 20, 20)));
            this.setPadding(10, 10, 10, 10);
        } else
        {
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            this.setPadding(1, 1, 1, 1);
        }

    }

    private void setPadding(Insets borderInsets)
    {
        padding = borderInsets;

    }

    public void setStyleAttribute(String string, int i)
    {
        style.putAttribute(string, i);

    }

    public void setStyleAttribute(String string, String string2)
    {
        style.putAttribute(string, string2);

    }

    public void setFontStyle(String font, int fonttype, int fontsize)
    {
        style.setFontsize(fontsize);
        style.setFontstyle(fonttype);
        style.setFontname(font);
    }

    public String getPanelname()
    {
        return panelname;
    }

    public String getTag()
    {
        return tag;
    }

    public boolean isSelected()
    {
        return false;
    }

    public int jswGetHeight()
    {
        if (setHeight > 0)
            return setHeight;
        Dimension d = getPreferredSize();
        if (d.height > 0)
            return d.height;
        else
        {
            d = getMaximumSize();
            if (d.height > 0)
                return d.height;
            else
            {
                d = getMinimumSize();
                return d.height;
            }
        }
    }

    public int jswGetWidth()
    {
        if (setWidth > 0)
            return setWidth;
        Dimension d = getPreferredSize();
        if (d.width > 0)
            return d.width;
        else
        {
            d = getMaximumSize();
            if (d.width > 0)
                return d.width;
            else
            {
                d = getMinimumSize();
                return d.width;
            }
        }
    }

    public void setPanelname(String panelname)
    {
        this.panelname = panelname;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
    }

    protected Rectangle getBorderBounds()
    {
        int x = padding.left;
        int y = padding.top;
        int width = getSize().width - padding.left - padding.right;
        int height = getSize().height - padding.top - padding.bottom;
        Rectangle bounds = new Rectangle(x, y, width, height);
        return bounds;
    }

    public String getMarker()
    {
        return marker;
    }

    public void setMarker(String marker)
    {
        this.marker = marker;
    }

    public void addStyle(String attname, String avalue)
    {
        getStyle().putAttribute(attname, avalue);
    }

    public void addStyle(String attname, int avalue)
    {
        getStyle().putAttribute(attname, avalue);
    }

    public jswStyle getStyle()
    {
        return style;
    }

    public void setStyle(jswStyle style)
    {
        this.style = style;
    }

    public void actionPerformed(ActionEvent e)
    {
        Long t = System.currentTimeMillis() / 10000;
        int uniqueId = t.intValue();
        HashMap<String, String> am = jswPanel.createActionMap(this, e);
        ActionEvent event = new ActionEvent(this, uniqueId, am.toString());
        actionlistener.actionPerformed(event);
    }

    public static Map<String, String> parseActionCommand(String cmd)
    {
        cmd = cmd.replace("{", " ");
        cmd = cmd.replace("}", "");
        String[] str = cmd.split(",");
        Map<String, String> actioncmd = new HashMap<String, String>();
        String[] cmdelement = null;
        for (String astr : str)
        {
            cmdelement = astr.split("=");
            if (cmdelement.length == 2)
                actioncmd.put(cmdelement[0].trim(), cmdelement[1].trim());
            else
                actioncmd.put(cmdelement[0].trim(), "null");
        }
        return actioncmd;
    }


    protected static HashMap<String, String> createActionMap(jswPanel apanel, MouseEvent e)
    {
        HashMap<String, String> action = new HashMap<String, String>();
        action.put("actiontype", "jswpanel action");
        action.put("source", e.getSource().getClass().getSimpleName());
        action.put("handlerclass", apanel.getClass().getSimpleName());
        action.put("handlername", apanel.getPanelname());
        action.put("commandstring", "mouseclick");
        return action;
    }

    public static HashMap<String, String> createActionMap(jswPanel apanel, ActionEvent e)
    {
        HashMap<String, String> action = new HashMap<String, String>();
        action.put("actiontype", "jswTable action");
        action.put("source", e.getSource().getClass().getSimpleName());
        action.put("handlerclass", apanel.getClass().getSimpleName());
        action.put("handlername", apanel.getPanelname());
        action.put("commandstring", "actionevent");
        action.put("command", e.getActionCommand());
        return action;
    }

    public static HashMap<String, String> createActionMap(jswPanel apanel, ChangeEvent e)
    {
        HashMap<String, String> action = new HashMap<String, String>();
        action.put("actiontype", "jswTable change");
        action.put("source", e.getSource().getClass().getSimpleName());
        action.put("handlerclass", apanel.getClass().getSimpleName());
        action.put("handlername", apanel.getPanelname());
        action.put("commandstring", "change event");
        return action;
    }

    public static HashMap<String, String> createActionMap(MySelectionListener apanel, TreeSelectionEvent e)
    {
        HashMap<String, String> action = new HashMap<String, String>();
        action.put("actiontype", "jswTable selection");
        action.put("source", e.getSource().getClass().getSimpleName());
        action.put("handlerclass", apanel.getClass().getSimpleName());
        action.put("handlername", e.getSource().getClass().getSimpleName());
        action.put("commandstring", "TreeSelection");
        return action;
    }


}
