package org.lerot.mywidgets;

import java.util.HashMap;

public class jswUtils
{
    public static HashMap<String, String> parsecsvstring(String text)
    {
        HashMap<String, String> cmdmap = new HashMap<String, String>();
        String text2 = text.replace("{", "");
        String text3 = text2.replace("}", "");
        String[] cmdarray = text3.split(",");
        for (String acmd : cmdarray)
        {
            String[] cmdpair = acmd.split("=");
            if( cmdpair.length< 2 )
            {
                cmdmap.put(cmdpair[0].trim(), " ");
            }
            else
             cmdmap.put(cmdpair[0].trim(), cmdpair[1].trim());
        }
        return cmdmap;
    }
}
