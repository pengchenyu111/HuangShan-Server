package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Scenic;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.ScenicService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 景点
 * @author pcy
 */
@RestController
@RequestMapping("scenics")
public class ScenicController {

    @Autowired
    ScenicService scenicService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultObj getAllScenes(){
        List<Scenic> scenes = scenicService.getAllScenes();
        if (scenes.size() > 0) {
            return ResponseUtil.success(scenes);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }
}
