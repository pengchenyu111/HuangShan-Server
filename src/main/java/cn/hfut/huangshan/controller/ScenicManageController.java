package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.AdminScenicManage;
import cn.hfut.huangshan.pojo.ScenicManage;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.ScenicManageService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员管理景点接口
 * @author pcy
 */
@RestController
@RequestMapping("scenic_manages")
public class ScenicManageController {

    @Autowired
    ScenicManageService scenicManageService;

    /**
     * 全查询
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultObj getAllScenicManage(){
        List<ScenicManage> scenicManages = scenicManageService.getAllScenicManage();
        if (scenicManages.size() > 0){
            return ResponseUtil.success(scenicManages);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 查询某个管理员管理的景点
     * @param adminId 管理员id
     * @return
     */
    @RequestMapping(value = "/ones/{adminId}",method = RequestMethod.GET)
    public ResultObj getOnesManages(@PathVariable("adminId") int adminId){
        List<AdminScenicManage> onesManages = scenicManageService.getOnesManages(adminId);
        if (onesManages.size() > 0){
            return ResponseUtil.success(onesManages);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }
}
