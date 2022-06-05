package com.cym.crowd.handler;

import com.cym.crowd.entity.po.Member;
import com.cym.crowd.service.MySqlRemoteService;
import com.cym.crowd.service.RedisRemoteService;
import com.cym.crowd.service.ShortMessageRemoteService;
import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cym
 * @date 2022-05-18  18:10
 * @description 远程调用member请求处理器
 */
@RestController
public class MemberRemoteHandler {


  @Resource
  private ShortMessageRemoteService shortMessageRemoteService;

  @Resource
  private RedisRemoteService redisRemoteService;

  @GetMapping("/send/message/remote/{phoneNumber}")
  public RespBean<String> sendMessage(@PathVariable("phoneNumber") String phoneNumber) {
    return shortMessageRemoteService.sendMessage(phoneNumber);
  }


  @GetMapping("/test/{phoneNumber}")
  public RespBean<String> test(@PathVariable("phoneNumber") String phoneNumber) {
    String key = "ccc";
    String code = "111111";
    int time = 10;
    RespBean<String> redisRespBean = redisRemoteService.setRedisKeyValueWithTimeout(key, code,
        time, TimeUnit.SECONDS);
    if (redisRespBean.getCode() == HttpStatus.OK.value()) {
      return RespBean.success("发送成功");
    }
    return RespBean.error(null);
  }
}
