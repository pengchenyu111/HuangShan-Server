package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.RoleMapper;
import cn.hfut.huangshan.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色
 * @author pcy
 */
@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    /**
     * 全查询
     * @return
     */
    @Transactional
    public List<Role> getAllRoles(){
        List<Role> roles = roleMapper.getAllRoles();
        return roles;
    }
}
