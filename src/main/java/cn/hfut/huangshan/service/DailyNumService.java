package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.DailyNumMapper;
import cn.hfut.huangshan.pojo.DailyNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<DailyNum> dailyNums = dailyNumMapper.periodDailyNum(startTime,endTime);
        return dailyNums;
    }

}
