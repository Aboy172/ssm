package com.cym.crowd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cym
 * @date 2022-06-05  15:59
 * @description 短信服务配置
 */
@Component
@ConfigurationProperties(prefix = "short.message")
@Data
public class ShortMessageProperties {

  private String host;
  private String path;
  private String method;
  private String header;
  private String appcode;
  private String templateId;

}
