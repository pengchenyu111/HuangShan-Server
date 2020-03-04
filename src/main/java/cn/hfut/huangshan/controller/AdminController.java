package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Admin;
import cn.hfut.huangshan.pojo.DB.DBAdmin;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.AdminService;
import cn.hfut.huangshan.utils.IdWorker;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
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
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObj getOneById(@PathVariable("id") long id){
        Admin admin = adminService.getOneById(id);
        if (admin != null){
            return ResponseUtil.success(admin);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }

    }

    /**
     * 增加一位管理员
     * 注意输入和输出的类不一样
     * @param dbAdmin
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultObj addOne(@RequestBody DBAdmin dbAdmin){
        IdWorker idWorker = new IdWorker(0,0);
        long id = idWorker.nextId();
        dbAdmin.setId(id);
        boolean isSuccess = adminService.addOne(dbAdmin);
        if (isSuccess){
            Admin admin = adminService.getOneById(id);
            return ResponseUtil.success(admin);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL,ErrorCode.ADD_FAIL_MSG,null);
        }
    }

    /**
     * 删除一名管理员
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("id") long id){
        boolean isSuccess = adminService.deleteOne(id);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL,ErrorCode.DELETE_FAIL_MSG,null);
        }
    }

    /**
     * 全更新一个
     * @param admin
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj changeOne(@RequestBody Admin admin, @PathVariable long id){
        boolean isSuccess = adminService.changeOne(admin);
        if (isSuccess){
            Admin updatedAdmin = adminService.getOneById(id);
            return ResponseUtil.success(updatedAdmin);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }

    }

    /**
     * 更换头像
     * @param account 账号
     * @param map 接受头像url，这里为什么不在url里直接加呢？因为headIconUrl有该死的http://.... 这样会解析错误
     * @return
     */
    @RequestMapping(value = "/head_icons/{account}",method = RequestMethod.PUT)
    public ResultObj changeAdminHeadIcon(@PathVariable("account") String account, @RequestBody Map<String,String> map){
        String headIconUrl = map.get("headIconUrl");
        boolean isChange = adminService.changeHeadIcon(headIconUrl,account);
        if (isChange){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }

    /**
     * 只更新联系电话
     * @param account 账号
     * @param map 电话
     * @return
     */
    @RequestMapping(value = "/phones/{account}",method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public ResultObj changePhone(@PathVariable("account") String account, @RequestBody Map<String,String> map){
        String phone = map.get("phone");
        boolean isChange = adminService.changePhone(account, phone);
        if (isChange){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }

    /**
     * 只更新个人简介
     * @param account 账号
     * @param map 个人简介
     * @return
     */
    @RequestMapping(value = "/introductions/{account}",method = RequestMethod.PUT)
    public ResultObj changeIntroduction(@PathVariable("account") String account, @RequestBody Map<String,String> map){
        String introduction = map.get("introduction");
        boolean isChange = adminService.changeIntroduction(account, introduction);
        if (isChange){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }

    /**
     * 修改密码
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "/passwords/{id}", method = RequestMethod.PUT)
    public ResultObj changePassword(@PathVariable("id") long id,  @RequestBody Map<String,String> map){
        //两次输入不一致等在前端校验
        String password = map.get("password");
        boolean isSuccess = adminService.changPassword(id,password);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }
}


