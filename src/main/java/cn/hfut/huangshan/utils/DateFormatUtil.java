package cn.hfut.huangshan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    /**
     * 转化成日期+时间的格式： yyyy-MM-dd HH:mm:ss
     * @param dateTime
     * @return
     */
    public static String toDateTime(String dateTime){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formatDateTime = null;
        try {
            formatDateTime = simpleDateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String format = simpleDateFormat.format(formatDateTime);
        return format;
    }

    /**
     * 转化成日期的格式： yyyy-MM-dd
     * @param date
     * @return
     */
    public static String toDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date formatDate = null;
        try {
            formatDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String format = simpleDateFormat.format(formatDate);
        return format;
    }
}
