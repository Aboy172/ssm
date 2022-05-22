package com.cym.crowdfundingcommonutil.exception;

import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import com.cym.crowdfundingcommonutil.vo.error.MemberFailCode;
import com.cym.crowdfundingcommonutil.vo.success.MemberSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author 86152
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {


  private RespBeanEnum respBeanEnum;

  private MemberSuccessCode memberSuccessCode;

  private MemberFailCode memberFailCode;

  public GlobalException(RespBeanEnum respBeanEnum) {
    this.respBeanEnum = respBeanEnum;
  }

  public GlobalException(String message, RespBeanEnum respBeanEnum) {
    super(message);
    this.respBeanEnum = respBeanEnum;
  }

  public GlobalException(String message, Throwable cause, RespBeanEnum respBeanEnum) {
    super(message, cause);
    this.respBeanEnum = respBeanEnum;
  }

  public GlobalException(Throwable cause, RespBeanEnum respBeanEnum) {
    super(cause);
    this.respBeanEnum = respBeanEnum;
  }

  public GlobalException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace, RespBeanEnum respBeanEnum) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.respBeanEnum = respBeanEnum;
  }

  public GlobalException() {
  }

  public GlobalException(MemberFailCode memberFailCode) {
    this.memberFailCode = memberFailCode;
  }

  public GlobalException(MemberSuccessCode memberSuccessCode) {
    this.memberSuccessCode = memberSuccessCode;
  }
}
