package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.ScenicManageMapper;
import cn.hfut.huangshan.pojo.AdminScenicManage;
import cn.hfut.huangshan.pojo.ScenicManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员管理景点
 * @author pcy
 */
@Service
public class ScenicManageService {

    @Autowired
    ScenicManageMapper scenicManageMapper;

    /**
     * 全查询
     * @return
     */
    @Transactional
    public List<ScenicManage> getAllScenicManage(){
        List<ScenicManage> scenicManages = scenicManageMapper.getAllScenicManage();
        return scenicManages;
    }

    /**
     * 查询某个管理员管理的景点
     * @param adminId 管理员id
     * @return
     */
    @Transactional
    public List<AdminScenicManage> getOnesManages(long adminId){
        List<AdminScenicManage> adminScenicManages = scenicManageMapper.getOnesManages(adminId);
        return adminScenicManages;
    }
}
