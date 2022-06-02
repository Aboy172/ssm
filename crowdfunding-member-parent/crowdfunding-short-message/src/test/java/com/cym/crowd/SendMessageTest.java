package com.cym.crowd;

import cn.hutool.json.JSONObject;
import com.cym.crowd.message.SendMessage;
import lombok.extern.slf4j.Slf4j;
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

  @Autowired
  private SendMessage sendMessageService;

  /**
   * 测试短信服务
   */
  @Test
  public void test() {
    JSONObject response = sendMessageService.sendMessageServiceResponse();
    log.info(response.toString());
  }
}
