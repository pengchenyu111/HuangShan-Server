package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.NotificationMapper;
import cn.hfut.huangshan.pojo.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通知
 * @author pcy
 */
@Service
public class NotificationService {

    @Autowired
    NotificationMapper notificationMapper;

    /**
     * 全查询
     * @return
     */
    @Transactional
    public List<Notification> getAllNotification(){
        List<Notification> notifications = notificationMapper.getAllNotification();
        return notifications;
    }
}
