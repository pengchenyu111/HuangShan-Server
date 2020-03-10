package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Admin;
import cn.hfut.huangshan.pojo.DB.DBAdmin;
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

    //根据id查询
    Admin getOneById(@Param("id") long id);

    //获取最大account
    String getMaxAccount();

    //增加一名管理员
    Integer addOne(DBAdmin dbAdmin);

    //删除一名
    Integer deleteOne(@Param("id")long id);

    //全更新一个
    Integer changeOne(DBAdmin dbAdmin);

    //局部更新一个
    Integer changeOnePart(DBAdmin dbAdmin);

    //更新头像
    Integer changeAdminHeadIcon(@Param("headIconUrl") String headIconUrl, @Param("account") String account);

    //更新联系电话
    Integer changePhone(@Param("account") String account, @Param("phone") String phone);

    //更新个人简介
    Integer changeIntroduction(@Param("account")String account, @Param("introduction") String introduction);

    //修改密码
    Integer changePassword(@Param("id")long id, @Param("password")String encode);
}
