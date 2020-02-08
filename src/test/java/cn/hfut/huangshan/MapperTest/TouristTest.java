package cn.hfut.huangshan.MapperTest;

import cn.hfut.huangshan.mapper.TouristMapper;
import cn.hfut.huangshan.pojo.Tourist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TouristTest {

    @Autowired
    TouristMapper touristMapper;

    @Test
    void testAll(){
        List<Tourist> tourists = touristMapper.getAllTourists();
        for (Tourist tourist : tourists) {
            System.out.println(tourist);
        }
    }

    @Test
    void testLogin(){
        Tourist tourist = touristMapper.touristLogin("18224464804", "hs123456");
        System.out.println(tourist);
    }
}
