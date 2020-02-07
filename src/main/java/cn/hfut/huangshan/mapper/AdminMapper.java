package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员
 * @author pcy
 */

@Repository
@Mapper
public interface AdminMapper {

    //查询所有管理员
    List<Admin> getAllAdmins();
}
