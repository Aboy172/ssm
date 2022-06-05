package com.cym.crowd.handler;

import cn.hutool.json.JSONObject;
import com.cym.crowd.message.SendMessage;
import com.cym.crowdfundingcommonutil.constant.CrowdConstant;
import com.cym.crowd.config.ShortMessageProperties;
import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.utils.CrowdUtils;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.error.SendMessageFailCode;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  @Resource
  private ShortMessageProperties shortMessageProperties;

  /**
   * 测试短信接口
   *
   * @return
   */
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

  @GetMapping("/send/message/{phoneNumber}")
  public RespBean<String> sendMessage(@PathVariable("phoneNumber") String phoneNumber) {
    //将返回的json数据转换为json对象
    try {
      return CrowdUtils.sendCodeShortMessage(shortMessageProperties.getHost(),
          shortMessageProperties.getPath(),
          shortMessageProperties.getMethod(), shortMessageProperties.getAppcode(),
          shortMessageProperties.getHeader(), phoneNumber, shortMessageProperties.getTemplateId());
    } catch (Exception e) {
      throw new GlobalException(SendMessageFailCode.SEND_MESSAGE_ERROR);
    }
  }

}
