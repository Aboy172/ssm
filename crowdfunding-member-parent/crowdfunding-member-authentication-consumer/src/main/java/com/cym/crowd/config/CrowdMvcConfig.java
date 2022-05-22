package com.cym.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cym
 * @date 2022-05-19  12:31
 * @description mvc 配置类
 */
@Configuration(proxyBeanMethods = false)
public class CrowdMvcConfig implements WebMvcConfigurer {

  public static final String[] VIEW_NAME = {
      "portal", "member-reg"
  };


  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName(VIEW_NAME[0]);
    registry.addViewController("/auth/member/to/reg/page.html").setViewName(VIEW_NAME[1]);

  }

}
