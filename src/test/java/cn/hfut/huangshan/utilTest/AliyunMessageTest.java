package cn.hfut.huangshan.utilTest;

import cn.hfut.huangshan.utils.AliyunMessageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AliyunMessageTest {

    @Test
    void send(){
        AliyunMessageUtil.sendVerificationCode("18224464804");
    }
}
