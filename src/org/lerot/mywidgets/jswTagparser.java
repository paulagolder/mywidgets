package org.lerot.mywidgets;



public class jswTagparser
{
    private final int maxtags;
    int index;
    private String nexttoken;
    String[] tags;


    public jswTagparser(String tagstring)
    {
        tags = tagstring.split("\\s+");
        index = 0;
        maxtags = tags.length;
    }


    public String[] getToken()
    {
        if (maxtags == 0) return null;
        if (index > maxtags) return null;
        nexttoken = tags[index].trim();
        index++;

        if (nexttoken.equalsIgnoreCase("")) return null;
        if (nexttoken.contains("="))
        {
            String[] subtoken = nexttoken.split("=");
            return new String[]{subtoken[0].trim(), subtoken[1].trim()};
        } else
        {
            return new String[]{nexttoken, "1"};
        }
    }

    public boolean notfinished()
    {
        return maxtags != 0 && index <= maxtags - 1;
    }
}


