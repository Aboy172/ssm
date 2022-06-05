package com.cym.crowd.handler;

import com.cym.crowd.entity.po.Member;
import com.cym.crowd.entity.vo.LoginVo;
import com.cym.crowd.service.MySqlRemoteService;
import com.cym.crowd.service.RedisRemoteService;
import com.cym.crowd.service.ShortMessageRemoteService;
import com.cym.crowdfundingcommonutil.constant.CrowdConstant;
import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cym
 * @date 2022-05-19  14:04
 * @description Member页面请求处理器
 */
@RestController
public class MemberHandler {

  @Resource
  private ShortMessageRemoteService shortMessageRemoteService;
  @Resource
  private RedisRemoteService redisRemoteService;

  @Resource
  private MySqlRemoteService mySqlRemoteService;

  @GetMapping("/auth/remote/get/member/by/{loginacct}")
  public RespBean<Member> getMemberByLoginAcct(@PathVariable("loginacct") String loginacct) {
    if (loginacct == null) {
      throw new GlobalException(RespBeanEnum.MENU_PID_ERROR);
    }
    return mySqlRemoteService.getMemberByLoginAcct(loginacct);

  }

  @GetMapping("/auth/remote/send/message")
  public RespBean<String> sendMessage(@Valid @RequestBody LoginVo loginVo) {
    String phoneNumber = loginVo.getPhoneNumber();
    RespBean<String> respBean = shortMessageRemoteService.sendMessage(phoneNumber);
    String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNumber;
    //如果发送短信成功,将验证码存入redis数据库中
    if (respBean.getCode() == HttpStatus.OK.value()) {
      String code = respBean.getObj();
      RespBean<String> redisRespBean = redisRemoteService.setRedisKeyValueWithTimeout(key, code,
          300, TimeUnit.SECONDS);
      if (redisRespBean.getCode() == HttpStatus.OK.value()) {
        return RespBean.success("获取成功");
      }
    }
    return respBean;
  }


}
