package com.tamdt.pmbackend.common.ulti;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUltils {
    public static Date convertStringToDate(String strDate, String formatDate) throws ParseException {
        if (StringUtils.isNullOrEmpty(strDate)) {
            return null;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(formatDate);

            Date date = null;
            if (strDate.contains("T")) {
                date = formatter.parse(strDate.replaceAll("Z$", "+0000"));
            } else {
                date = formatter.parse(strDate);
            }
            return date;
        }
    }

    public static String convertDateToString(Date date, String formatDate){
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        return dateFormat.format(date);
    }

}
