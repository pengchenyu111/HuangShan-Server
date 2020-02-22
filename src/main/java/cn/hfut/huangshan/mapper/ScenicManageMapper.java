package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.AdminScenicManage;
import cn.hfut.huangshan.pojo.ScenicManage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员管理景点
 * @author pcy
 */
@Repository
@Mapper
public interface ScenicManageMapper {

    //全查询
    List<ScenicManage> getAllScenicManage();

    //查询某一个管理员管理的景点
    List<AdminScenicManage> getOnesManages(@Param("adminId") int adminId);
}
