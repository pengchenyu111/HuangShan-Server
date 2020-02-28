package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Notification;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.NotificationService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 通知
 * @author pcy
 */
@RestController
@RequestMapping("notifications")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    /**
     * 全查询
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResultObj getAllNotification(){
        List<Notification> notifications = notificationService.getAllNotification();
        if (notifications.size() > 0) {
            return ResponseUtil.success(notifications);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 只查询近期的7个通知，用于轮播
     * @return
     */
    @RequestMapping(value = "/recent", method = RequestMethod.GET)
    public ResultObj getRecentNotification(){
        List<Notification> notifications = notificationService.getRecentNotification();
        if (notifications.size() > 0) {
            return ResponseUtil.success(notifications);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResultObj getById(@PathVariable("id") long id){
        Notification notification = notificationService.getById(id);
        if (notification != null) {
            return ResponseUtil.success(notification);
        } else {
            return ResponseUtil.error(ErrorCode.QUERY_FAIL,ErrorCode.QUERY_FAIL_MSG,null);
        }
    }

    /**
     * 新增一个
     * @param notification
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResultObj addOne(@RequestBody Notification notification){
        Notification insertNotification = notificationService.add(notification);
        if (insertNotification != null){
            return ResponseUtil.success(insertNotification);
        } else {
            return ResponseUtil.error(ErrorCode.ADD_FAIL,ErrorCode.ADD_FAIL_MSG,null);
        }
    }

    /**
     * 更新一个
     * @param notification
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResultObj updateOne(@PathVariable("id") long id, @RequestBody Notification notification){
        notification.setId(id);//这里会有点小小的问题，可以任意植入id？
        Notification updatedNotification = notificationService.updateOne(notification);
        if (updatedNotification != null){
            return ResponseUtil.success(updatedNotification);
        } else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }

    /**
     * 关闭一条通知
     * @param id
     * @return
     */
    @RequestMapping(value = "/closes/{id}", method = RequestMethod.PUT)
    public ResultObj closeOne(@PathVariable("id") long id){
        boolean isSuccess = notificationService.closeOne(id);
        if (isSuccess){
            return ResponseUtil.success(null);
        } else {
            return ResponseUtil.error(ErrorCode.UPDATE_FAIL,ErrorCode.UPDATE_FAIL_MSG,null);
        }
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResultObj deleteOne(@PathVariable("id") long id){
        boolean isSuccess = notificationService.deleteOne(id);
        if (isSuccess){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.error(ErrorCode.DELETE_FAIL,ErrorCode.DELETE_FAIL_MSG,null);
        }
    }
}
