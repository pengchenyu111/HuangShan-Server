package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Complaint;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.ComplaintService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户投诉接口
 * @author pcy
 */
@RestController
@RequestMapping("/complaints")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    /**
     * 全查询
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultObj getAllComplaints(){
        List<Complaint> complaints = complaintService.getAllComplaints();
        if (complaints.size() > 0){
            return ResponseUtil.success(complaints);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 查询最近的投诉，7个，用于轮播
     * 其实这里应该用消息队列 kafka 或 RabbitMQ 实现才更好，用户一来投诉就加到消息队列里
     * 但还学的懵懂，暂时不用了
     * @return
     */
    @RequestMapping(value = "/recent", method = RequestMethod.GET)
    public ResultObj getRecent(){
        List<Complaint> complaints = complaintService.getRecent();
        if (complaints.size() > 0){
            return ResponseUtil.success(complaints);
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
        Complaint complaint = complaintService.getOneById(id);
        if (complaint != null){
            return ResponseUtil.success(complaint);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 获取投诉分类排行榜
     * @return
     */
    @RequestMapping(value = "/ranks", method = RequestMethod.GET)
    public ResultObj getRanks(){
        List<String> ranks = complaintService.getRanks();
        if (ranks.size() > 0){
            return ResponseUtil.success(ranks);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 根据日期查询
     * 这里不能直接写/{date},因为要与/{id}混，报错
     * @param date
     * @return
     */
    @RequestMapping(value = "/dates/{date}", method = RequestMethod.GET)
    public ResultObj getByDate(@PathVariable("date") String date){
        List<Complaint> complaints = complaintService.getByDate(date);
        if (complaints.size() > 0){
            return ResponseUtil.success(complaints);
        }else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 增加一个
     * @param complaint
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultObj addOne(@RequestBody Complaint complaint){
        Complaint insertComplaint = complaintService.addOne(complaint);
        if (insertComplaint != null){
            return ResponseUtil.success(insertComplaint);
        }else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL,ErrorCode.ADD_FAIL_MSG,null);
        }

    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("id") long id){
        boolean isSuccess = complaintService.deleteOne(id);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL,ErrorCode.DELETE_FAIL_MSG,null);
        }
    }

    /**
     * 全更新一个
     * @param id
     * @param complaint
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj updateOne(@PathVariable("id") long id,@RequestBody Complaint complaint){
        Complaint updatedComplaint = complaintService.updateOne(complaint);
        if (updatedComplaint != null){
            return ResponseUtil.success(updatedComplaint);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }

    /**
     * 管理员处理一条用户的反馈
     * @param id
     * @param map
     * @return
     */
    @RequestMapping(value = "handles/{id}",method = RequestMethod.PUT)
    public ResultObj handleOne(@PathVariable("id") long id, @RequestBody Map<String,String> map){
        String handleAdminName = map.get("handleAdminName");
        String handleMessage = map.get("handleMessage");
        Complaint complaint = complaintService.handleOne(id,handleAdminName,handleMessage);
        if (complaint != null){
            return ResponseUtil.success(complaint);
        }else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }
}
