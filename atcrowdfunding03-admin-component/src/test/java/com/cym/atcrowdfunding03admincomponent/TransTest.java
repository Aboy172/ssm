package com.cym.atcrowdfunding03admincomponent;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cym.atcrowdfunding03admincomponent.mapper.AdminMapper;
import com.cym.atcrowdfunding03admincomponent.pojo.Admin;
import com.cym.atcrowdfunding03admincomponent.service.AdminService;
import com.cym.atcrowdfunding03admincomponent.exception.GlobalException;
import com.cym.atcrowdfunding05commonutil.vo.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * program: ssm Date: 2022-03-29  11:30 Author: cym Description:
 */
@SpringBootTest
@Slf4j
public class TransTest {

  @Autowired
  private AdminService adminService;

  @Autowired
  private AdminMapper adminMapper;

  @Test
  public void testSaveAdmin() {
    SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
    sdf.applyPattern("yyyy-MM-dd HH:mm:ss");// a为am/pm的标记
    Date date = new Date();
    Admin admin = new Admin();
    admin.setLoginAcct("cym");
    admin.setUserPswd("123456");
    admin.setUserName("admin");
    admin.setEmail("11");
    admin.setCreateTime(sdf.format(date));
    //int result = adminService.saveAdmin(admin);
    //System.out.println(result);

  }

  @Test
  public void test() {
    String login_acct = "ccc";
    boolean r = false;
    QueryWrapper<Admin> wrapper = new QueryWrapper<>();
    wrapper.select("login_acct");
    List<Map<String, Object>> list = adminMapper.selectMaps(wrapper);
    for (Map<String, Object> item : list) {
      if (item.get("login_acct").equals(login_acct)) {
        throw new GlobalException(RespBeanEnum.ADMIN_EXIST);
      }
    }
    System.out.println(r);

  }
}
