package cn.hfut.huangshan.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AliyunMessageTest {

    @Test
    void send(){
        AliyunMessageUtil.sendVerificationCode("18224464804");
    }
}
