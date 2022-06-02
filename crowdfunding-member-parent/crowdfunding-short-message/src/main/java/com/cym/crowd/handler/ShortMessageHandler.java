package com.cym.crowd.handler;

import cn.hutool.json.JSONObject;
import com.cym.crowd.message.SendMessage;
import com.cym.crowdfundingcommonutil.constant.CrowdConstant;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.error.SendMessageFailCode;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cym
 * @date 2022-06-02  21:02
 * @description
 */
@RestController
public class ShortMessageHandler {

  @Resource
  private SendMessage sendMessageService;

  @GetMapping("/send/message")
  public RespBean<String> test() {
    JSONObject response = sendMessageService.sendMessageServiceResponse();
    String status = response.getStr("status");
    if (CrowdConstant.MESSAGE_STATUS_RATE_LIMIT.equals(status)) {
      return RespBean.error(SendMessageFailCode.SEND_MESSAGE_REPEAT_ERROR.getCode(),
          SendMessageFailCode.SEND_MESSAGE_REPEAT_ERROR.getMessage());
    }
    if (CrowdConstant.MESSAGE_STATUS_OK.equals(status)) {
      return RespBean.success("发送成功");
    }
    return RespBean.error(SendMessageFailCode.SEND_MESSAGE_SERVER_ERROR.getCode(),
        SendMessageFailCode.SEND_MESSAGE_SERVER_ERROR.getMessage());

  }

}
