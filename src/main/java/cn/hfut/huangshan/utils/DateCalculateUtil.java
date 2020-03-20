package cn.hfut.huangshan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateCalculateUtil {

    /**
     * 给定一个年份判断该年份是否为闰年
     */
    public static boolean isLeapYear(int year) {
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.isLeapYear(year);
    }

    /**
     * 利用SimpleDateFormat获取当前日期的字符串表示形式 格式：yyyy-MM-dd
     */
    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(System.currentTimeMillis());
    }

    /**
     * 给出任意一个年月日得到该天是星期几
     */
    public static String getWeek(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            Date d = dateFormat.parse(date);
            c.setTime(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int weekDay = c.get(Calendar.DAY_OF_WEEK)-1;
        String weekDayName = "";
        switch(weekDay){
            case 0 :
                weekDayName = "星期日";
                break;
            case 1 :
                weekDayName = "星期一";
                break;
            case 2 :
                weekDayName = "星期二";
                break;
            case 3 :
                weekDayName = "星期三";
                break;
            case 4 :
                weekDayName = "星期四";
                break;
            case 5 :
                weekDayName = "星期五";
                break;
            case 6 :
                weekDayName = "星期六";
        }
        return weekDayName;
    }

    /**
     * 获得距离指定日期n天的那一天的日期
     */
    public static String getDistanceDay(String date, int day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        Calendar c =Calendar.getInstance();
        try {
            d = dateFormat.parse(date);
            c.setTime(d);
            c.add(Calendar.DATE, day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat.format(c.getTime());
    }

    public static Calendar buildCalendar(String formatDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(formatDate);
            Calendar cal=Calendar.getInstance();
            cal.setTime(date);
            return cal;
        } catch (Exception e){
            e.printStackTrace();
        }
        return Calendar.getInstance();
    }

//    public static int getMonth(String formatDate){
//
//    }
}
