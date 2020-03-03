package cn.hfut.huangshan.service;

import cn.hfut.huangshan.constants.DefaultSetting;
import cn.hfut.huangshan.mapper.AdminMapper;
import cn.hfut.huangshan.mapper.RoleMapper;
import cn.hfut.huangshan.pojo.Admin;
import cn.hfut.huangshan.pojo.DB.DBAdmin;
import cn.hfut.huangshan.pojo.Role;
import cn.hfut.huangshan.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 管理员
 * @author pcy
 */
@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;

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
     * 根据id查询
     * @param id
     * @return
     */
    public Admin getOneById(long id) {
        Admin admin = adminMapper.getOneById(id);
        return admin;
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


    /**
     * 添加一个管理员
     * 由于管理员不能自己注册，所以会有个初始的默认密码：账号后6位
     * @param dbAdmin
     * @return
     */
    @Transactional
    public boolean addOne(DBAdmin dbAdmin) {
        //生成新account
        String lastSixNum = String.valueOf(Integer.parseInt(adminMapper.getMaxAccount().substring(4)) + 1);
        String firstFourNum = new SimpleDateFormat("yyyy").format(new Date());
        dbAdmin.setAccount(firstFourNum + lastSixNum);
        //设置默认密码
        String encode = EncryptionUtil.sha384HashWithSalt(lastSixNum, DefaultSetting.DEFAULT_PASSWORD_SALT);
        dbAdmin.setPassword(encode);
        //设置默认头像,这里后期要改再说
        dbAdmin.setHeadIcon(DefaultSetting.DEFAULT_ADMIN_HEAD_ICON);
        //设置生日格式，避免出错
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dbAdmin.getBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dbAdmin.setBirth(format.format(date));
        //插入数据库
        Integer rows = adminMapper.addOne(dbAdmin);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 删除一名
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOne(long id) {
        Integer rows = adminMapper.deleteOne(id);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 全更新一个
     * 这里之所以这么复杂，就是pojo没和表一一对应的缘故
     * @param admin
     * @return
     */
    public boolean changeOne(Admin admin) {
        DBAdmin dbAdmin = new DBAdmin();
        dbAdmin.setId(admin.getId());
        dbAdmin.setAccount(admin.getAccount());
        //注意这里传的是明文密码
        String encode = EncryptionUtil.sha384HashWithSalt(admin.getPassword(),DefaultSetting.DEFAULT_PASSWORD_SALT);
        dbAdmin.setPassword(encode);
        dbAdmin.setName(admin.getName());
        //这里还是要看前端怎么传了
        Role byName = roleMapper.getByName(admin.getRoleName());
        dbAdmin.setRoleId(byName.getId());
        dbAdmin.setPhone(admin.getPhone());
        dbAdmin.setWorkYear(admin.getWorkYear());
        dbAdmin.setBirth(admin.getBirth());
        dbAdmin.setSex(admin.getSex());
        dbAdmin.setIntroduction(admin.getIntroduction());
        dbAdmin.setHeadIcon(admin.getHeadIcon());
        Integer rows = adminMapper.changeOne(dbAdmin);
        if (rows > 0){
            return true;
        }
        return false;
    }
}
