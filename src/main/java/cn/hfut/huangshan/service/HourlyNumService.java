package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.HourlyNumMapper;
import cn.hfut.huangshan.pojo.HourlyNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    @Transactional
    public List<HourlyNum> periodHourlyNum(String startTime,String endTime){
        List<HourlyNum> hourlyNums = hourlyNumMapper.periodHourlyNum(startTime, endTime);
        return hourlyNums;
    }
}
