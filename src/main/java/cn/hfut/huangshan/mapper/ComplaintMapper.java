package cn.hfut.huangshan.mapper;

import cn.hfut.huangshan.pojo.Complaint;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 投诉
 * @author pcy
 */
@Mapper
@Repository
public interface ComplaintMapper {

    //全查询
    List<Complaint> getAllComplaints();

    //查询最近的
    List<Complaint> getRecent();

    //根据id查询
    Complaint getOneById(@Param("id") long id);

    //获取投诉分类排行榜
    List<String> getRanks();

    //根据日期查询
    List<Complaint> getByDate(@Param("date") String formatDate);

    //新增一个
    Integer addOne(Complaint complaint);

    //删除一个
    Integer deleteOne(@Param("id") long id);

    //全更新一个
    Integer updateOne(Complaint complaint);

    //管理员处理一条投诉
    Integer handleOne(@Param("id") long id,@Param("handleAdminName") String handleAdminName, @Param("handleTime")String handleTime, @Param("handleMessage")String handleMessage);


}
