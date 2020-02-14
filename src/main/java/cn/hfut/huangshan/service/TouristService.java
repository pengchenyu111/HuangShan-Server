package cn.hfut.huangshan.service;

import cn.hfut.huangshan.constants.DefaultSetting;
import cn.hfut.huangshan.mapper.TouristMapper;
import cn.hfut.huangshan.pojo.Tourist;
import cn.hfut.huangshan.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 游客
 * @author pcy
 */
@Service
public class TouristService {

    @Autowired
    TouristMapper touristMapper;

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
}
