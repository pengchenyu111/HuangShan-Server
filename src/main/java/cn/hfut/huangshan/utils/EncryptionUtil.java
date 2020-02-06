package cn.hfut.huangshan.utils;

import org.apache.shiro.crypto.hash.Sha384Hash;

/**
 * 密码加密工具类
 * @author PCY
 * */
public class EncryptionUtil {

    /**
     * sha384Hash 简单sha384加密
     * @param source 加密字符串
     * @return 加密后的字符串
     */
    public static String sha384Hash(String source){
        String code = new Sha384Hash(source).toString();
        return code;
    }

    /**
     * sha384HashWithSalt 加盐加密
     * @param source 加密字符串
     * @param salt 盐
     * @return 加密后的字符串
     */
    public static String sha384HashWithSalt(String source,String salt){
        String code = new Sha384Hash(source,salt).toString();
        return code;
    }
}