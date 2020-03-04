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

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Role getById(long id) {
        Role role = roleMapper.getById(id);
        return role;
    }

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    public Role getByName(String name) {
        Role role = roleMapper.getByName(name);
        return role;
    }

    /**
     * 修改一个
     * @param role
     * @return
     */
    @Transactional
    public boolean changeOne(Role role) {
        Integer rows = roleMapper.changeOne(role);
        if (rows > 0){
            return true;
        }
        return false;
    }
}
