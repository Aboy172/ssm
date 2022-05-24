package com.cym.crowdfundingcommonutil.vo.error;

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
public enum RedisFailCode {

  /**
   * redis模块失败返回数据
   */
  NULL_ERROR_MEMBERS(45555, "设置值失败"),
  NULL_MESSAGES(45556, "没有数据"),
  KEY_IS_TIMEOUT(45557,"session已过期,请重新登录");
  private long code;

  private String message;


}
