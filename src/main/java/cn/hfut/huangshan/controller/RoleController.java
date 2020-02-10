package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Role;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.RoleService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
