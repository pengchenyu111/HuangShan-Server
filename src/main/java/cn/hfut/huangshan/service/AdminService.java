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

    /**
     * 全查询
     * @return
     */
    @Transactional
    public List<Admin> getAllAdmins(){
        List<Admin> admins = adminMapper.getAllAdmins();
        return admins;
    }

    /**
     * 管理员登陆
     * @param account 账号
     * @param password 密码
     * @return
     */
    @Transactional
    public Admin adminLogin(String account,String password){
        Admin admin = adminMapper.adminLogin(account, password);
        return admin;
    }
}
