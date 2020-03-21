package cn.hfut.huangshan.utils;


import cn.hfut.huangshan.pojo.DailyNum;
import java.util.List;


public class DailyNumPredictUtil {

    public static int calNormalDailyNum(String preWeather, int yesterdayNum, String yesWeather, int lastWeekNum, String lastWeather){
        double yesNum = yesterdayNum * WeatherUtil.weatherEffect(preWeather, yesWeather);
        double lastNum = lastWeekNum * WeatherUtil.weatherEffect(preWeather, lastWeather);
        return (int) (yesNum * 0.6 + lastNum * 0.4);
    }

    public static int calHolidayDailyNum(String preWeather, List<DailyNum> holidayDailyNums, DailyNum yesterdayNum){
        double sum = 0;
        for (DailyNum dailyNum : holidayDailyNums) {
            sum += dailyNum.getTodayTotalNum() * WeatherUtil.weatherEffect(preWeather, dailyNum.getWeatherName());
        }
        sum /= holidayDailyNums.size();
        double yesterdayEffect = yesterdayNum.getTodayTotalNum() * WeatherUtil.weatherEffect(preWeather, yesterdayNum.getWeatherName());
        return (int) (sum * 0.8 + yesterdayEffect * 0.2);
    }



}
