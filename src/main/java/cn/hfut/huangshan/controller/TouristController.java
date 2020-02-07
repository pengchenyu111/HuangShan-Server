package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Tourist;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.TouristService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 游客接口
 * @author pcy
 */
@RestController
@RequestMapping("tourist")
public class TouristController {

    @Autowired
    TouristService touristService;

    /**
     * 查询所有游客
     * @return
     */
    @GetMapping("/all")
    public ResultObj getAllTourists(){
        List<Tourist> tourists = touristService.getAllTourists();
        if (tourists.size() > 0){
            return ResponseUtil.success(tourists);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_NULL,ErrorCode.QUERY_NULL_MSG,null);
        }
    }
}
