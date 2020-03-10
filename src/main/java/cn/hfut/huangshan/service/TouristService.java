package cn.hfut.huangshan.service;

import cn.hfut.huangshan.constants.DefaultSetting;
import cn.hfut.huangshan.mapper.TouristMapper;
import cn.hfut.huangshan.pojo.DB.DBTourist;
import cn.hfut.huangshan.pojo.Tourist;
import cn.hfut.huangshan.utils.EncryptionUtil;
import cn.hfut.huangshan.utils.RedisUtil;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 游客
 * @author pcy
 */
@Service
public class TouristService {

    @Autowired
    TouristMapper touristMapper;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 全查询
     * @return
     */
    @Transactional
    public List<Tourist> getAllTourists(){
        List<Tourist> tourists = touristMapper.getAllTourists();
        return tourists;
    }

    /**
     * 游客登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    @Transactional
    public Tourist touristLogin(String account,String password){
        //获取加密后的字符串
        String encodePassword = EncryptionUtil.sha384HashWithSalt(password, DefaultSetting.DEFAULT_PASSWORD_SALT);
        Tourist tourist = touristMapper.touristLogin(account, encodePassword);
        return tourist;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Tourist getById(long id) {
        Tourist tourist = touristMapper.getById(id);
        return tourist;
    }

    /**
     * 增加一个
     * 这个可以给管理员添加游客，不用发验证码
     * @param dbTourist
     * @return
     */
    @Transactional
    public boolean addOne(DBTourist dbTourist) {
        //检查账号是否已注册
        Tourist byPhone = touristMapper.getByPhone(dbTourist.getPhone());
        if (byPhone != null ){
            //账号已注册
            return false;
        }
        //设置账户
        dbTourist.setAccount(dbTourist.getPhone());
        //设置默认名字
        dbTourist.setName("游客" + dbTourist.getPhone());
        //设置加密密码
        String encode = EncryptionUtil.sha384HashWithSalt(dbTourist.getPassword(),DefaultSetting.DEFAULT_PASSWORD_SALT);
        dbTourist.setPassword(encode);
        //设置角色,游客：5，写死
        dbTourist.setRoleId(5);
        //设置默认头像
        dbTourist.setHeadIcon(DefaultSetting.DEFAULT_ADMIN_HEAD_ICON);
        Integer rows = touristMapper.addOne(dbTourist);
        if (rows > 0){
            return true;
        }
        return false;

    }

    /**
     * 更新个人资料
     * @param tourist
     * @return
     */
    @Transactional
    public boolean updateOne(Tourist tourist) {
        DBTourist dbTourist = new DBTourist();
        //id传过来
        dbTourist.setId(tourist.getId());
        //账号随手机号变化
        dbTourist.setAccount(tourist.getPhone());
        //密码不变，会有单独的修改密码接口
        //修改姓名
        dbTourist.setName(tourist.getName());
        //角色写死
        dbTourist.setRoleId(5);
        //修改手机号
        dbTourist.setPhone(tourist.getPhone());
        //设置生日格式，避免出错
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(tourist.getBirth());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dbTourist.setBirth(format.format(date));
        //设置性别
        dbTourist.setSex(tourist.getSex());
        //设置头像
        dbTourist.setHeadIcon(tourist.getHeadIcon());
        Integer rows = touristMapper.updateOne(dbTourist);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 修改密码
     * @param id
     * @param newPassword
     * @return
     */
    @Transactional
    public boolean changePassword(long id, String newPassword) {
        String encode = EncryptionUtil.sha384HashWithSalt(newPassword,DefaultSetting.DEFAULT_PASSWORD_SALT);
        Integer rows = touristMapper.updatePassword(id, encode);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteOne(long id) {
        Integer rows = touristMapper.deleteOne(id);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 修改头像
     * @param id
     * @param headIconUrl
     * @return
     */
    @Transactional
    public boolean changeHeadIcon(long id, String headIconUrl) {
        Integer rows = touristMapper.changeHeadIcon(id,headIconUrl);
        if (rows > 0){
            return true;
        }
        return false;
    }

    /**
     * 游客注册
     * 注册前一定要发送验证码
     * @param id
     * @param phone
     * @param password
     * @param verificationCode 验证码
     * @return
     */
    public String registerOne(long id, String phone, String password, String verificationCode) {
        //检查账号是否已注册
        Tourist byPhone = touristMapper.getByPhone(phone);
        if (byPhone != null ){
            return "4";
        }
        //检查验证码有没有过期
        String key = "vercode:" + phone;
        Long ttl = redisUtil.ttl(key, 0);
        if (ttl <= 0){
            //过期
            return "1";
        }else {
            //再看验证码对不对
            String value = redisUtil.get(key, 0);
            if (value.equals(verificationCode)){
                DBTourist dbTourist = new DBTourist();
                //设置id
                dbTourist.setId(id);
                //设置账户
                dbTourist.setAccount(phone);
                //设置默认名字
                dbTourist.setName("游客" + phone);
                //设置加密密码
                String encode = EncryptionUtil.sha384HashWithSalt(password,DefaultSetting.DEFAULT_PASSWORD_SALT);
                dbTourist.setPassword(encode);
                //设置角色,游客：5，写死
                dbTourist.setRoleId(5);
                //设置默认头像
                dbTourist.setHeadIcon(DefaultSetting.DEFAULT_ADMIN_HEAD_ICON);
                Integer rows = touristMapper.addOne(dbTourist);
                if (rows > 0){
                    return "0";
                }
                return "3";
            }else {
                return "2";
            }
        }
    }
}
