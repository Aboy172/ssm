package com.cym.crowd.handler;

import com.cym.crowd.entity.po.Member;
import com.cym.crowd.service.MemberService;
import com.purgeteam.dispose.starter.Result;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author cym
 * @date 2022-05-18  17:29
 * @description 获取mysql数据库请求处理器
 */
@RestController
@Slf4j
public class MemberProviderHandler {

  @Resource
  private MemberService memberService;

  /**
   * 处理登录会员请求
   *
   * @param loginacct 用户名
   * @return 返回登录情况的json数据结果集
   */
  @RequestMapping("/get/member/by/{loginacct}")
  public Result getMemberByLoginAcct(@PathVariable("loginacct") String loginacct) {
    Member member = memberService.getMemberByLoginAcct(loginacct);
    return Result.ofSuccess(member);
  }

}
