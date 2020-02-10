package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.ScenicManage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员管理景点
 * @author pcy
 */
@Repository
@Mapper
public interface ScenicManageMapper {

    List<ScenicManage> getAllScenicManage();
}
