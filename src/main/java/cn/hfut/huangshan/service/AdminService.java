package cn.hfut.huangshan.service;

import cn.hfut.huangshan.mapper.AdminMapper;
import cn.hfut.huangshan.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员
 * @author pcy
 */
@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Transactional
    public List<Admin> getAllAdmins(){
        List<Admin> admins = adminMapper.getAllAdmins();
        return admins;
    }
}
