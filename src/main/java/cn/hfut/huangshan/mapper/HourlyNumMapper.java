package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.HourlyNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 小时客流
 * @author pcy
 */
@Repository
@Mapper
public interface HourlyNumMapper {

    //限制性全查询：降序前2000条
    List<HourlyNum> getLimitAllHourlyNum();

    //按开始日期和结束日期来查询
    List<HourlyNum> periodHourlyNum(@Param("startTime") String startTime, @Param("endTime") String endTime);

    //根据日期查询某一天的
    List<HourlyNum> getOneDayNum(@Param("date") String date);
}
