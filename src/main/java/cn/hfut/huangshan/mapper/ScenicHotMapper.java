package cn.hfut.huangshan.mapper;


import cn.hfut.huangshan.pojo.ScenicHot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ScenicHotMapper {

    //全查询
    List<ScenicHot> getAll();

    //按景点id查询
    ScenicHot getByScenicId(@Param("id") long id);

    //删除一个景点的实时热度
    Integer deleteOne(@Param("id") long id);

    //增加一个
    Integer addOne(ScenicHot scenicHot);

    //更新一个
    Integer updateOne(ScenicHot scenicHot);
}
