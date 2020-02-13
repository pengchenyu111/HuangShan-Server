package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通知
 * @author pcy
 */
@Repository
@Mapper
public interface NotificationMapper {

    //全查询
    List<Notification> getAllNotification();
}
