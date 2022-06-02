package com.cym.crowdfundingcommonutil.exception;

import cn.hutool.json.JSONObject;
import com.purgeteam.dispose.starter.exception.category.BusinessException;
import com.purgeteam.dispose.starter.exception.error.details.BusinessErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cym
 * @date 2022-06-02  21:13
 * @description 发送短信异常类
 */

@ToString
@Getter
@EqualsAndHashCode(callSuper = true)
@Setter
public class SendMessageException  extends BusinessException {

  private String code;
  private JSONObject jsonObject;
  private String message;




  public SendMessageException(String code, String message, JSONObject jsonObject) {
    super(code,message);
    this.jsonObject = jsonObject;
  }
}
