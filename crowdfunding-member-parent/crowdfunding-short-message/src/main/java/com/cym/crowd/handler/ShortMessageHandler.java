package com.cym.crowd.handler;

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
  private ShortMessageProperties shortMessageProperties;

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
