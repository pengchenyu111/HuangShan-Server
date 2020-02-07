package cn.hfut.huangshan.MapperTest;

import cn.hfut.huangshan.mapper.AdminMapper;
import cn.hfut.huangshan.pojo.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdminTest {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 测试查询所有管理员
     */
    @Test
    void testAll(){
        List<Admin> admins = adminMapper.getAllAdmins();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }
}
