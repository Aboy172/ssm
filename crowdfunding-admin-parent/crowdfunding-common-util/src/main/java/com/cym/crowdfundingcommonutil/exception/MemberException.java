package com.cym.crowdfundingcommonutil.exception;

import com.cym.crowdfundingcommonutil.vo.error.MemberFailCode;
import com.purgeteam.dispose.starter.exception.category.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author cym
 * @date 2022-05-18  21:05
 * @description member业务全局处理异常
 */
@Getter
@Setter
public class MemberException extends BusinessException {
  private String code;
  private boolean isShowMsg = true;

  /**
   * 使用枚举传参
   *
   * @param errorCode 异常枚举
   */
  public MemberException(MemberFailCode errorCode) {
    super(errorCode.getCode(), errorCode.getMessage());
    this.code = errorCode.getCode();
  }
}
