package com.cym.crowd.handler;

import cn.hutool.core.util.IdUtil;
import com.cym.crowdfundingcommonutil.constant.CrowdConstant;
import com.cym.crowdfundingcommonutil.utils.CookieUtil;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.error.RedisFailCode;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cym
 * @date 2022-05-23  02:14
 * @description 登录业务请求redis数据库处理器
 */
@RestController
public class LoginRedisHandler {


  @Resource
  private RedisTemplate<String, Object> redisTemplate;

  @GetMapping("/sava/member/to/ticket")
  public RespBean<String> setMemberTicket(HttpServletRequest request,
      HttpServletResponse response) {
    ValueOperations<String, Object> operations =
        redisTemplate.opsForValue();
    String ticket = IdUtil.randomUUID();
    long timeout = 300L;
    Boolean result = operations.setIfAbsent(CrowdConstant.MEMBER_TICKET, ticket, timeout,
        TimeUnit.SECONDS);
    if (result) {
      CookieUtil.setCookie(request, response, CrowdConstant.MEMBER_TICKET, ticket);
      return RespBean.success(ticket);
    }
    return RespBean.error(RedisFailCode.NULL_ERROR_MEMBERS.getCode(),
        RedisFailCode.NULL_ERROR_MEMBERS.getMessage());
  }

  @GetMapping("/get/member/ticket")
  public RespBean<String> getMemberTicket(String ticket) {
    ValueOperations<String, Object> operations = redisTemplate.opsForValue();
    String memberTicket = (String) operations.get(ticket);
    if (memberTicket == null) {
      return null;
    }
    return RespBean.success(memberTicket);
  }

}
