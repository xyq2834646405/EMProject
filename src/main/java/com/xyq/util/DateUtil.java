package com.xyq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getFormatDate(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static String getFormatDate(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
