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
        System.out.print(DailyNumPredictUtil.dailyNumPredict("2000-01-01", "大雨", null, null));
    }
}
