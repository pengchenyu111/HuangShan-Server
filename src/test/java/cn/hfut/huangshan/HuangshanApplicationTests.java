package cn.hfut.huangshan;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class HuangshanApplicationTests {

    @Test
    void test1() {
        System.out.println(LocalDateTime.now());
    }

}
