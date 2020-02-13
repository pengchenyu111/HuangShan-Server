package cn.hfut.huangshan.controller;

import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Notification;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.NotificationService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
