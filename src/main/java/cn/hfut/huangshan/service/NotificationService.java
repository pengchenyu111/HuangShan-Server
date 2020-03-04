package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.NotificationMapper;
import cn.hfut.huangshan.pojo.Notification;
import cn.hfut.huangshan.utils.IdWorker;
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

    /**
     * 只查询近期的7个通知，用于轮播
     * @return
     */
    public List<Notification> getRecentNotification() {
        List<Notification> notifications = notificationMapper.getRecentNotification();
        return notifications;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Transactional
    public Notification getById(long id) {
        Notification notification = notificationMapper.getById(id);
        return notification;
    }

    /**
     * 新增一个
     * @param notification 通知实体
     * @return
     */
    @Transactional
    public Notification add(Notification notification) {
        //插入数据库
        IdWorker idWorker = new IdWorker(0,0);
        long id = idWorker.nextId();
        notification.setId(id);
        Integer rows = notificationMapper.add(notification);
        if (rows > 0){
            return notificationMapper.getById(id);
        }
        return new Notification();
    }



    /**
     * 更新
     * @param notification
     * @return
     */
    @Transactional
    public boolean updateOne(Notification notification) {
        Integer rows = notificationMapper.updateOne(notification);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOne(long id) {
        Integer rows = notificationMapper.deleteOne(id);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 关闭一条通知
     * @param id
     * @return
     */
    @Transactional
    public boolean closeOne(long id) {
        Integer rows = notificationMapper.closeOne(id);
        if (rows > 0){
            return true;
        }
        return false;
    }
}
