package org.lerot.mywidgets;

import java.util.Date;

public class jswDate extends jswLabel
{

    public jswDate(String inLabel)
    {
        super(inLabel);
        Date now = new Date();
        setText(now.toString());
    }
}
