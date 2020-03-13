package cn.hfut.huangshan.mapper;


import cn.hfut.huangshan.pojo.Suggestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SuggestionMapper {


    //全查询
    List<Suggestion> getAll();

    //根据id查询
    Suggestion getById(@Param("id") long id);

    //增加一个
    Integer addOne(Suggestion suggestion);

    //更新一个
    Integer updateOne(Suggestion suggestion);

    //删除一个
    Integer deleteOne(@Param("id") long id);
}
