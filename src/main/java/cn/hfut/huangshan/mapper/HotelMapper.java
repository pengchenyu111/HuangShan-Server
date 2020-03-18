package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HotelMapper {

    //全查询
    List<Hotel> getAll();

    //根据id查询
    Hotel getById(@Param("id") long id);

    //根据id删除一个
    Integer deleteOne(@Param("id") long id);

    //增加一个
    Integer addOne(Hotel hotel);

    //更新一个
    Integer updateOne(Hotel hotel);
}
