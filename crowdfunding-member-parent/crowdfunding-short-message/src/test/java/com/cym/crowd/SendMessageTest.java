package com.cym.crowd;

import cn.hutool.json.JSONObject;
import com.cym.crowd.config.ShortMessageProperties;
import com.cym.crowd.message.SendMessage;
import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.utils.CrowdUtils;
import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.error.SendMessageFailCode;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author cym
 * @date 2022-06-02  21:45
 * @description
 */
@SpringBootTest
@Slf4j
public class SendMessageTest {

  @Resource
  private ShortMessageProperties shortMessageProperties;

  /**
   * 测试短信服务
   */
  @Test
  public void test() {
    try {
      RespBean<String> stringRespBean = CrowdUtils.sendCodeShortMessage(
          shortMessageProperties.getHost(),
          shortMessageProperties.getPath(),
          shortMessageProperties.getMethod(), shortMessageProperties.getAppcode(),
          shortMessageProperties.getHeader(), "15207553802",
          shortMessageProperties.getTemplateId());
      Assertions.assertEquals(200,stringRespBean.getCode());
    }  catch (Exception e) {
      throw new GlobalException(SendMessageFailCode.SEND_MESSAGE_ERROR);
    }
  }
}
