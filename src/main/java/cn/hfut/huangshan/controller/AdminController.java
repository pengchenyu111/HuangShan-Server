package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Admin;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.AdminService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员接口
 * @author pcy
 */
@RestController
@RequestMapping("admins")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 查询所有管理员
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResultObj getAllAdmins(){
        List<Admin> allAdmins = adminService.getAllAdmins();
        if (allAdmins.size() > 0){
            return ResponseUtil.success(allAdmins);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }
}
