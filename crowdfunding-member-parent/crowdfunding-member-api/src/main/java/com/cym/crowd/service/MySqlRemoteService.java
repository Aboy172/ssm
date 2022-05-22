package com.cym.crowd.service;

import com.cym.crowd.entity.po.Member;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cym
 * @date 2022-05-18  17:11
 * @description 调用mysql服务模块
 */
@FeignClient("cym-crowd-mysql")
public interface MySqlRemoteService {

  /**
   * 远程调用登录功能验证功能
   *
   * @param loginacct 登录账号
   * @return 返回登录情况的json数据结果集
   */
  @RequestMapping("/get/member/by/{loginacct}")
  RespBean<Member> getMemberByLoginAcct(@PathVariable("loginacct") String loginacct);


}
