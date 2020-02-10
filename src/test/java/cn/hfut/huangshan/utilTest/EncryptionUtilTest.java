package cn.hfut.huangshan.utilTest;

import cn.hfut.huangshan.constants.DefaultSetting;
import cn.hfut.huangshan.utils.EncryptionUtil;
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
