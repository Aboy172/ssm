package com.cym.crowd.handler;

import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.error.RedisFailCode;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cym
 * @date 2022-05-18  22:22
 * @description redis服务提供请求处理器
 */
@RestController
public class RedisHandler {

  @Resource
  private RedisTemplate<String, String> redisTemplate;

  /**
   * 远程调用redis设置key值接口
   *
   * @param key   键
   * @param value 值
   * @return 返回设置结果
   */
  @PostMapping("/set/redis/{key}/{value}")
  public RespBean<String> setRedisKeyValue(@PathVariable("key") String key,
      @PathVariable("value") String value) {

    try {

      ValueOperations<String, String> operations = redisTemplate.opsForValue();
      operations.set(key, value);
      return RespBean.success(null);
    } catch (Exception e) {
      return new RespBean<>(RedisFailCode.NULL_ERROR_MEMBERS.getMessage());
    }
  }

  /**
   * 远程调用redis设置key值+过期时间接口
   *
   * @param key      键
   * @param value    值
   * @param time     过期时间
   * @param timeUnit 单位
   * @return 返回设置结果
   */

  @PostMapping("/set/redis/key/value/time")
  RespBean<String> setRedisKeyValueWithTimeout(@RequestParam("key")String key,@RequestParam("value") String value,
      @RequestParam("time")Integer time,@RequestParam("timeUnit") TimeUnit timeUnit ){
    try {
      ValueOperations<String, String> operations = redisTemplate.opsForValue();
      operations.set(key, value, time,timeUnit);
      return RespBean.success(null);
    } catch (Exception e) {
      return new RespBean<>(RedisFailCode.NULL_ERROR_MEMBERS.getMessage());
    }

  }

  /**
   * 远程调用redis获取key值接口
   *
   * @param key 键
   * @return 返回设置结果
   */
  @GetMapping("/get/redis/string/{key}")
  public RespBean<String> getRedisKeyValue(@PathVariable("key") String key) {
    try {

      ValueOperations<String, String> operations = redisTemplate.opsForValue();
      String redisKey = operations.get(key);
      return RespBean.success(redisKey);
    } catch (Exception e) {
      return new RespBean<>(RedisFailCode.NULL_ERROR_MEMBERS.getMessage());
    }
  }

  /**
   * 远程调用redis移除key接口
   *
   * @param key 键
   * @return 返回设置结果
   */
  @GetMapping("/remove/redis/string/{key}")
  public RespBean<String> removeRedisKeyValue(@PathVariable("key") String key) {
    try {

      Boolean delete = redisTemplate.delete(key);
      return RespBean.success("删除情况:" + delete);
    } catch (Exception e) {
      return new RespBean<>(RedisFailCode.NULL_ERROR_MEMBERS.getMessage());
    }
  }


}
