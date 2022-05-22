// This file is auto-generated, don't edit it. Thanks.
package com.cym.crowd.message;


import com.cym.crowd.util.HttpUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

/**
 * @author cym
 */
public class Sample {


  public static void main(String[] args) {
    //
    String host = "https://jumsendsms.market.alicloudapi.com";
    String path = "/sms/send-upgrade";
    String method = "POST";
    String appcode = "204068987";
    Map<String, String> headers = new HashMap<>(32);
    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
    headers.put("Authorization", "APPCODE " + appcode);
    Map<String, String> querys = new HashMap<>(32);
    querys.put("mobile", "15207553802");
    querys.put("templateId", "SMS_241364048");
    querys.put("value", "111");
    Map<String, String> body = new HashMap<>();

    try {
      /**
       * 重要提示如下:
       * HttpUtils请从
       * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
       * 下载
       *
       * 相应的依赖请参照
       * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
       */
      HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, body);
      System.out.println(response.toString());
      //获取response的body
      System.out.println(EntityUtils.toString(response.getEntity()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}






