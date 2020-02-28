package cn.hfut.huangshan.service;

import cn.hfut.huangshan.constants.DefaultSetting;
import cn.hfut.huangshan.mapper.AdminMapper;
import cn.hfut.huangshan.pojo.Admin;
import cn.hfut.huangshan.utils.EncryptionUtil;
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
        //获取加密后的字符串
        String encodePassword = EncryptionUtil.sha384HashWithSalt(password, DefaultSetting.DEFAULT_PASSWORD_SALT);
        Admin admin = adminMapper.adminLogin(account, encodePassword);
        return admin;
    }

    /**
     * 系统管理员网页端登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    @Transactional
    public Admin systemAdminLogin(String account, String password) {
        String encodePassword = EncryptionUtil.sha384HashWithSalt(password, DefaultSetting.DEFAULT_PASSWORD_SALT);
        try {
            Admin systemAdmin = adminMapper.systemAdminLogin(account, encodePassword);
            return systemAdmin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Admin();
    }

    /**
     * 更换头像
     * @param headIconUrl 头像地址
     * @param account 账号
     * @return
     */
    @Transactional
    public boolean changeHeadIcon(String headIconUrl, String account){
        Integer rows = adminMapper.changeAdminHeadIcon(headIconUrl, account);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 更新联系电话
     * @param account 账号
     * @param phone 电话
     * @return
     */
    @Transactional
    public boolean changePhone(String account, String phone){
        Integer rows = adminMapper.changePhone(account, phone);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 更新联系电话
     * @param account 账号
     * @param introduction 简介
     * @return
     */
    @Transactional
    public boolean changeIntroduction(String account, String introduction){
        Integer rows = adminMapper.changeIntroduction(account, introduction);
        if (rows > 0){
            return true;
        }
        return false;
    }
}
