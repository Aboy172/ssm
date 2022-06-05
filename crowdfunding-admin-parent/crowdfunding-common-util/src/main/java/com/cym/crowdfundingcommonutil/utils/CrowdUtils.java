package com.cym.crowdfundingcommonutil.utils;

import com.cym.crowdfundingcommonutil.vo.RespBean;
import com.cym.crowdfundingcommonutil.vo.error.SendMessageFailCode;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.springframework.http.HttpStatus;


/**
 * 判断是否为ajax请求，如果是返回true，如果不是，说明是普通请求，返回false
 */
public class CrowdUtils {

  private static final int COUNT = 6;

  public static boolean judgeRequestType(HttpServletRequest request) {
    String contentTypeHeader = request.getHeader("Content-Type");
    String xRequestHeader = request.getHeader("X-Requested-With");
    return
        contentTypeHeader != null && contentTypeHeader.contains("application/json")
            ||
            ("XMLHttpRequest".equals(xRequestHeader));
  }

  /**
   *
   * @param host 服务器地址
   * @param path 请求路径
   * @param method 请求方式
   * @param appcode appcode
   * @param header 请求头信息
   * @param phoneNumber 手机号
   * @param templateId 模板id
   * @return 调用结果
   * @throws Exception 抛出所有会发生的异常
   */
  public static RespBean<String> sendCodeShortMessage(
      String host,
      String path,
      String method,
      String appcode,
      String header,
      String phoneNumber,
      String templateId
  ) throws Exception {
    Map<String, String> headers = new HashMap<>();
    headers.put("Authorization", "APPCODE " + appcode);
    //根据API的要求，定义相对应的Content-Type
    headers.put("Content-Type", header);
    Map<String, String> querys = new HashMap<>(2);
    Map<String, String> bodys = new HashMap<>(3);
    String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, COUNT - 1)));
    bodys.put("content", "code:" + code);
    bodys.put("phone_number", phoneNumber);
    bodys.put("template_id", templateId);
    HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
    StatusLine statusLine = response.getStatusLine();
    int statusCode = statusLine.getStatusCode();
    if (statusCode == HttpStatus.OK.value()) {
      return RespBean.success(code);
    } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
      return RespBean.error(SendMessageFailCode.SEND_MESSAGE_REPEAT_ERROR.getCode(),
          SendMessageFailCode.SEND_MESSAGE_REPEAT_ERROR.getMessage());
    }
    return RespBean.error(SendMessageFailCode.SEND_MESSAGE_SERVER_ERROR.getCode(),
        SendMessageFailCode.SEND_MESSAGE_SERVER_ERROR.getMessage());

    //获取response的body
    //System.out.println(EntityUtils.toString(response.getEntity()));

  }

}

