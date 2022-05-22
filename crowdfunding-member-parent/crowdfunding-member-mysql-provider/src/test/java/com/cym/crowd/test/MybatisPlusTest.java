package com.cym.crowd.test;

import com.cym.crowd.entity.po.Member;
import com.cym.crowd.mapper.MemberMapper;
import java.sql.Connection;
import javax.annotation.Resource;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author cym
 * @date 2022-05-17  14:33
 * @description
 */
@SpringBootTest
@Slf4j
public class MybatisPlusTest {


  @Resource
  private DataSource dataSource;

  @Resource
  private MemberMapper memberMapper;

  @Test
  public void testDataSource() throws Exception {
    Connection connection = dataSource.getConnection();
    log.debug("*************" + connection);
  }

  @Test
  public void testMapper() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String source = "123456";
    String encode = passwordEncoder.encode(source);
    int result = memberMapper.insertSelective(
        new Member(null, "cym", encode, "cc", "cym@163.com", 0, 0, "曹操", "11111", 3232));
    log.info("result : " + result);
  }
}
