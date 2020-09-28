package cn.hfut.huangshan.service;

import cn.hfut.huangshan.pojo.Scenic;
import cn.hfut.huangshan.utils.IdWorker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ScenicTest {

    @Autowired
    ScenicService scenicService;

    /*
    *junit4	        junit5	    特点
    *@BeforeClass	@BeforeAll	在当前类的所有测试方法之前执行。注解在【静态方法】上。
    *@AfterClass	@AfterAll	在当前类中的所有测试方法之后执行。注解在【静态方法】上。
    *@Before	    @BeforeEach	在每个测试方法之前执行。注解在【非静态方法】上。
    *@After	        @AfterEach	在每个测试方法之后执行。注解在【非静态方法】上。
    * */

    @BeforeEach
    public void beforeMethodTest(){
        System.out.println("********开始测试**********");
    }

    @AfterEach
    public void afterMethodTest(){
        System.out.println("********测试结束**********");
    }

    @Test
    public void testQueryAll(){
        List<Scenic> scenes = scenicService.getAllScenes();
        System.out.println(scenes.toString());
        Assert.notEmpty(scenes, "查询结果为空！");
    }

    @Test
    public void testQueryOne(){
        long id = 1;
        Scenic scenic = scenicService.getById(id);
        System.out.println(scenic);
        Assert.notNull(scenic,"查询结果为空！");
    }

    @Test
    public void testAddOne(){
        IdWorker idWorker = new IdWorker();
        long id = idWorker.nextId();
        Scenic scenic = new Scenic();
        scenic.setId(id);
        boolean isAdd = scenicService.addOne(scenic);
        Assert.isTrue(isAdd, "未添加成功！");
    }

    @Test
    public void testDeleteOne(){
        boolean isDelete = scenicService.deleteOne(439655278);
        Assert.isTrue(isDelete, "未删除成功！");
    }



}
