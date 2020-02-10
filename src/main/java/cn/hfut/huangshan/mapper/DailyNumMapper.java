package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.DailyNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DailyNumMapper {

    //限制性全查询：降序前1000条
    List<DailyNum> getLimitAllDailyNum();

    //按开始日期和结束日期查询
    List<DailyNum> periodDailyNum(@Param("startTime") String startTime,@Param("endTime")String endTime);
}
