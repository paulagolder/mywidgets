package org.lerot.mywidgets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class jswDate extends jswLabel
{

    public jswDate(String format)
    {
        super(format);
        Date now = new Date();
        String pattern = format;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(now);
        setText(date);
    }

    public jswDate(String date, String format)
    {
        super(date);
        // String sDate1="31/12/1998";
        try
        {
            Date ddate = new SimpleDateFormat(format).parse(date);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            String fdate = simpleDateFormat.format(ddate);
            setText(fdate);
        } catch (ParseException e)
        {
            setText(" format error " + date);
        }

    }
}
