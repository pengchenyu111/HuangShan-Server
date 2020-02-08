package cn.hfut.huangshan.mapper;

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
}
