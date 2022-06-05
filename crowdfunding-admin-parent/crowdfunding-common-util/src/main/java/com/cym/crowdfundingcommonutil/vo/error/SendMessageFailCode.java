package com.cym.crowdfundingcommonutil.vo.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author cym
 * @date 2022-06-02  22:26
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public enum SendMessageFailCode {
  /**
   * 短信服务失败统一返回结果集
   */


  SEND_MESSAGE_ERROR(47777, "服务器错误，请联系管理员"),
  SEND_MESSAGE_REPEAT_ERROR(47778,"请不要重复发送"),

  SEND_MESSAGE_SERVER_ERROR(48000,"获取失败,请稍后再试");


  private long code;

  private String message;
}
