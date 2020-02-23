package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    //根据账号密码查询管理员
    Admin adminLogin(@Param("account") String account, @Param("password") String password);

    //更新头像
    Integer changeAdminHeadIcon(@Param("headIconUrl") String headIconUrl, @Param("account") String account);

    //更新联系电话
    Integer changePhone(@Param("account") String account, @Param("phone") String phone);

    //更新个人简介
    Integer changeIntroduction(@Param("account")String account, @Param("introduction") String introduction);
}
