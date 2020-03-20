package cn.hfut.huangshan.utils;


import java.util.Calendar;

public class DailyNumPredictUtil {

    public static int dailyNumPredict(String formatDate, String weatherName, String holidayName, String holidayOrder){
        String weekDayName = DateCalculateUtil.getWeek(formatDate);
        Calendar predictCalendar = DateCalculateUtil.buildCalendar(formatDate);
        int predictYear = predictCalendar.get(Calendar.YEAR);
        int predictMonth = predictCalendar.get(Calendar.MONTH) + 1;
        int predictDate = predictCalendar.get(Calendar.DATE);
        if(holidayName == null){
            return predictDate + predictMonth + predictYear;
        }
        return 1;
    }

}
