package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.HourlyNum;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.HourlyNumService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 小时数据
 * @author pcy
 */
@RestController
@RequestMapping("hourlyNum")
public class HourlyNumController {

    @Autowired
    HourlyNumService hourlyNumService;

    /**
     * 限制性全查询：降序前2000条
     * @return
     */
    @GetMapping("/limitAll")
    public ResultObj getLimitAllHourlyNum(){
        List<HourlyNum> hourlyNums = hourlyNumService.getLimitAllHourlyNum();
        if (hourlyNums.size() > 0){
            return ResponseUtil.success(hourlyNums);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 按开始日期和结束日期来查询
     * @param map 开始日期和结束日期
     * @return
     */
    @PostMapping("/period")
    public ResultObj periodHourlyNum(@RequestBody Map<String,String> map){
        String startTime = map.get("startTime");
        String endTime = map.get("endTime");
        List<HourlyNum> hourlyNums = hourlyNumService.periodHourlyNum(startTime, endTime);
        if (hourlyNums.size() > 0){
            return ResponseUtil.success(hourlyNums);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }
}
