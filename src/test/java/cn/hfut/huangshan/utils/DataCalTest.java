package cn.hfut.huangshan.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class DataCalTest {

    @BeforeEach
    public void beforeMethodTest(){
        System.out.println("********开始测试**********");
    }

    @AfterEach
    public void afterMethodTest(){
        System.out.println("********测试结束**********");
    }

    @Test
    public void testLeapYear(){
        int year = 2020;
        boolean leapYear = DateCalculateUtil.isLeapYear(year);
        Assert.isTrue(leapYear,"不是闰年！");
    }

    @Test
    public  void testGetWeek(){
        int weekday = DateCalculateUtil.getWeek("2020-05-07");
        Assert.isTrue(weekday == 4, "计算结果错误！");
    }
}
