package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Admin;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.AdminService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    /**
     * 更换头像
     * @param account 账号
     * @param map 接受头像url，这里为什么不在url里直接加呢？因为headIconUrl有该死的http://.... 这样会解析错误
     * @return
     */
    @RequestMapping(value = "head_icons/{account}",method = RequestMethod.PUT)
    public ResultObj changeAdminHeadIcon(@PathVariable("account") String account, @RequestBody Map<String,String> map){
        String headIconUrl = map.get("headIconUrl");
        boolean isChange = adminService.changeHeadIcon(headIconUrl,account);
        if (isChange){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }
}
