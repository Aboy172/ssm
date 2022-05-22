package com.cym.crowd.handler;

import com.cym.crowd.entity.po.Member;
import com.cym.crowd.service.MySqlRemoteService;
import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cym
 * @date 2022-05-18  18:10
 * @description 远程调用member请求处理器
 */
@RestController
public class MemberRemoteHandler {

  @Resource
  private MySqlRemoteService mySqlRemoteService;

  @GetMapping("/remote/get/member/by/{loginacct}")
  public RespBean<Member> getMemberByLoginAcct(@PathVariable("loginacct") String loginacct) {
    if (loginacct == null) {
      throw new GlobalException(RespBeanEnum.MENU_PID_ERROR);
    }
    return mySqlRemoteService.getMemberByLoginAcct(loginacct);
  }


}
