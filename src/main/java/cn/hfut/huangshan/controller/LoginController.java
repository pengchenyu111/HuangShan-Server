package cn.hfut.huangshan.controller;


import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Admin;
import cn.hfut.huangshan.pojo.Tourist;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.AdminService;
import cn.hfut.huangshan.service.TouristService;
import cn.hfut.huangshan.utils.ResponseUtil;
import cn.hfut.huangshan.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 登录
 * @author PCY
 */
@RestController
public class LoginController {

    @Autowired
    AdminService adminService;
    @Autowired
    TouristService touristService;
    @Autowired
    TokenUtil tokenUtil;

    /**
     * 登录请求
     * @param map 接收账号和密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public ResultObj login(HttpServletRequest request, @RequestBody Map<String, String> map){
        String account = map.get("account");
        String password = map.get("password");
        Admin admin = adminService.adminLogin(account, password);
        if (admin != null){
            request.getSession().setAttribute("account",account);
            String token = tokenUtil.createToken(account);
            request.getSession().setAttribute("token",token);
            return ResponseUtil.login(ErrorCode.ADMIN_LOGIN,ErrorCode.ADMIN_LOGIN_MSG,admin);
        } else {
            Tourist tourist = touristService.touristLogin(account, password);
            if (tourist != null){
                request.getSession().setAttribute("account",account);
                String token = tokenUtil.createToken(account);
                request.getSession().setAttribute("token",token);
                return ResponseUtil.login(ErrorCode.TOURIST_LOGIN, ErrorCode.TOURIST_LOGIN_MSG, tourist);
            }else {
                return ResponseUtil.error(ErrorCode.LOGIN_DATA_WRONG, ErrorCode.LOGIN_DATA_WRONG_MSG,null);
            }
        }
    }

    /**
     * 系统管理员网页端登录请求
     * @param map 接收账号和密码
     * @return
     */
    @RequestMapping(value = "/systemAdmin/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultObj systemAdminLogin(HttpServletRequest request, @RequestBody Map<String, String> map) {
        String account = map.get("account");
        String password = map.get("password");
        Admin systemAdmin = adminService.adminLogin(account, password);
        if (systemAdmin != null && systemAdmin.getRoleName().equals("系统管理员")){
            request.getSession().setAttribute("account",account);
            String token = tokenUtil.createToken(account);
            request.getSession().setAttribute("token",token);
            return ResponseUtil.login(ErrorCode.ADMIN_LOGIN,ErrorCode.ADMIN_LOGIN_MSG,systemAdmin);
        } else {
            return ResponseUtil.login(ErrorCode.LOGIN_DATA_WRONG, ErrorCode.LOGIN_DATA_WRONG_MSG, null);
        }
    }

    /**
     * 注销登陆
     * @param request
     * @return
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultObj loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return ResponseUtil.success(null);
    }

}
