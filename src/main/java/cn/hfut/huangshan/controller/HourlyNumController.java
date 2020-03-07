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
@RequestMapping("hourly_nums")
public class HourlyNumController {

    @Autowired
    HourlyNumService hourlyNumService;

    /**
     * 限制性全查询：降序前2000条
     * @return
     */
    @RequestMapping(value = "/limit_all", method = RequestMethod.GET)
    public ResultObj getLimitAllHourlyNum(){
        List<HourlyNum> hourlyNums = hourlyNumService.getLimitAllHourlyNum();
        if (hourlyNums.size() > 0){
            return ResponseUtil.success(hourlyNums);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 按开始日期和结束日期查询
     * @param start 开始日期
     * @param end 结束日期
     * @return
     */
    @RequestMapping(value = "/period/start/{start}/end/{end}",method = RequestMethod.GET)
    public ResultObj periodHourlyNum(@PathVariable("start")String start, @PathVariable("end")String end){
        List<HourlyNum> hourlyNums = hourlyNumService.periodHourlyNum(start, end);
        if (hourlyNums.size() > 0){
            return ResponseUtil.success(hourlyNums);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 查询某一天的
     * @param date
     * @return
     */
    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public ResultObj getOneDayNum(@PathVariable("date") String date){
        List<HourlyNum> hourlyNums = hourlyNumService.getOneDayNum(date);
        if (hourlyNums.size() > 0){
            return ResponseUtil.success(hourlyNums);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }

    }
}
