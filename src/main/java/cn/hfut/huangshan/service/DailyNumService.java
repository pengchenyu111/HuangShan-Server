package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.DailyNumMapper;
import cn.hfut.huangshan.pojo.DailyNum;
import cn.hfut.huangshan.utils.DailyNumPredictUtil;
import cn.hfut.huangshan.utils.DateCalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 一天数据
 * @author pcy
 */
@Service
public class DailyNumService {

    @Autowired
    DailyNumMapper dailyNumMapper;

    /**
     * 限制性全查询：降序前1000条
     * @return
     */
    @Transactional
    public List<DailyNum> getLimitAllDailyNum(){
        List<DailyNum> dailyNums = dailyNumMapper.getLimitAllDailyNum();
        return dailyNums;
    }

    /**
     * 按开始日期和结束日期查询
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @return
     */
    @Transactional
    public List<DailyNum> periodDailyNum(String startTime,String endTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(startTime);
            date2 = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatStartTime = format.format(date1);
        String formatEndTime = format.format(date2);
        List<DailyNum> dailyNums = dailyNumMapper.periodDailyNum(formatStartTime,formatEndTime);
        return dailyNums;
    }

    /**
     * 根据日期查询某一天的
     * @param date
     * @return
     */
    public DailyNum getOneByDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatDate = format.format(date1);
        DailyNum dailyNum = dailyNumMapper.getOneByDate(formatDate);
        return dailyNum;
    }

    /**
     * 查询同一假期相对同一天
     * @param dailyNum
     * @return
     */
    public List<DailyNum> getHolidayDailyNum(DailyNum dailyNum){
        return dailyNumMapper.getHolidayDailyNum(dailyNum);
    }

    /**
     * 预测函数
     * @param formatDate yyyy-MM-dd
     * @param weatherName 详情见 WeatherUtil
     * @param holidayName 是假期传假期名，非假期传null
     * @param holidayOrder 是假期传假期第几天，非假期传0即可
     * @return
     */
    public int dailyNumPredict(String formatDate, String weatherName, String holidayName, int holidayOrder){
        if(holidayName == null){
            return normalNumPredict(formatDate, weatherName);
        }else{
            return holidayNumPredict(formatDate, holidayName, holidayOrder, weatherName);
        }
    }

    /**
     * 平常日预测
     * @param formatDate
     * @param weather
     * @return
     */
    public int normalNumPredict(String formatDate, String weather){

        int weekday = DateCalculateUtil.getWeek(formatDate);

        Calendar predictCalendar = DateCalculateUtil.buildCalendar(formatDate);
        int predictYear = predictCalendar.get(Calendar.YEAR);
        int predictMonth = predictCalendar.get(Calendar.MONTH) + 1;
        int predictDate = predictCalendar.get(Calendar.DATE);

        // 前一天数据
        DailyNum yesterdayNum = getDailyNum(formatDate, -1);

        // 最近同星期非假期数据
        DailyNum lastWeekNum = getDailyNum(formatDate, -7);
        int count = 2;
        while(lastWeekNum.getHolidayName() != null){
            lastWeekNum = getDailyNum(formatDate, -7 * (count++));
        }

        if(predictMonth != 7 && predictMonth != 8){
            if(weekday == 1){
                return DailyNumPredictUtil.calNormalDailyNum(weather, yesterdayNum.getTodayTotalNum() * 6 / 10, yesterdayNum.getWeatherName(), lastWeekNum.getTodayTotalNum(), lastWeekNum.getWeatherName());
            }else if(weekday == 6){
                return DailyNumPredictUtil.calNormalDailyNum(weather, yesterdayNum.getTodayTotalNum() * 15 / 10, yesterdayNum.getWeatherName(), lastWeekNum.getTodayTotalNum(), lastWeekNum.getWeatherName());
            }else{
                return DailyNumPredictUtil.calNormalDailyNum(weather, yesterdayNum.getTodayTotalNum() * 11 / 10, yesterdayNum.getWeatherName(), lastWeekNum.getTodayTotalNum(), lastWeekNum.getWeatherName());
            }
        }else{
            if(weekday == 1){
                return DailyNumPredictUtil.calNormalDailyNum(weather, yesterdayNum.getTodayTotalNum() * 8 / 10, yesterdayNum.getWeatherName(), lastWeekNum.getTodayTotalNum(), lastWeekNum.getWeatherName());
            }else if(weekday == 6){
                return DailyNumPredictUtil.calNormalDailyNum(weather, yesterdayNum.getTodayTotalNum() * 15 / 10, yesterdayNum.getWeatherName(), lastWeekNum.getTodayTotalNum(), lastWeekNum.getWeatherName());
            }else{
                return DailyNumPredictUtil.calNormalDailyNum(weather, yesterdayNum.getTodayTotalNum() * 11 / 10, yesterdayNum.getWeatherName(), lastWeekNum.getTodayTotalNum(), lastWeekNum.getWeatherName());
            }
        }
    }

    /**
     * 假期日预测
     * @param formatDate
     * @param holidayName
     * @param holidayOrder
     * @param weather
     * @return
     */
    public int holidayNumPredict(String formatDate, String holidayName, int holidayOrder, String weather){
        // 前一天数据
        DailyNum yesterdayNum = getDailyNum(formatDate, -1);

        DailyNum dailyNum = new DailyNum();
        dailyNum.setHolidayName(holidayName);
        dailyNum.setHolidayOrder(holidayOrder);
        // 近年同假期同天dailyNum
        List<DailyNum> holidayDailyNums = dailyNumMapper.getHolidayDailyNum(dailyNum);

        return DailyNumPredictUtil.calHolidayDailyNum(weather, holidayDailyNums, yesterdayNum);
    }

    /**
     * 通过日期、位移找到新的日期
     * @param formatDate
     * @param index
     * @return
     */
    public DailyNum getDailyNum(String formatDate, int index){
        Calendar calendar = DateCalculateUtil.buildCalendar(formatDate);
        calendar.add(Calendar.DATE, index);
        String indexDate = DateCalculateUtil.getFormatTime(calendar);
        return dailyNumMapper.getOneByDate(indexDate);
    }

}
