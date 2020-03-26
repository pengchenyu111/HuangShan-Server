package cn.hfut.huangshan.utilTest;

import cn.hfut.huangshan.mapper.DailyNumMapper;
import cn.hfut.huangshan.pojo.DailyNum;
import cn.hfut.huangshan.service.DailyNumService;
import cn.hfut.huangshan.utils.DailyNumPredictUtil;
import cn.hfut.huangshan.utils.DateCalculateUtil;
import cn.hfut.huangshan.utils.WeatherUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class DateCalculateUtilTest {

    @Autowired
    DailyNumService dailyNumService;

    @Test
    void getWeekday() {
        System.out.print(DateCalculateUtil.getWeek("2020-03-22"));
        System.out.print(DateCalculateUtil.getWeek("2020-03-23"));
    }

    @Test
    void getCurrentDate() {
        System.out.print(DateCalculateUtil.getCurrentDate());
    }

    @Test
    void getDate() {
        System.out.print(DateCalculateUtil.getDistanceDay("2020-03-20", -1));
    }

    @Test
    public void getAllWeather() {
        List<DailyNum> dailyNums = dailyNumService.periodDailyNum("2016-8-23", "2019-11-20");
        Set<String> weatherSets = new HashSet<>();
        for(int i=0;i<dailyNums.size();i++){
            weatherSets.add(dailyNums.get(i).getWeatherName());
        }
        Iterator it = weatherSets.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
    }

    @Test
    public void weatherEffectTest() {
        System.out.print(WeatherUtil.weatherEffect("晴", "大雨"));
    }

    @Test
    public void parseIntTest() {
        int month = Integer.parseInt("05");
        System.out.print(month);
    }

    @Test
    public void subStrTest() {
        String date = "2019-05-21";
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        System.out.print(year + ":" + month + ":" + day);
    }

    @Test
    public void testCalendar(){
        Calendar predictCalendar = DateCalculateUtil.buildCalendar("2019-08-23");
        System.out.print(predictCalendar.get(Calendar.YEAR) + ":" + ( predictCalendar.get(Calendar.MONTH) + 1 ) + ":" + predictCalendar.get(Calendar.DATE));
    }

    @Test
    public void testDailyNumPredict() {
        System.out.print(dailyNumService.dailyNumPredict("2000-01-01", "大雨", null, 0));
    }

    @Test
    public void testYesterdayDate(){
        System.out.print(dailyNumService.normalNumPredict("2019-03-20", "雷阵雨"));
    }

    @Test
    public void getHolidayDailyNumTest(){
        DailyNum dailyNum = new DailyNum();
        dailyNum.setHolidayName("国庆节");
        dailyNum.setHolidayOrder(1);
        List<DailyNum> dailyNums = dailyNumService.getHolidayDailyNum(dailyNum);
        for (DailyNum num : dailyNums) {
            System.out.println(num.toString());
        }
    }

    @Test
    public void getHolidayPredictTest(){
        System.out.println("2019-10-01 " + dailyNumService.dailyNumPredict("2019-10-01", "小雨", "国庆节", 1));
        System.out.println("2019-10-02 " + dailyNumService.dailyNumPredict("2019-10-02", "多云", "国庆节", 2));
        System.out.println("2019-10-03 " + dailyNumService.dailyNumPredict("2019-10-03", "晴", "国庆节", 3));
        System.out.println("2019-10-04 " + dailyNumService.dailyNumPredict("2019-10-04", "晴", "国庆节", 4));
        System.out.println("2019-10-05 " + dailyNumService.dailyNumPredict("2019-10-05", "阴", "国庆节", 5));
        System.out.println("2019-10-06 " + dailyNumService.dailyNumPredict("2019-10-06", "多云", "国庆节", 6));
        System.out.println("2019-10-07 " + dailyNumService.dailyNumPredict("2019-10-07", "阴", "国庆节", 7));
        System.out.println("2019-10-08 " + dailyNumService.dailyNumPredict("2019-04-20", "雷阵雨", null, 1));
    }

    @Test
    public void normalDailyDayTest(){
        System.out.println("2019-07-15 " + dailyNumService.dailyNumPredict("2019-07-15", "多云", null, 4));
        System.out.println("2019-07-16 " + dailyNumService.dailyNumPredict("2019-07-16", "雷阵雨", null, 5));
        System.out.println("2019-07-17 " + dailyNumService.dailyNumPredict("2019-07-17", "中雨", null, 6));
        System.out.println("2019-07-18 " + dailyNumService.dailyNumPredict("2019-07-18", "多云", null, 6));
        System.out.println("2019-07-19 " + dailyNumService.dailyNumPredict("2019-07-19", "多云", null, 1));
        System.out.println("2019-07-20 " + dailyNumService.dailyNumPredict("2019-07-20", "多云", null, 2));
        System.out.println("2019-07-21 " + dailyNumService.dailyNumPredict("2019-07-21", "小雨", null, 3));
    }
}
