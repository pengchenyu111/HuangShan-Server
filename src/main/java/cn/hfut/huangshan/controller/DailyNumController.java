package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.DailyNum;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.DailyNumService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 日客流 接口
 * @author pcy
 */
@RestController
@RequestMapping("daily_nums")
public class DailyNumController {

    @Autowired
    DailyNumService dailyNumService;

    /**
     * 限制性全查询：降序前1000条
     * @return
     */
    @RequestMapping(value = "/limit_all",method = RequestMethod.GET)
    public ResultObj getLimitAllDailyNum(){
        List<DailyNum> dailyNums = dailyNumService.getLimitAllDailyNum();
        if (dailyNums.size() > 0) {
            return ResponseUtil.success(dailyNums);
        } else {
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
    public ResultObj periodDailyNum(@PathVariable("start")String start, @PathVariable("end")String end){
        //这里让前端来处理开始时间要写于结束时间的校验
        List<DailyNum> dailyNums = dailyNumService.periodDailyNum(start, end);
        if (dailyNums.size() > 0) {
            return ResponseUtil.success(dailyNums);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 根据日期查询某一天的
     * @param date
     * @return
     */
    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public ResultObj getOneByDate(@PathVariable("date") String date){
        DailyNum dailyNum = dailyNumService.getOneByDate(date);
        if (dailyNum != null) {
            return ResponseUtil.success(dailyNum);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }
}
