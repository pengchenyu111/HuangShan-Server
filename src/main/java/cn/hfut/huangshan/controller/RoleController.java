package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Role;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.RoleService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色接口
 * @author pcy
 */
@RestController
@RequestMapping("roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    /**
     * 全查询
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultObj getAllRoles(){
        List<Role> roles = roleService.getAllRoles();
        if (roles.size() > 0) {
            return ResponseUtil.success(roles);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObj getById(@PathVariable("id") long id){
        Role role = roleService.getById(id);
        if (role != null){
            return ResponseUtil.success(role);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 根据角色名查询
     * 这里就不是restful风格了，因为中文夹在url里会产生错误
     * @param map
     * @return
     */
    @RequestMapping(value = "/names", method = RequestMethod.POST)
    public ResultObj getByRoleName(@RequestBody Map<String,String> map){
        String name = map.get("name");
        Role role = roleService.getByName(name);
        if (role != null){
            return ResponseUtil.success(role);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 修改一个
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj changeOne(@PathVariable("id") long id, @RequestBody Role role){
        boolean isSuccess = roleService.changeOne(role);
        if (isSuccess){
            Role updatedRole = roleService.getById(id);
            return ResponseUtil.success(updatedRole);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }
}
