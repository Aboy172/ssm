package com.cym.crowd.service;

import com.cym.crowdfundingcommonutil.vo.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author cym
 * @date 2022-06-05  16:15
 * @description 远程调用短信服务接口
 */
@FeignClient("cym-crowd-short-message")
public interface ShortMessageRemoteService {

  /**
   * 远程调用短信服务的发送短信功能
   * @param phoneNumber 手机号
   * @return 返回获取结构结果
   */
  @GetMapping("/send/message/{phoneNumber}")
  RespBean<String> sendMessage(@PathVariable("phoneNumber") String phoneNumber);
}
