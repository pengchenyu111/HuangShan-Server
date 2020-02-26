package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    //只查询近期的7个通知，用于轮播
    List<Notification> getRecentNotification();

    //根据id查询
    Notification getById(@Param("id") long id);

    //新增一个
    Integer add(Notification notification);

    //更新一个
    Integer updateOne(Notification notification);

    //删除一个
    Integer deleteOne(@Param("id") long id);
}
