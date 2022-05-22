package com.cym.crowdfundingcommonutil.vo.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author cym
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public enum MemberSuccessCode {
  /**
   * member业务成功返回数据
   */
  SUCCESS_MEMBERS_LOGIN("200", "登录成功");

  private String code;

  private String message;

}
