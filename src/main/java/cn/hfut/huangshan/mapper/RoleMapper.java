package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色
 * @author pcy
 */
@Repository
@Mapper
public interface RoleMapper {

    //全查询
    List<Role> getAllRoles();

    //根据id查询
    Role getById(@Param("id") long id);

    //根据名字查询
    Role getByName(@Param("name") String name);

    //修改一个
    Integer changeOne(Role role);
}
