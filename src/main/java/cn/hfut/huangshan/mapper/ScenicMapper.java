package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Scenic;
import org.apache.ibatis.annotations.Mapper;
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
}
