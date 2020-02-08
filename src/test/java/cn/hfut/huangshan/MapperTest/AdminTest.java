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

    @Test
    void testLogin(){
        Admin admin = adminMapper.adminLogin("2017211241", "pcy90321");
        System.out.println(admin);
        Admin admin1 = adminMapper.adminLogin("2017211241","123456");
        System.out.println(admin1);

    }
}
