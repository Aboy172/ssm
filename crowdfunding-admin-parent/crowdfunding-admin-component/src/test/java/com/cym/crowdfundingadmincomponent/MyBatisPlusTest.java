package com.cym.crowdfundingadmincomponent;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cym.crowdfundingadmincomponent.mapper.AdminMapper;
import com.cym.crowdfundingadmincomponent.service.AdminService;
import com.cym.crowdfundingadmincomponent.service.AuthService;
import com.cym.crowdfundingadminentity.entity.pojo.Admin;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * program: atcrowdfunding01-admin-parent
 * Date: 2022-03-28  20:08
 * Author: cym
 * Description:
 */
@SpringBootTest
@Slf4j
public class MyBatisPlusTest {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AuthService authService;


    @Test
    public void test ( ) {
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
        Date date = new Date();
        Admin admin = new Admin();
        admin.setLoginAcct("cym");
        admin.setUserPswd("123456");
        admin.setUserName("admin");
        admin.setEmail("123@163.com");
        admin.setCreateTime(sdf.format(date));
        int result = adminMapper.insertSelective(admin);

    }


    @Test
    public void testGetPage ( ) {
        Page<Admin> pageInfo = adminService.getPage("",1,5);
        System.out.println(pageInfo);
    }


    @Test
    public void testConcurrentUpdate ( ) {
        Admin adminWang = adminMapper.selectById(7);
        adminWang.setUserName("张三");
        adminMapper.updateById(adminWang);


        Admin adminLi = adminMapper.selectById(7);
        adminLi.setUserName("李四2");
        adminMapper.updateById(adminLi);


        Admin adminBoss = adminMapper.selectById(7);
        log.info("adminBoss ="+adminBoss);


    }


}
