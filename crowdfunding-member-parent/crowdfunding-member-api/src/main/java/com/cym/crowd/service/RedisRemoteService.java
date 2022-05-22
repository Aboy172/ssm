package com.cym.crowd.service;

import com.cym.crowdfundingcommonutil.vo.RespBean;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author cym
 * @date 2022-05-18  22:09
 * @description redis 远程调用接口
 */
@FeignClient("cym-crowd-redis")
public interface RedisRemoteService {

  /**
   * 远程调用redis设置key值接口
   *
   * @param key   键
   * @param value 值
   * @return 返回设置结果
   */
  @PostMapping("/set/redis/{key}/{value}")
  RespBean<String> setRedisKeyValue(@PathVariable("key") String key,
      @PathVariable("value") String value);

  /**
   * 远程调用redis设置key值+过期时间接口
   *
   * @param key      键
   * @param value    值
   * @param time     过期时间
   * @param timeUnit 单位
   * @return 返回设置结果
   */
  @PostMapping("/set/redis/{key}/{value}/{time}/{timeUnix}")
  RespBean<String> setRedisKeyValueWithTimeout(@PathVariable("key") String key,
      @PathVariable("value") String value, @PathVariable("time") long time,
      @PathVariable("timeUnix") TimeUnit timeUnit);

  /**
   * 远程调用redis获取key值接口
   *
   * @param key 键
   * @return 返回设置结果
   */
  @GetMapping("/get/redis/string/{key}")
  RespBean<String> getRedisKeyValue(@PathVariable("key") String key);

  /**
   * 远程调用redis移除key接口
   *
   * @param key 键
   * @return 返回设置结果
   */
  @GetMapping("/remove/redis/string/{key}")
  RespBean<String> removeRedisKeyValue(@PathVariable("key") String key);

  /**
   * 设置session到redis数据库中
   *
   * @param request
   * @param response
   * @return
   */
  @GetMapping("/sava/member/to/ticket")
  RespBean<String> setMemberTicket(HttpServletRequest request,
      HttpServletResponse response);

  @GetMapping("/get/member/ticket")
  RespBean<String> getMemberTicket();

}
