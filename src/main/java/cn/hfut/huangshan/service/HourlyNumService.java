package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.HourlyNumMapper;
import cn.hfut.huangshan.pojo.HourlyNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 小时客流
 * @author pcy
 */
@Service
public class HourlyNumService {

    @Autowired
    HourlyNumMapper hourlyNumMapper;

    /**
     * 限制性全查询：降序前2000条
     * @return
     */
    public List<HourlyNum> getLimitAllHourlyNum(){
        List<HourlyNum> hourlyNums = hourlyNumMapper.getLimitAllHourlyNum();
        return hourlyNums;
    }

    /**
     * 按开始日期和结束日期来查询
     * @param startTime 开始日期
     * @param endTime 结束日期
     * @return
     */
    public List<HourlyNum> periodHourlyNum(String startTime,String endTime){
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
        List<HourlyNum> hourlyNums = hourlyNumMapper.periodHourlyNum(formatStartTime, formatEndTime);
        return hourlyNums;
    }

    /**
     * 根据日期查询某一天的
     * @param date
     * @return
     */
    public List<HourlyNum> getOneDayNum(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formatDate = format.format(date1);
        List<HourlyNum> hourlyNums = hourlyNumMapper.getOneDayNum(formatDate);
        return hourlyNums;
    }

    /**
     *
     * @param date
     * @param hour
     * @return
     */
    public HourlyNum getOneByDateHour(String date, String hour) {
        HourlyNum hourlyNum = hourlyNumMapper.getOneByDateHour(date,hour);
        return hourlyNum;
    }

    /**
     * 增加一个
     * @param hourlyNum
     * @return
     */
    @Transactional
    public boolean addOne(HourlyNum hourlyNum) {
        Integer rows = hourlyNumMapper.addOne(hourlyNum);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 批量增加
     * @param hourlyNums
     * @return
     */
    @Transactional
    public boolean addMany(List<HourlyNum> hourlyNums) {
        Integer rows = hourlyNumMapper.addMany(hourlyNums);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 更新一个
     * @param hourlyNum
     * @return
     */
    @Transactional
    public boolean updateOne(HourlyNum hourlyNum) {
        Integer rows = hourlyNumMapper.updateOne(hourlyNum);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 删除一个
     * @param date
     * @param hour
     * @return
     */
    @Transactional
    public boolean deleteOne(String date, String hour) {
        Integer rows = hourlyNumMapper.deleteOne(date, hour);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 删除一天二点全部
     * @param date
     * @return
     */
    public boolean deleteOneDayAll(String date) {
        Integer rows = hourlyNumMapper.deleteOneDayAll(date);
        if (rows > 0){
            return true;
        }
        return false;
    }
}
