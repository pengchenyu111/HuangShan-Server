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
    public static int getWeek(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            Date d = dateFormat.parse(date);
            c.setTime(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_WEEK)-1;
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

    /**
     * 规范时间字符串转日历对象
     * @param formatDate
     * @return
     */

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

    /**
     * 年月日生成日历对象
     * @param year
     * @param month
     * @param date
     * @return
     */

    public static Calendar buildCalendarFromYMD(int year, int month, int date){
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, date);
        return c;
    }

    /**
     * 日历类转时间字符串
     * @param calendar
     * @return
     */
    public static String getFormatTime(Calendar calendar){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatTime = sdf.format(calendar.getTime());
        return formatTime;
    }
}
