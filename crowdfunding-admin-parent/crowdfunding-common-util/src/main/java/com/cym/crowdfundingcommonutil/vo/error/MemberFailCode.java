package com.cym.crowdfundingcommonutil.vo.error;


import lombok.AllArgsConstructor;
import lombok.Data;
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
public enum MemberFailCode {
  /**
   * member业务失败返回数据
   */
  NULL_ERROR_MEMBERS("44444", "抱歉查询不到当前会员的信息");



  private String code;

  private String message;


}
