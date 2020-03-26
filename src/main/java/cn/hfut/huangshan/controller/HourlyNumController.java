package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.HourlyNum;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.HourlyNumService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 按日期+时间查询
     * @param date
     * @param hour
     * @return
     */
    @RequestMapping(value = "/{date}/{hour}", method = RequestMethod.GET)
    public ResultObj getOneByDateHour(@PathVariable("date")String date, @PathVariable("hour") String hour){
        HourlyNum hourlyNum = hourlyNumService.getOneByDateHour(date,hour);
        if (hourlyNum != null){
            return ResponseUtil.success(hourlyNum);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL, ErrorCode.QUERY_FAIL_MSG, null);
        }
    }

    /**
     * 增加一个
     * 注意hourlyNum 的 hour 的 9点要写成 09 ，不能写成 9
     * @param hourlyNum
     * @return
     */
    @RequestMapping(value = "/one", method = RequestMethod.POST)
    public ResultObj addOne(@RequestBody HourlyNum hourlyNum){
        boolean isSuccess = hourlyNumService.addOne(hourlyNum);
        if (isSuccess){
            HourlyNum insertedOne = hourlyNumService.getOneByDateHour(hourlyNum.getDateName(), hourlyNum.getHour());
            return ResponseUtil.success(insertedOne);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL, ErrorCode.ADD_FAIL_MSG, null);
        }
    }

    /**
     * 批量增加
     * @param hourlyNums
     * @return
     */
    @RequestMapping(value = "/many", method = RequestMethod.POST)
    public ResultObj addMany(@RequestBody List<HourlyNum> hourlyNums){
        boolean isSuccess = hourlyNumService.addMany(hourlyNums);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL, ErrorCode.ADD_FAIL_MSG, null);
        }
    }

    /**
     * 更新一个
     * @param date
     * @param hour
     * @param hourlyNum
     * @return
     */
    @RequestMapping(value = "/{date}/{hour}", method = RequestMethod.PUT)
    public ResultObj updateOne(@PathVariable("date")String date, @PathVariable("hour") String hour, @RequestBody HourlyNum hourlyNum){
        boolean isSuccess = hourlyNumService.updateOne(hourlyNum);
        if (isSuccess){
            HourlyNum updatedOne = hourlyNumService.getOneByDateHour(date, hour);
            return ResponseUtil.success(updatedOne);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL, ErrorCode.UPDATE_FAIL_MSG, null);
        }
    }

    /**
     * 删除一个
     * @param date
     * @param hour
     * @return
     */
    @RequestMapping(value = "/{date}/{hour}", method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("date")String date, @PathVariable("hour") String hour){
        boolean isSuccess = hourlyNumService.deleteOne(date,hour);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL, ErrorCode.DELETE_FAIL_MSG, null);
        }
    }

    /**
     * 删除一天内的全部
     * @param date
     * @return
     */
    @RequestMapping(value = "/{date}", method = RequestMethod.DELETE)
    public ResultObj deleteOneDayAll(@PathVariable("date")String date){
        boolean isSuccess = hourlyNumService.deleteOneDayAll(date);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL, ErrorCode.DELETE_FAIL_MSG, null);
        }
    }
}
