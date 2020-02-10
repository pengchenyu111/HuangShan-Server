package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
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
}
