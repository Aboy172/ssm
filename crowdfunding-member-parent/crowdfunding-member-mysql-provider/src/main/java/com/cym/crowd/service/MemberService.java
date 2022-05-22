package com.cym.crowd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cym.crowd.entity.po.Member;

/**
 * @author cym
 * @description 针对表【t_member】的数据库操作Service
 * @createDate 2022-05-17 15:07:53
 */

public interface MemberService extends IService<Member> {

  /**
   * 根据账号名获取会员用户详细信息
   *
   * @param loginacct 登录账号
   * @return 返回会员用户详细信息
   */
  Member getMemberByLoginAcct(String loginacct);
}
