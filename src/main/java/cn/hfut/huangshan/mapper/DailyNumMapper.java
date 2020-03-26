package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.DailyNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
@Mapper
public interface DailyNumMapper {

    //限制性全查询：降序前1000条
    List<DailyNum> getLimitAllDailyNum();

    //按开始日期和结束日期查询
    List<DailyNum> periodDailyNum(@Param("startTime") String startTime,@Param("endTime")String endTime);

    //根据日期查询某一天的
    DailyNum getOneByDate(@Param("date") String date);

    //查询同一假期相对同一天
    List<DailyNum> getHolidayDailyNum(DailyNum dailyNum);

    //插入一个
    Integer addOneDayNum(DailyNum dailyNum);

    //更新一个
    Integer updateOne(DailyNum dailyNum);

    //删除一个
    Integer deleteOne(@Param("date") String date);
}
