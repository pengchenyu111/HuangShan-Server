package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.DB.DBTourist;
import cn.hfut.huangshan.pojo.Tourist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 游客
 * @author pcy
 */
@Repository
@Mapper
public interface TouristMapper {

    //查询所有游客
    List<Tourist> getAllTourists();

    //根据账号密码查询游客
    Tourist touristLogin(@Param("account") String account, @Param("password") String password);

    //根据id查询
    Tourist getById(@Param("id") long id);

    //增加一名
    Integer addOne(DBTourist dbTourist);

    //更新一个
    Integer updateOne(DBTourist dbTourist);

    //修改密码
    Integer updatePassword(@Param("id") long id, @Param("password") String encode);

    //删除一个
    Integer deleteOne(@Param("id")long id);

    //修改头像
    Integer changeHeadIcon(@Param("id")long id, @Param("headIcon")String headIconUrl);
}
