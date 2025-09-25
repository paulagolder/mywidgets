package org.lerot.mywidgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

//import static org.odftoolkit.odfdom.dom.attribute.style.StyleNumFormatAttribute.Value.i;

public abstract class jswPanel extends JPanel  //implements ActionListener
{
    private static final long serialVersionUID = 1L;
    public boolean trace;
    private String panelname;
    private final int setHeight = 0;
    private final int setWidth = 0;
    protected jswStyle style;
    protected Insets padding = new Insets(1, 1, 1, 1);
    // ActionListener actionlistener = null;
    private String tag = "";
    private String marker = "";

    public jswPanel(String name)
    {
        panelname = name;
        tag = name;
        initialiseStyle(this.getClass().getSimpleName());
        //applyStyle();
    }

    public jswPanel(int count)
    {
        this(String.valueOf(count));
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

    public static Map<String, String> parseActionCommand(String cmd)
    {
        Map<String, String> actioncmd = new HashMap<String, String>();
        if (cmd.startsWith("{"))
        {
            cmd = cmd.replace("{", " ");
            cmd = cmd.replace("}", "");
            String[] str = cmd.split(",");
            String[] cmdelement = null;
            for (String astr : str)
            {
                cmdelement = astr.split("=");
                if (cmdelement.length == 2)
                    actioncmd.put(cmdelement[0].trim(), cmdelement[1].trim());
                else
                    actioncmd.put(cmdelement[0].trim(), "null");
            }
        } else
            actioncmd.put("command", cmd);
        return actioncmd;
    }

    protected static HashMap<String, String> createActionMap(jswPanel apanel, MouseEvent e)
    {
        HashMap<String, String> action = createBaseActionMap(apanel, e);
        action.put("actiontype", "mouseevent");
        action.put("command", "mouseclick");
        if (apanel.getTag() != null)
        {
            action.put("sourcetag", apanel.getTag());
        }
        return action;
    }

    public static HashMap<String, String> createActionMap(jswPanel apanel, ActionEvent e)
    {
        // //System.out.println( "in camap "+e.getActionCommand());
        HashMap<String, String> action = createBaseActionMap(apanel, e);
        action.put("actiontype", "ActionEvent");
        return action;
    }

    public static HashMap<String, String> createActionMap(jswPanel apanel, jswActionEvent e)
    {
        HashMap<String, String> action = createBaseActionMap(apanel, e);
        return action;
    }

    public static HashMap<String, String> createActionMap(jswPanel apanel, ChangeEvent e)
    {
        HashMap<String, String> action = createBaseActionMap(apanel, e);
        action.put("actiontype", "ChangeEvent");
        return action;
    }

    public static HashMap<String, String> createActionMap(TreeSelectionListener apanel, TreeSelectionEvent e)
    {
        HashMap<String, String> action = new HashMap<String, String>();
        action.put("actiontype", "TreeSelection");
        return action;
    }

    public static HashMap<String, String> createBaseActionMap(jswPanel apanel, EventObject e)
    {
        // //System.out.println( "in camap "+e.getActionCommand());
        HashMap<String, String> action = new HashMap<String, String>();
        action.put("handlerclass", apanel.getClass().getSimpleName());
        action.put("handlertag", apanel.getTag());
        if (e.getSource() instanceof jswPanel)
        {
            action.put("sourcetag", ((jswPanel) e.getSource()).getTag());
            action.put("sourcename", ((jswPanel) e.getSource()).getPanelname());
        }
        return action;
    }

    public boolean isTrace()
    {
        return trace;
    }

    public void setTrace(boolean trace)
    {
        this.trace = trace;
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

    public void setPadding(int in)
    {
        padding = new Insets(in, in, in, in);
    }

    public void setPadding(String instr)
    {
        padding = makePadding(instr);
    }

    private void setPadding(Insets borderInsets)
    {
        padding = borderInsets;
    }

    public void setPadding()
    {
        setPadding(5);
    }

    public void setPadding(int top, int left, int bottom, int right)
    {
        padding = new Insets(top, left, bottom, right);
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

    public void setPanelname(String panelname)
    {
        this.panelname = panelname;
    }

    public String getTag()
    {
        return tag;
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

  /*  public void actionPerformed(ActionEvent e)
    {
        Long t = System.currentTimeMillis() / 10000;
        int uniqueId = t.intValue();
        HashMap<String, String> am = jswPanel.createActionMap(this, e);
        am.put("panelhandled", this.getPanelname());
        ActionEvent event = new ActionEvent(this, uniqueId, am.toString());
        actionlistener.actionPerformed(event);
    }

    public static Map<String, String> parseActionCommand(String cmd)
    {
        Map<String, String> actioncmd = new HashMap<String, String>();
        if(cmd.startsWith("{"))
        {
            cmd = cmd.replace("{", " ");
            cmd = cmd.replace("}", "");
            String[] str = cmd.split(",");

            String[] cmdelement = null;
            for (String astr : str)
            {
                cmdelement = astr.split("=");
                if (cmdelement.length == 2)
                    actioncmd.put(cmdelement[0].trim(), cmdelement[1].trim());
                else
                    actioncmd.put(cmdelement[0].trim(), "null");
            }
        }
        else
            actioncmd.put("command",cmd);

        return actioncmd;
    }

    protected static HashMap<String, String> createActionMap(jswPanel apanel, MouseEvent e)
    {
        HashMap<String, String> action =  createBaseActionMap(apanel,e);
        action.put("actiontype", "jswpanel mouseevent");
        action.put("commandstring", "mouseclick");
        return action;
    }

    public static HashMap<String, String> createActionMap(jswPanel apanel, ActionEvent e)
    {
        HashMap<String, String> action =  createBaseActionMap(apanel,e);
        action.put("actiontype", "actionevent");
        action.put("commandstring", "actionevent");
        action.put( e.getSource().getClass().getSimpleName(),e.getSource().getClass().getSimpleName());
        String cmd =   e.getActionCommand();
        if(cmd.contains("{"))
        {
            Map<String, String> cmdmap = parseActionCommand(cmd);
            for (Map.Entry<String, String> entry : cmdmap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                action.put(key,value);
            }
            action.put("command","action");
        }else
        {
            action.put("command", cmd);
        }
        //action.put("commandx",  e.getActionCommand());
        return action;
    }

    public static HashMap<String, String> createActionMap(jswPanel apanel, ChangeEvent e)
    {
        HashMap<String, String> action =  createBaseActionMap(apanel,e);
        action.put("actiontype", "jswpanel  changeevent");
        action.put("commandstring", "change event");
        return action;
    }

    public static HashMap<String, String> createBaseActionMap(jswPanel apanel, EventObject e)
    {
        HashMap<String, String> action = new HashMap<String, String>();
        action.put("source", e.getSource().getClass().getSimpleName());
        action.put("handlerclass", apanel.getClass().getSimpleName());
        action.put("handlername", e.getSource().getClass().getSimpleName());
        action.put( e.getSource().getClass().getSimpleName(),e.getSource().getClass().getSimpleName());
        if(e.getSource() instanceof  jswPanel)
        {
            action.put("sourcetag", ((jswPanel) e.getSource()).getTag());
        }

        return action;
    }

   public static HashMap<String, String> createActionMap(MySelectionListener apanel, TreeSelectionEvent e)
    {

        HashMap<String, String> action = new HashMap<String, String>();
        action.put("source", e.getSource().getClass().getSimpleName());
        action.put("handlerclass", apanel.getClass().getSimpleName());
        action.put("handlername", e.getSource().getClass().getSimpleName());
        action.put( e.getSource().getClass().getSimpleName(),e.getSource().getClass().getSimpleName());
        if(e.getSource() instanceof  jswPanel)
        {
            action.put("sourcetag", ((jswPanel) e.getSource()).getTag());
        }
            action.put("actiontype", "jswpanel  selection");
            action.put("commandstring", "TreeSelection");
            return action;

    }

}*/

}
