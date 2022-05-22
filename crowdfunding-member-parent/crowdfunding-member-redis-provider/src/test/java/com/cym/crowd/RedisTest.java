package com.cym.crowd;

import java.io.Serializable;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cym
 * @date 2022-05-18  21:53
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest implements Serializable {

  @Resource
  private RedisTemplate<String, String> redisTemplate;

  @Test
  public void test() {
    log.info("连接redis数据库中.......");
    redisTemplate.opsForValue().set("k1", "k2");

    log.info("执行完毕........");

  }
}
