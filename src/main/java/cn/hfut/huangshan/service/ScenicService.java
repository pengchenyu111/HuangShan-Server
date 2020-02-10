package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.ScenicMapper;
import cn.hfut.huangshan.pojo.Scenic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 景点
 * @author pcy
 */
@Service
public class ScenicService {

    @Autowired
    ScenicMapper scenicMapper;

    /**
     * 全查询
     * @return
     */
    @Transactional
    public List<Scenic> getAllScenes(){
        List<Scenic> scenes = scenicMapper.getAllScenes();
        return scenes;
    }
}
