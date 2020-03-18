package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Scenic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 景点
 * @author pcy
 */
@Repository
@Mapper
public interface ScenicMapper {

    //全查询
    List<Scenic> getAllScenes();

    //按id查询
    Scenic getById(@Param("id") long id);

    //按id删除
    Integer deleteOne(@Param("id") long id);

    //增加一个
    Integer addOne(Scenic scenic);

    //更新一个
    Integer updateOne(Scenic scenic);
}
