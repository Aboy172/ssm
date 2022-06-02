package com.cym.crowdfundingcommonutil.exception;

import com.cym.crowdfundingcommonutil.vo.RespBeanEnum;
import com.cym.crowdfundingcommonutil.vo.error.MemberFailCode;
import com.cym.crowdfundingcommonutil.vo.error.RedisFailCode;
import com.cym.crowdfundingcommonutil.vo.error.SendMessageFailCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author 86152
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {


  private RespBeanEnum respBeanEnum;


  private MemberFailCode memberFailCode;

  private RedisFailCode redisFailCode;

  private SendMessageFailCode sendMessageFailCode;

  public GlobalException(RespBeanEnum respBeanEnum) {
    this.respBeanEnum = respBeanEnum;
  }

  public GlobalException(String message, Throwable cause, SendMessageFailCode sendMessageFailCode) {
    super(message, cause);
    this.sendMessageFailCode = sendMessageFailCode;
  }

  public GlobalException(RedisFailCode redisFailCode) {
    this.redisFailCode = redisFailCode;
  }

  public GlobalException(String message, RespBeanEnum respBeanEnum) {
    this.respBeanEnum = respBeanEnum;
  }

  public GlobalException(MemberFailCode memberFailCode) {
    this.memberFailCode = memberFailCode;
  }

  public GlobalException(SendMessageFailCode sendMessageFailCode) {
    this.sendMessageFailCode = sendMessageFailCode;
  }
}
