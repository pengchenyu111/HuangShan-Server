package cn.hfut.huangshan.utils;

import cn.hfut.huangshan.constants.DefaultSetting;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EncryptionUtilTest {

    @Test
    void test(){
        String s = EncryptionUtil.sha384HashWithSalt(DefaultSetting.DEFAULT_PASSWORD, DefaultSetting.DEFAULT_PASSWORD_SALT);
        System.out.println(s);
    }
}
