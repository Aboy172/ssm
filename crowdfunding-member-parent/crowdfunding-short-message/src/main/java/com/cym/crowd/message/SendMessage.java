package com.cym.crowd.message;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cym.crowd.util.HttpUtils;
import com.cym.crowdfundingcommonutil.exception.GlobalException;
import com.cym.crowdfundingcommonutil.vo.error.SendMessageFailCode;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * @author cym
 * @date 2022-06-02  21:06
 * @description 发送短信工具类
 */
@Service
public class SendMessage {

  public JSONObject sendMessageServiceResponse() {
    String host = "https://dfsns.market.alicloudapi.com";
    String path = "/data/send_sms";
    String method = "POST";
    String appcode = "d90b68d30d8046cf97479c12b212bfaa";
    Map<String, String> headers = new HashMap<>();
    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
    headers.put("Authorization", "APPCODE " + appcode);
    //根据API的要求，定义相对应的Content-Type
    headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
    Map<String, String> querys = new HashMap<>();
    Map<String, String> bodys = new HashMap<>();
    int count = 6;
    String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, count - 1)));
    bodys.put("content", "code:" + code);
    bodys.put("phone_number", "15207553802");
    bodys.put("template_id", "TPL_0000");

    JSONObject jsonObject;
    try {
      HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
      String responseStr = EntityUtils.toString(response.getEntity());
      jsonObject = JSONUtil.parseObj(responseStr);
      return jsonObject;
      //获取response的body
      //System.out.println(EntityUtils.toString(response.getEntity()));
    } catch (Exception e) {
      throw new GlobalException(SendMessageFailCode.SEND_MESSAGE_ERROR);
    }
  }


}
